package com.hr.fitness.Model;


import java.io.Serializable;


public class Record implements Serializable {
    public int id;
    public int gender;
    public String height;
    public String weight;
    public String waistline;
    public String BMI;
    public String date;
    public String bodyFat;
    public boolean beenClick=false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWaistline() {
        return waistline;
    }

    public void setWaistline(String waistline) {
        this.waistline = waistline;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(String bodyFat) {
        this.bodyFat = bodyFat;
    }

    public boolean isBeenClick() {
        return beenClick;
    }

    public void setBeenClick(boolean beenClick) {
        this.beenClick = beenClick;
    }
}
