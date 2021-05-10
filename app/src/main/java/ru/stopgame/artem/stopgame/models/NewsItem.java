package ru.stopgame.artem.stopgame.models;


import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class NewsItem implements Serializable{
    private String text;
    private String date;
    private Bitmap img;
    private String imgUrl;
    private String namePost;
    private String komments;
    private String url;

    private String colorTag;
    private String tag;

    //для блогов
    private String view=null;//Просмотры
    private Map<String, String> tags = new HashMap<>();//Тэги
    private String rating=null;//Рэйтинг

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getColorTag() {
        return colorTag;
    }

    public void setColorTag(String colorTag) {
        this.colorTag = colorTag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getKomments() {
        return komments;
    }

    public void setKomments(String komments) {
        this.komments = komments;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getNamePost() {
        return namePost;
    }

    public void setNamePost(String namePost) {
        this.namePost = namePost;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", img=" + img +
                ", imgUrl='" + imgUrl + '\'' +
                ", namePost='" + namePost + '\'' +
                ", komments='" + komments + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
