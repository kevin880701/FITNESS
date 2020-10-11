package com.hr.fitness.ui.health;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import androidx.viewpager.widget.ViewPager;

import com.hr.fitness.Model.Model;
import com.hr.fitness.Model.HealthRecord;
import com.hr.fitness.Model.ViewPagerAdapter;
import com.hr.fitness.SQLite.BmiFormat;
import com.hr.fitness.ui.health.bmi.bmiLineChartFragment;
import com.hr.fitness.ui.health.bodyFat.bodyFatLineChartFragment;
import com.hr.fitness.ui.health.height.heightLineChartFragment;
import com.hr.fitness.ui.health.waist.waistLineChartFragment;
import com.hr.fitness.ui.health.weight.weightLineChartFragment;

import java.util.ArrayList;

public class HealthPresenter {

    ViewPagerAdapter pagerAdapter;
    Model model;
    ArrayList<HealthRecord> healthRecordList;
    SQLiteDatabase healthListDB;

    public HealthPresenter(SQLiteDatabase healthListDB, Model model){
        this.model = model;
        this.healthListDB = healthListDB;
        healthRecordList = new ArrayList<>();
    }

    public void setupViewPager(ViewPager viewPager) {
        pagerAdapter.addFragment(new weightLineChartFragment(model), "體重");
        pagerAdapter.addFragment(new heightLineChartFragment(model), "身高");
        pagerAdapter.addFragment(new waistLineChartFragment(model), "腰圍");
        pagerAdapter.addFragment(new bmiLineChartFragment(model), "BMI");
        pagerAdapter.addFragment(new bodyFatLineChartFragment(model), "體脂率");
        viewPager.setAdapter(pagerAdapter);
    }

    public void createDB(){
        String createTable = "CREATE TABLE IF NOT EXISTS '" + BmiFormat.BMI_TABLE_NAME + "'( " +
                BmiFormat.id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BmiFormat.gender  + " INTEGER, " +
                BmiFormat.height  + " VARCHAR(250), " +
                BmiFormat.weight  + " VARCHAR(250), " +
                BmiFormat.waistline + " VARCHAR(250)," +
                BmiFormat.BMI + " VARCHAR(250)," +
                BmiFormat.bodyFat + " VARCHAR(250)," +
                BmiFormat.date + " VARCHAR(250)" +
                ");";
        healthListDB.execSQL(createTable);
    }

    public void getData() {
        String selectSQL = "SELECT * FROM '" + BmiFormat.BMI_TABLE_NAME + "' ORDER BY '" + BmiFormat.id + "' ASC";
        Cursor cursor = healthListDB.rawQuery(selectSQL,null);
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
        model.setHealthRecordList(healthRecordList);
    }

    public void setPagerAdapter(ViewPagerAdapter pagerAdapter){
        this.pagerAdapter = pagerAdapter;
    }

    public ArrayList<HealthRecord> getHealthRecordList() {
        return healthRecordList;
    }
}
