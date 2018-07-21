package ru.stopgame.artem.stopgame.models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class DialogPostModel implements Serializable {
    private String visitorsValue;
    private String visitorsColor;
    private String visitorsNumber;
    private String followNumber;
    private List<MenuBaseItem> tabsMenu = new ArrayList<>();

    public String getVisitorsValue() {
        return visitorsValue;
    }

    public void setVisitorsValue(String visitorsValue) {
        this.visitorsValue = visitorsValue;
    }

    public String getVisitorsNumber() {
        return visitorsNumber;
    }

    public void setVisitorsNumber(String visitorsNumber) {
        this.visitorsNumber = visitorsNumber;
    }

    public String getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(String followNumber) {
        this.followNumber = followNumber;
    }

    public List<MenuBaseItem> getTabsMenu() {
        return tabsMenu;
    }

    public void setTabsMenu(List<MenuBaseItem> tabsMenu) {
        this.tabsMenu = tabsMenu;
    }

    public String getVisitorsColor() {
        return visitorsColor;
    }

    public void setVisitorsColor(String visitorsColor) {
        this.visitorsColor = visitorsColor;
    }

    @Override
    public String toString() {
        return "DialogPostModel{" +
                "visitorsValue='" + visitorsValue + '\'' +
                ", visitorsColor='" + visitorsColor + '\'' +
                ", visitorsNumber='" + visitorsNumber + '\'' +
                ", followNumber='" + followNumber + '\'' +
                ", tabsMenu=" + tabsMenu +
                '}';
    }
}
