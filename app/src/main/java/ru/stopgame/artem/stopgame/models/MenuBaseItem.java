package ru.stopgame.artem.stopgame.models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuBaseItem implements Serializable {
    private String name;
    private String url;
    private boolean active=false;//Для /game tab Потому что удобно!!
    private List<MenuBaseItem> array = new ArrayList<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MenuBaseItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuBaseItem> getArray() {
        return array;
    }

    public void setArray(List<MenuBaseItem> array) {
        this.array = array;
    }

    public MenuBaseItem(String name, List<MenuBaseItem> array) {
        this.name = name;
        this.array = array;
    }

    public MenuBaseItem(String name, String url, List<MenuBaseItem> array) {
        this.name = name;
        this.url = url;
        this.array = array;
    }

    public MenuBaseItem(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public MenuBaseItem() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "MenuBaseItem{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", active=" + active +
                ", array=" + array +
                '}';
    }
}
