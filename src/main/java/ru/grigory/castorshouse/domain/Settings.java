package ru.grigory.castorshouse.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 17.10.14
 * Time: 22:23
 * To change this template use File | Settings | File Templates.
 */
public class Settings implements Serializable {
    private String key;
    private String value;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
