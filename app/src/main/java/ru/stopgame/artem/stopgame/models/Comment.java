package ru.stopgame.artem.stopgame.models;


import android.graphics.Bitmap;

public class Comment {
    private String urlImage;//Ссылка изображение
    private Bitmap urlImageBit;//Само изображене
    private String userName;//Ник пользователя
    private String userUrl;//
    private String raiting;//
    private String meRaiting = null; // + - null
    private String text;// Текст комментария
    private String heshtag;// # хештег только цифры
    private String heshtagUp;// #
    private int numberComm=0;//Номер коментария
    private String date;//or String Дата комментарии
    private boolean online;// В сети ли пользователь
    private String type;//
    private String answering;//При нажатии ответит пользователю




    public Comment() {
    }


    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Bitmap getUrlImageBit() {
        return urlImageBit;
    }

    public void setUrlImageBit(Bitmap urlImageBit) {
        this.urlImageBit = urlImageBit;
    }

    public String getHeshtagUp() {
        return heshtagUp;
    }

    public void setHeshtagUp(String heshtagUp) {
        this.heshtagUp = heshtagUp;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public int getNumberComm() {
        return numberComm;
    }

    public void setNumberComm(int numberComm) {
        this.numberComm = numberComm;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRaiting() {
        return raiting;
    }

    public void setRaiting(String raiting) {
        this.raiting = raiting;
    }

    public String getMeRaiting() {
        return meRaiting;
    }

    public void setMeRaiting(String meRaiting) {
        this.meRaiting = meRaiting;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeshtag() {
        return heshtag;
    }

    public void setHeshtag(String heshtag) {
        this.heshtag = heshtag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswering() {
        return answering;
    }

    public void setAnswering(String answering) {
        this.answering = answering;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "urlImage='" + urlImage + '\'' +
                ", urlImageBit=" + urlImageBit +
                ", userName='" + userName + '\'' +
                ", userUrl='" + userUrl + '\'' +
                ", raiting='" + raiting + '\'' +
                ", meRaiting='" + meRaiting + '\'' +
                ", text='" + text + '\'' +
                ", heshtag='" + heshtag + '\'' +
                ", heshtagUp='" + heshtagUp + '\'' +
                ", numberComm=" + numberComm +
                ", date='" + date + '\'' +
                ", online=" + online +
                ", type='" + type + '\'' +
                ", answering='" + answering + '\'' +
                '}';
    }
}
