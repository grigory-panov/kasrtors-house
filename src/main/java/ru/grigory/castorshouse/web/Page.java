package ru.grigory.castorshouse.web;

/**
 * Created by grigory on 12.11.15.
 */
public class Page {
    private String url;
    private int number;

    public Page() {
    }

    public Page(int number, String url) {
        this.url = url;
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
