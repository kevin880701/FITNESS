package com.hr.fitness.ui.health;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import androidx.viewpager.widget.ViewPager;

import com.hr.fitness.Model.Model;
import com.hr.fitness.Model.Record;
import com.hr.fitness.Model.ViewPagerAdapter;
import com.hr.fitness.SQLite.FeedReaderContract;
import com.hr.fitness.ui.health.bmi.bmiLineChartFragment;
import com.hr.fitness.ui.health.bmi.bodyFatLineChartFragment;
import com.hr.fitness.ui.health.bmi.heightLineChartFragment;
import com.hr.fitness.ui.health.bmi.waistLineChartFragment;
import com.hr.fitness.ui.health.bmi.weightLineChartFragment;

import java.util.ArrayList;

public class HealthPresenter {

    ViewPagerAdapter pagerAdapter;
    Model model;
    ArrayList<Record> recordList;
    SQLiteDatabase healthListDB;

    public HealthPresenter(SQLiteDatabase healthListDB, Model model){
        this.model = model;
        this.healthListDB = healthListDB;
        recordList = new ArrayList<>();
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
        String createTable = "CREATE TABLE IF NOT EXISTS '" + FeedReaderContract.BMI_TABLE_NAME + "'( " +
                FeedReaderContract.id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FeedReaderContract.gender  + " INTEGER, " +
                FeedReaderContract.height  + " VARCHAR(250), " +
                FeedReaderContract.weight  + " VARCHAR(250), " +
                FeedReaderContract.waistline + " VARCHAR(250)," +
                FeedReaderContract.BMI + " VARCHAR(250)," +
                FeedReaderContract.bodyFat + " VARCHAR(250)," +
                FeedReaderContract.date + " VARCHAR(250)" +
                ");";
        healthListDB.execSQL(createTable);
    }

    public void getData() {
        String selectSQL = "SELECT * FROM '" + FeedReaderContract.BMI_TABLE_NAME + "' ORDER BY '" + FeedReaderContract.id + "' ASC";
        Cursor cursor = healthListDB.rawQuery(selectSQL,null);
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
        model.setRecordList(recordList);
    }

    public void setPagerAdapter(ViewPagerAdapter pagerAdapter){
        this.pagerAdapter = pagerAdapter;
    }

    public ArrayList<Record> getRecordList() {
        return recordList;
    }
}
