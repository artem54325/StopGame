package ru.stopgame.artem.stopgame.models;


import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//("ALL")
public class PostShow  {
    //("checkstyle:WhitespaceAround")
    private String titlePost="";
    //("checkstyle:WhitespaceAround")
    private String text="";
    private List<PhotoAndText> image = new ArrayList<>();
    //("checkstyle:WhitespaceAround")
    private GameTable gameTable=null;

    //("checkstyle:WhitespaceAround")
    private String audioMedia=null;

    //("checkstyle:WhitespaceAround")
    private Bitmap view=null;
    //("checkstyle:WhitespaceAround")
    private String viewUrl="";
    //("checkstyle:WhitespaceAround")
    private String name="";
    //("checkstyle:WhitespaceAround")
    private String date="";
    //("checkstyle:WhitespaceAround")
    private String views="";
    //("checkstyle:WhitespaceAround")
    private String comments="";
    //("checkstyle:WhitespaceAround")
    private String priting="";
    private int appraisal;

    //("checkstyle:WhitespaceAround")
    private String tag="";
    //("checkstyle:WhitespaceAround")
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

    //("checkstyle:Indentation")
    public void setText(String text) {
        this.text = text;
    }

    //("checkstyle:Indentation")
    public List<PhotoAndText> getImage() {
        return image;
    }

    //("checkstyle:Indentation")
    public void setImage(List<PhotoAndText> image) {
        this.image = image;
    }

    //("checkstyle:Indentation")
    public void setGameTable(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    //("checkstyle:Indentation")
    public Bitmap getView() {
        return view;
    }

    //("checkstyle:Indentation")
    public void setView(Bitmap view) {
        this.view = view;
    }

    //("checkstyle:Indentation")
    public String getViewUrl() {
        return viewUrl;
    }

    //("checkstyle:Indentation")
    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }

    //("checkstyle:Indentation")
    public String getName() {
        return name;
    }

    //("checkstyle:Indentation")
    public void setName(String name) {
        this.name = name;
    }

    //("checkstyle:Indentation")
    public String getDate() {
        return date;
    }

    //("checkstyle:Indentation")
    public void setDate(String date) {
        this.date = date;
    }

    //("checkstyle:Indentation")
    public String getViews() {
        return views;
    }

    //("checkstyle:Indentation")
    public void setViews(String views) {
        this.views = views;
    }

    //("checkstyle:Indentation")
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
