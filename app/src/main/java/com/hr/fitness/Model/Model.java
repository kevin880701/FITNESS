package com.hr.fitness.Model;


import android.database.sqlite.SQLiteDatabase;


import java.io.Serializable;
import java.util.ArrayList;


public class Model implements Serializable {
    public SQLiteDatabase musicListDB;
//    public DataDisplayActivity dataDisplayActivity;
    public ArrayList<Record> recordList;

    public SQLiteDatabase getMusicListDB() {
        return musicListDB;
    }

    public void setMusicListDB(SQLiteDatabase musicListDB) {
        this.musicListDB = musicListDB;
    }

//    public DataDisplayActivity getDataDisplayActivity() {
//        return dataDisplayActivity;
//    }
//
//    public void setDataDisplayActivity(DataDisplayActivity dataDisplayActivity) {
//        this.dataDisplayActivity = dataDisplayActivity;
//    }

    public ArrayList<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(ArrayList<Record> recordList) {
        this.recordList = recordList;
    }
}
