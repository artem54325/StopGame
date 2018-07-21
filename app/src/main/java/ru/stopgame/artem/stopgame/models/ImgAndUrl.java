package ru.stopgame.artem.stopgame.models;


import android.graphics.Bitmap;

public class ImgAndUrl {
    private String urlImage;
    private Bitmap bitmap;
    private String urlLink;

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    @Override
    public String toString() {
        return "ImgAndUrl{" +
                "urlImage='" + urlImage + '\'' +
                ", bitmap=" + bitmap +
                ", urlLink='" + urlLink + '\'' +
                '}';
    }
}
