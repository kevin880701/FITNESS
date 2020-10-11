package com.hr.fitness.Model;


import java.io.Serializable;


public class HistoryRecord implements Serializable {
    public int id;
    public String date;
    public String itemId;
    public String itemName;
    public String itemContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }
}
