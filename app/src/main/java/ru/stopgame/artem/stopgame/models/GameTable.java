package ru.stopgame.artem.stopgame.models;


import android.graphics.Bitmap;

public class GameTable {
    private String urlView;
    private Bitmap urlViewBit;
    private String game;
    private String platform;
    private String release;
    private String develope;
    private String publisher;
    private String tedxtOfficale;

    public String getUrlView() {
        return urlView;
    }

    public void setUrlView(String urlView) {
        this.urlView = urlView;
    }

    public Bitmap getUrlViewBit() {
        return urlViewBit;
    }

    public void setUrlViewBit(Bitmap urlViewBit) {
        this.urlViewBit = urlViewBit;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getDevelope() {
        return develope;
    }

    public void setDevelope(String develope) {
        this.develope = develope;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTedxtOfficale() {
        return tedxtOfficale;
    }

    public void setTedxtOfficale(String tedxtOfficale) {
        this.tedxtOfficale = tedxtOfficale;
    }

    @Override
    public String toString() {
        return "GameTable{" +
                "urlView='" + urlView + '\'' +
                ", urlViewBit=" + urlViewBit +
                ", game='" + game + '\'' +
                ", platform='" + platform + '\'' +
                ", release='" + release + '\'' +
                ", develope='" + develope + '\'' +
                ", publisher='" + publisher + '\'' +
                ", tedxtOfficale='" + tedxtOfficale + '\'' +
                '}';
    }
}
