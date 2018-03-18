package ru.grigory.castorshouse.web;

import org.apache.commons.lang.StringUtils;

import java.util.PropertyResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 12.10.14
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    public static String resolveMessage(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        String resolvedMessage = null;
        try {
            resolvedMessage = PropertyResourceBundle.getBundle("Messages").getString(code);
        } catch (Exception ex) {

        }
        if (resolvedMessage == null) {
            resolvedMessage = "not found:" + code;
        }
        return resolvedMessage;
    }
}
