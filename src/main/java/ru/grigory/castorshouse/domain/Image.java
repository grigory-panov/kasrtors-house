package ru.grigory.castorshouse.domain;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by grigory on 10.11.15.
 */
public class Image implements Serializable {
    private String filename;
    private String header;
    private String description;
    private String tag;
    private String md5;
    private String md5thumb;
    private Date dateAdd;
    private boolean inClub;
    private String filterTag="";
    private String filterInClub="";

    public String getThumb(){
        return "/image/thumb/" + tag + "/" + filename;
    }

    public String getPhotoLarge(){
        return "/photoLarge.html?tag=" + tag + "&name=" + filename + "&filterInClub=" + filterInClub  + "&filterTag=" + filterTag;
    }

    public String getFull(){
        return "/image/full/" + tag + "/" + filename;
    }

    public boolean isInClub() {
        return inClub;
    }

    public void setInClub(boolean inClub) {
        this.inClub = inClub;
    }

    public String getMd5thumb() {
        return md5thumb;
    }

    public void setMd5thumb(String md5thumb) {
        this.md5thumb = md5thumb;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setFilter(String tag, Boolean club) {
        filterInClub = club != null ? club.toString() : "";
        filterTag = tag != null ? tag : "";
    }
}
