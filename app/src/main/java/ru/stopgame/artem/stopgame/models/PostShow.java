package ru.stopgame.artem.stopgame.models;


import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostShow implements Serializable {
    private String titlePost="";
    private String text="";
    private List<PhotoAndText> image = new ArrayList<>();
    private GameTable gameTable=null;

    private String audioMedia=null;

    private Bitmap view=null;
    private String viewUrl="";
    private String name="";
    private String date="";
    private String views="";
    private String comments="";
    private String priting="";
    private int appraisal;

    private String tag="";
    private String tagPad="";

    private List<Comment> commentsList = new ArrayList<>();


    public String getAudioMedia() {
        return audioMedia;
    }

    public void setAudioMedia(String audioMedia) {
        this.audioMedia = audioMedia;
    }

    public List<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagPad() {
        return tagPad;
    }

    public void setTagPad(String tagPad) {
        this.tagPad = tagPad;
    }

    public String getTitlePost() {
        return titlePost;
    }

    public void setTitlePost(String titlePost) {
        this.titlePost = titlePost;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<PhotoAndText> getImage() {
        return image;
    }

    public void setImage(List<PhotoAndText> image) {
        this.image = image;
    }

    public void setGameTable(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    public Bitmap getView() {
        return view;
    }

    public void setView(Bitmap view) {
        this.view = view;
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPriting() {
        return priting;
    }

    public void setPriting(String priting) {
        this.priting = priting;
    }

    public GameTable getGameTable() {
        return gameTable;
    }

    public int getAppraisal() {
        return appraisal;
    }

    public void setAppraisal(int appraisal) {
        this.appraisal = appraisal;
    }

    public class PhotoAndText{
        private String text;
        private Bitmap img;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Bitmap getImg() {
            return img;
        }

        public void setImg(Bitmap img) {
            this.img = img;
        }
    }

    @Override
    public String toString() {
        return "PostShow{" +
                "titlePost='" + titlePost + '\'' +
                ", text='" + text + '\'' +
                ", image=" + image +
                ", gameTable=" + gameTable +
                ", view=" + view +
                ", viewUrl='" + viewUrl + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", views='" + views + '\'' +
                ", comments='" + comments + '\'' +
                ", priting='" + priting + '\'' +
                ", appraisal=" + appraisal +
                '}';
    }
}
