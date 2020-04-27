package ru.stopgame.artem.stopgame.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.Date;



public class Authorization extends SugarRecord {

    public Authorization(String username, String password) {
        this.username = username;
        this.password = password;
        this.dateAuthorization = new Date();
    }


    public Authorization() {
    }
    @Unique
     String username;
     String password;
     Date dateAuthorization;
     boolean activeStatus;


    public String getUsername() {
        return username;
    }

    //({"checkstyle:Indentation", "CheckStyle"})
    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    //("checkstyle:Indentation")
    public void setPassword(String password) {
        this.password = password;
    }

    //("checkstyle:Indentation")
    public Date getDateAuthorization() {
        return dateAuthorization;
    }

    //("checkstyle:Indentation")
    public void setDateAuthorization(Date dateAuthorization) {
        this.dateAuthorization = dateAuthorization;
    }

    //("checkstyle:Indentation")
    public boolean isActiveStatus() {
        return activeStatus;
    }

    //("checkstyle:Indentation")
    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
}
