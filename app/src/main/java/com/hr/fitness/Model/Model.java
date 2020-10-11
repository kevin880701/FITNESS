package com.hr.fitness.Model;


import android.database.sqlite.SQLiteDatabase;


import java.io.Serializable;
import java.util.ArrayList;


public class Model implements Serializable {
    public SQLiteDatabase musicListDB;
//    public DataDisplayActivity dataDisplayActivity;
    public ArrayList<HealthRecord> healthRecordList;

    public SQLiteDatabase getMusicListDB() {
        return musicListDB;
    }

    public void setMusicListDB(SQLiteDatabase musicListDB) {
        this.musicListDB = musicListDB;
    }

    public ArrayList<HealthRecord> getHealthRecordList() {
        return healthRecordList;
    }

    public void setHealthRecordList(ArrayList<HealthRecord> healthRecordList) {
        this.healthRecordList = healthRecordList;
    }
}
