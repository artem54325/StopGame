package ru.stopgame.artem.stopgame.models;


import android.graphics.Bitmap;

public class ImgTextObject {
    private String text;
    private Bitmap image;

    public ImgTextObject(String text, Bitmap image) {
        this.text = text;
        this.image = image;
    }

    public ImgTextObject() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
