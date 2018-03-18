package ru.grigory.castorshouse.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.grigory.castorshouse.controller.ImageController;
import ru.grigory.castorshouse.dao.ImageDao;
import ru.grigory.castorshouse.domain.Image;
import ru.grigory.castorshouse.exception.BusinessException;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by grigory on 10.11.15.
 */
@Service
public class ImageService {
    @Autowired
    private ImageDao imageDao;

    public void addImage(Image image){
        if(image.getFilename() == null){
            throw new BusinessException("filename cannot be null");
        }
        imageDao.add(image);
    }

    public void updateImage(Image image){
        if(image.getFilename() == null){
            throw new BusinessException("filename cannot be null");
        }
        imageDao.update(image);
    }

    public Object getPange(int offset, int limit) {
        return imageDao.getRange(offset, limit);
    }

    public int getTotalCount() {
        return imageDao.getTotalCount();
    }

    public Image getByTagAndName(String tag, String name) {
        Image im = imageDao.getImageByTagAndName(tag, name);
        if(im!=null) {
            im.setFilter(im.getTag(), im.isInClub());
        }
        return im;
    }

    public void deleteImage(Image img) {
        imageDao.delete(img);
    }

    public Image getRandomImageInTag(Boolean inClub, String tag) {
        List<Image> imagesInTag = imageDao.getImagesByTag(tag, inClub);
        if(imagesInTag.isEmpty()){
            return null;
        }
        Random random = new Random();
        Image im = imagesInTag.get(random.nextInt(imagesInTag.size()));
        im.setFilter(im.getTag(), inClub);
        return im;
    }

    public Image getRandomImageInTags(Boolean inClub, String ... params) {
        List<Image> imagesInTag = imageDao.getImagesByTags(params, inClub);
        if(imagesInTag.isEmpty()){
            return null;
        }
        Random random = new Random();
        Image im = imagesInTag.get(random.nextInt(imagesInTag.size()));
        im.setFilter(im.getTag(), inClub);
        return im;
    }

    public int getTotalCountByTag(String tag, Boolean club) {
        return imageDao.getTotalCountByTag(tag, club);
    }


    public List<Image> getPangeInTag(String tag, Boolean club, int offset, int limit) {
        List<Image> list = imageDao.getImagesByTag(tag, club, offset, limit);
        for(Image img : list){
            img.setFilter(tag, club);
        }
        return list;
    }

    public String getNextFreeNameByTagAndName(String tag, String name) {
        Pattern regexp = Pattern.compile("^"+ Pattern.quote(name) + "(\\d+)\\." + ImageController.extension + "$");
        List<Image> imagesInTag = imageDao.getImagesByFirstPartOfName(tag, name);

        int lastInd = 0;

        for(Image img : imagesInTag){
            Matcher m = regexp.matcher(img.getFilename());
            while(m.find()){
                if(StringUtils.isNotEmpty(m.group(1))) {
                    lastInd = Math.max(lastInd, Integer.parseInt(m.group(1)));
                }
            }
        }
        if(0 != lastInd){
            return name + (++lastInd);
        }
        return imageDao.getImageByTagAndName(tag, name + "." + ImageController.extension) == null ?  name : name + "1";
    }
}
