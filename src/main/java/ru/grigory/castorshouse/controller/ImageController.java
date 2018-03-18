package ru.grigory.castorshouse.controller;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.grigory.castorshouse.domain.Image;
import ru.grigory.castorshouse.exception.ImageProcessingException;
import ru.grigory.castorshouse.exception.ResourceNotFoundException;
import ru.grigory.castorshouse.service.ImageService;
import ru.grigory.castorshouse.service.SettingsService;
import ru.grigory.castorshouse.web.Page;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by grigory on 11.09.15.
 */
@Controller
public class ImageController {

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private ImageService imageService;

    public final static String extension = "png";
    private final static String thumbnail = "_thumb";
    private final static int pageSize = 25;


    @ResponseBody
    @RequestMapping(value = "full/{tag}/{name}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImgOrig(@PathVariable(value = "tag") String tag,
                             @PathVariable(value = "name") String name,
                             HttpServletRequest request, HttpServletResponse response) throws IOException {

        Image img = imageService.getByTagAndName(tag, name + "." + extension);
        if (img == null) {
            throw new ResourceNotFoundException();
        }
        response.setHeader("ETag", img.getMd5());
        String cashHeader = request.getHeader("If-None-Match");
        if (cashHeader != null) {
            if (img.getMd5().equals(cashHeader)) {
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                return null;
            }
        }

        File f = new File(getStorageDir(), tag + "_" + img.getFilename());
        if (!f.exists()) {
            throw new ResourceNotFoundException();
        } else {
            try (FileInputStream fis = new FileInputStream(f)) {
                return IOUtils.toByteArray(fis);
            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "thumb/{tag}/{name}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImgThumb(@PathVariable(value = "tag") String tag,
                              @PathVariable(value = "name") String name,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {

        Image img = imageService.getByTagAndName(tag, name + "." + extension);
        if (img == null) {
            throw new ResourceNotFoundException();
        }
        response.setHeader("ETag", img.getMd5thumb());
        String cashHeader = request.getHeader("If-None-Match");
        if (cashHeader != null) {
            if (img.getMd5thumb().equals(cashHeader)) {
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                return null;
            }
        }

        File f = new File(getStorageDir(), tag + "_" + name + thumbnail + "." + extension);
        if (!f.exists()) {
            throw new ResourceNotFoundException();
        } else {
            try (FileInputStream fis = new FileInputStream(f)) {
                return IOUtils.toByteArray(fis);
            }
        }
    }


    // admin methods
    @RequestMapping(method = RequestMethod.GET, value = "admin/images.html")
    public String getImagesList(@RequestParam(value = "page", required = false) Integer page, Model model) {
        int totalCount = imageService.getTotalCount();
        int pageCount = (int)Math.floor((double)totalCount/pageSize);
        List<Page> list = new ArrayList<>(pageCount);
        if(pageCount > 1) {
            for (int i = 0; i < pageCount; i++) {
                list.add(new Page(i + 1, "/admin/images.html?page=" + (i + 1)));
            }
        }
        if (page == null) {
            page = 1;
        }
        if(page > pageCount && pageCount > 0){
            page = pageCount;
        }
        model.addAttribute("currentPage", page);
        model.addAttribute("pages", list);

        model.addAttribute("imagesPage", imageService.getPange(pageSize * (page - 1), pageSize));
        return "admin/images";
    }

    @RequestMapping(method = RequestMethod.GET, value = "admin/imageAdd.html")
    public String getImageAddPage() {
        return "admin/imageAdd";
    }

    @RequestMapping(method = RequestMethod.GET, value = "images/delete/{tag}/{name}")
    public String deleteImage(@PathVariable(value = "tag") String tag,
                              @PathVariable(value = "name") String name, Model model) {
        File storage = getStorageDir();
        File f = new File(storage, tag + "_" + name + "." + extension);
        if (f.exists()) {
            f.delete();
        }
        f = new File(storage, tag + "_" + name + thumbnail + "." + extension);
        if (f.exists()) {
            f.delete();
        }
        Image img = imageService.getByTagAndName(tag, name + "." + extension);
        if (img != null) {
            imageService.deleteImage(img);
        }
        return "redirect:/admin/images.html";
    }

    @RequestMapping(method = RequestMethod.POST, value = "admin/imageAdd.html")
    public String addImage(@RequestParam(value = "file") MultipartFile file,
                           @RequestParam(value = "filename") String fileName,
                           @RequestParam(value = "header", required = false) String header,
                           @RequestParam(value = "inClub", required = false) String inClub,
                           @RequestParam(value = "tag") String tag,
                           @RequestParam(value = "description", required = false) String description,
                           Model model){
        if (fileName.contains("..") || fileName.contains("/")) {
            model.addAttribute("message", "В имени не должно быть символов / или ..");
            return "admin/imageAdd";
        }
        fileName = fileName.replace(" ", "_");
        fileName = imageService.getNextFreeNameByTagAndName(tag, fileName);

        if(imageService.getByTagAndName(tag, fileName + "." + extension) != null){
            model.addAttribute("message", "Выберите другое имя, такое уже есть");
            return "admin/imageAdd";
        }

        if (fileName.contains(".")) {
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        }

        String[] md5 = storeFile(file, tag + "_" + fileName, extension);

        Image img = new Image();
        img.setMd5(md5[0]);
        img.setMd5thumb(md5[1]);
        img.setFilename(fileName + "." + extension);
        img.setTag(tag);
        img.setHeader(header);
        img.setDescription(description);
        img.setDateAdd(new Date());
        if(inClub != null) {
            img.setInClub(true);
        }
        imageService.addImage(img);
        return "redirect:/admin/images.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "admin/imageEdit.html")
    public String updateImage(@RequestParam(value = "tag") String tag,
                              @RequestParam(value = "name") String filename, Model model) {

        Image img = imageService.getByTagAndName(tag, filename);
        if (img == null) {
            throw new ResourceNotFoundException();
        }
        model.addAttribute("image", img);
        return "admin/imageEdit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "admin/imageEdit.html")
    public String updateImage(@RequestParam(value = "tag") String tag,
                              @RequestParam(value = "name") String filename,
                              @ModelAttribute Image image,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("image", image);
            return "admin/imageEdit.html";
        }
        image.setTag(tag);
        image.setFilename(filename);
        imageService.updateImage(image);
        return "redirect:/admin/images.html";
    }

    private String[] storeFile(MultipartFile file, String fileName, String ext) throws ImageProcessingException {
        try {
            String[] md5Array = new String[2];
            File storage = getStorageDir();
            BufferedImage image = ImageIO.read(file.getInputStream());
            File output = new File(storage, fileName + "." + ext);
            ImageIO.write(Scalr.resize(image, Scalr.Method.BALANCED, 800), ext, output);
            try (FileInputStream fis = new FileInputStream(output)) {
                md5Array[0] = DigestUtils.md5DigestAsHex(IOUtils.toByteArray(fis));
            }
            output = new File(storage, fileName + thumbnail + "." + ext);
            ImageIO.write(Scalr.resize(image, Scalr.Method.BALANCED, 263), ext, output);
            try (FileInputStream fis = new FileInputStream(output)) {
                md5Array[1] = DigestUtils.md5DigestAsHex(IOUtils.toByteArray(fis));
            }
            return md5Array;

        } catch (IOException ex) {
            throw new ImageProcessingException(ex);
        }
    }

    private File getStorageDir() {
        return new File(settingsService.findByKey("image-data").getValue());
    }


}
