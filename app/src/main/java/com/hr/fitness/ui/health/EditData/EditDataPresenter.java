package com.hr.fitness.ui.health.EditData;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.hr.fitness.Model.HealthRecord;
import com.hr.fitness.SQLite.BmiFormat;

import java.util.ArrayList;

public class EditDataPresenter {

    SQLiteDatabase listDB;
    ArrayList<HealthRecord> healthRecordList;

    public EditDataPresenter(SQLiteDatabase listDB){
        this.listDB = listDB;
        healthRecordList = new ArrayList<>();
    }

    public void delDB(int delId){
//        String delFile = "DELETE FROM '" + FeedReaderContract.TABLE_NAME + "' WHERE '" + FeedReaderContract.id + "' = '" + delId + "'" ;
        String delFile = "DELETE FROM " + BmiFormat.BMI_TABLE_NAME + " WHERE " + BmiFormat.id + " = " + delId  ;
//        String delFile2 = "DELETE FROM 'bmiRecord' WHERE 'id' = '3'";
        Log.v("777",delFile);
//        Log.v("777",delFile2);
        listDB.execSQL(delFile);
    }


    public void getData() {
        String selectSQL = "SELECT * FROM '" + BmiFormat.BMI_TABLE_NAME + "' ORDER BY '" + BmiFormat.id + "' ASC";
        Cursor cursor = listDB.rawQuery(selectSQL,null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(BmiFormat.id));
            int gender = cursor.getInt(cursor.getColumnIndex(BmiFormat.gender));
            String height = cursor.getString(cursor.getColumnIndex(BmiFormat.height));
            String weight = cursor.getString(cursor.getColumnIndex(BmiFormat.weight));
            String waistline = cursor.getString(cursor.getColumnIndex(BmiFormat.waistline));
            String BMI = cursor.getString(cursor.getColumnIndex(BmiFormat.BMI));
            String bodyFat = cursor.getString(cursor.getColumnIndex(BmiFormat.bodyFat));
            String date = cursor.getString(cursor.getColumnIndex(BmiFormat.date));

            HealthRecord healthRecord = new HealthRecord();
            healthRecord.setId(id);
            healthRecord.setGender(gender);
            healthRecord.setHeight(height);
            healthRecord.setWeight(weight);
            healthRecord.setWaistline(waistline);
            healthRecord.setBMI(BMI);
            healthRecord.setBodyFat(bodyFat);
            healthRecord.setDate(date);
            healthRecordList.add(healthRecord);
        }
    }

    public ArrayList<HealthRecord> getHealthRecordList() {
        return healthRecordList;
    }


}
