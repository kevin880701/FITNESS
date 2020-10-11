package com.hr.fitness.ui.history;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hr.fitness.Model.HistoryRecord;
import com.hr.fitness.Model.Model;
import com.hr.fitness.Model.HealthRecord;
import com.hr.fitness.Model.ViewPagerAdapter;
import com.hr.fitness.SQLite.BmiFormat;
import com.hr.fitness.SQLite.HistoryFormat;

import java.util.ArrayList;

public class HistoryPresenter {

    Model model;
    ArrayList<HistoryRecord> historyRecordList;
    SQLiteDatabase historyLiteDB;
    HistoryFragment historyFragment;
    String[] idArray;
    String[] nameArray;
    String[] contentArray;

    public HistoryPresenter(SQLiteDatabase historyLiteDB, Model model,HistoryFragment historyFragment){
        this.model = model;
        this.historyLiteDB = historyLiteDB;
        this.historyFragment = historyFragment;
        historyRecordList = new ArrayList<>();
    }

    public void createDB(){
        String createTable = "CREATE TABLE IF NOT EXISTS '" + HistoryFormat.HISTORY_TABLE_NAME + "'( " +
                HistoryFormat.id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HistoryFormat.date  + " VARCHAR(50), " +
                HistoryFormat.itemId  + " VARCHAR(250), " +
                HistoryFormat.itemName  + " VARCHAR(250), " +
                HistoryFormat.itemContent + " VARCHAR(250)" +
                ");";
        historyLiteDB.execSQL(createTable);
    }

    public ArrayList getData() {
        String selectSQL = "SELECT * FROM '" + HistoryFormat.HISTORY_TABLE_NAME + "' ORDER BY '" + BmiFormat.date + "' ASC";
        Cursor cursor = historyLiteDB.rawQuery(selectSQL,null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(BmiFormat.id));
            String date = cursor.getString(cursor.getColumnIndex(HistoryFormat.date));
            String itemId = cursor.getString(cursor.getColumnIndex(HistoryFormat.itemId));
            String itemName = cursor.getString(cursor.getColumnIndex(HistoryFormat.itemName));
            String itemContent = cursor.getString(cursor.getColumnIndex(HistoryFormat.itemContent));

            HistoryRecord historyRecord = new HistoryRecord();
            historyRecord.setId(id);
            historyRecord.setDate(date);
            historyRecord.setItemId(itemId);
            historyRecord.setItemName(itemName);
            historyRecord.setItemContent(itemContent);
            historyRecordList.add(historyRecord);
        }
        return historyRecordList;
    }

    public String changeTextDateRecord(String datePosition){
        String item = "";
        idArray = null;
        nameArray = null;
        contentArray = null;
        for(int i=0;i<historyRecordList.size();i++){
            if(historyRecordList.get(i).getDate().equals(datePosition)){
                idArray = historyRecordList.get(i).getItemId().split(",");
                nameArray = historyRecordList.get(i).getItemName().split(",");
                contentArray = historyRecordList.get(i).getItemContent().split(",");
                Log.v("PPP","" + contentArray.length);
                for(int j=0;j<idArray.length;j++){
                    if(contentArray[j] == null){
                        item = item + nameArray[j] + "：" + 0 + "下\n";
                    }else{
                        item = item + nameArray[j] + "：" + contentArray[j] + "下\n";
                    }
                }
                break;
            }
        }
        historyFragment.textDateRecord.setText(item);
        Log.v("PPP","" + item);
        return item;
    }

    public String[] getIdArray() {
        return idArray;
    }

    public String[] getNameArray() {
        return nameArray;
    }

    public String[] getContentArray() {
        return contentArray;
    }
}
