package com.hr.fitness.ui.health.EditData;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.hr.fitness.Model.Record;
import com.hr.fitness.SQLite.FeedReaderContract;

import java.util.ArrayList;

public class EditDataPresenter {

    SQLiteDatabase listDB;
    ArrayList<Record> recordList;

    public EditDataPresenter(SQLiteDatabase listDB){
        this.listDB = listDB;
        recordList = new ArrayList<>();
    }

    public void delDB(int delId){
//        String delFile = "DELETE FROM '" + FeedReaderContract.TABLE_NAME + "' WHERE '" + FeedReaderContract.id + "' = '" + delId + "'" ;
        String delFile = "DELETE FROM " + FeedReaderContract.BMI_TABLE_NAME + " WHERE " + FeedReaderContract.id + " = " + delId  ;
//        String delFile2 = "DELETE FROM 'bmiRecord' WHERE 'id' = '3'";
        Log.v("777",delFile);
//        Log.v("777",delFile2);
        listDB.execSQL(delFile);
    }


    public void getData() {
        String selectSQL = "SELECT * FROM '" + FeedReaderContract.BMI_TABLE_NAME + "' ORDER BY '" + FeedReaderContract.id + "' ASC";
        Cursor cursor = listDB.rawQuery(selectSQL,null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(FeedReaderContract.id));
            int gender = cursor.getInt(cursor.getColumnIndex(FeedReaderContract.gender));
            String height = cursor.getString(cursor.getColumnIndex(FeedReaderContract.height));
            String weight = cursor.getString(cursor.getColumnIndex(FeedReaderContract.weight));
            String waistline = cursor.getString(cursor.getColumnIndex(FeedReaderContract.waistline));
            String BMI = cursor.getString(cursor.getColumnIndex(FeedReaderContract.BMI));
            String bodyFat = cursor.getString(cursor.getColumnIndex(FeedReaderContract.bodyFat));
            String date = cursor.getString(cursor.getColumnIndex(FeedReaderContract.date));

            Record record = new Record();
            record.setId(id);
            record.setGender(gender);
            record.setHeight(height);
            record.setWeight(weight);
            record.setWaistline(waistline);
            record.setBMI(BMI);
            record.setBodyFat(bodyFat);
            record.setDate(date);
            recordList.add(record);
        }
    }

    public ArrayList<Record> getRecordList() {
        return recordList;
    }


}
