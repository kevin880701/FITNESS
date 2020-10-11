package com.hr.fitness.ui.health.EnterData;

import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;

import com.hr.fitness.Model.HealthRecord;
import com.hr.fitness.SQLite.BmiFormat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EnterDataPresenter {

    SQLiteDatabase musicListDB;
    ArrayList<HealthRecord> healthRecordList;

    public EnterDataPresenter(SQLiteDatabase musicListDB){
        this.musicListDB = musicListDB;
        healthRecordList = new ArrayList();
    }


    public void insertDB(int gender,String height,String weight,String waistline){
        CharSequence date;
        DecimalFormat df = new DecimalFormat("##0.00");
        String bodyFat = "";
        String BMI = df.format(Double.valueOf(weight)/Double.valueOf(height)/Double.valueOf(height)*10000);
        if(gender == 0){
            bodyFat =df.format(Integer.valueOf(waistline)*0.74-Integer.valueOf(weight)*0.082-34.89/Integer.valueOf(height)*100);
        }else if(gender == 1){
            bodyFat = df.format(Integer.valueOf(waistline)*0.74-Integer.valueOf(weight)*0.082-44.74/Integer.valueOf(height)*100);
        }

        Calendar mCal = Calendar.getInstance();
        date = DateFormat.format("yyyy/MM/dd hh:mm;ss", mCal.getTime());
        String sql = "INSERT into '" + BmiFormat.BMI_TABLE_NAME + "' ( '"  + BmiFormat.gender
                + "','" + BmiFormat.height
                + "','" + BmiFormat.weight
                + "','" + BmiFormat.waistline
                + "','" + BmiFormat.BMI
                + "','" + BmiFormat.bodyFat
                + "','" + BmiFormat.date + "' ) " +
                "VALUES (?,?,?,?,?,?,?)";
        Object[] mValue = new Object[]{gender,height,weight,waistline,BMI,bodyFat,date.toString()};

        musicListDB.execSQL(sql,mValue);
    }
}
