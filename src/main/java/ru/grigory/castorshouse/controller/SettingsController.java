package ru.grigory.castorshouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.grigory.castorshouse.domain.Settings;
import ru.grigory.castorshouse.service.SettingsService;
import ru.grigory.castorshouse.web.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 17.10.14
 * Time: 22:22
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @ModelAttribute("settings")
    public List<Settings> populateSettings() {
        return settingsService.findAll();
    }

    @RequestMapping(value = "admin/settings.html", method = RequestMethod.GET)
    public String getSettingsList(@RequestParam(value = "message", required = false) String message, Model model) {
        String resolvedMesage = Utils.resolveMessage(message);
        if (resolvedMesage != null) {
            model.addAttribute("message", resolvedMesage);
            model.addAttribute("messageClass", message.startsWith("error.") ? "text-danger" : "text-info");
        }
        model.addAttribute("title", "Настройки");
        return "admin/settings";
    }

    @RequestMapping(value = "admin/settings.html", method = RequestMethod.POST)
    public String saveSettings(@ModelAttribute("settings_object") Settings param ) {

        String code;
        Settings settings = settingsService.findByKey(param.getKey());
        if (settings != null) {
            settings.setValue(param.getValue());
            settings.setDescription(param.getDescription());
            try {
                settingsService.update(settings);
                code = "message.OK";
            } catch (Exception ex) {
                code = "error.database";
            }
        } else {
            code = "error.settings.notfound";
        }
        return "redirect:/admin/settings.html?message=" + code;
    }


    @RequestMapping(value = "admin/settingsEdit.html", method = RequestMethod.GET)
    public ModelAndView editSettings(@RequestParam(value = "key", required = true) String key) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("settings_object", settingsService.findByKey(key));
        params.put("title", "Редактирование " + key);
        return new ModelAndView("admin/settingsEdit", params);
    }

}
