package com.hr.fitness.ui.history.HistoryEdit;

import android.database.sqlite.SQLiteDatabase;
import android.text.InputType;
import android.text.format.DateFormat;
import android.text.method.NumberKeyListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hr.fitness.R;
import com.hr.fitness.SQLite.BmiFormat;
import com.hr.fitness.SQLite.HistoryFormat;
import com.hr.fitness.SQLite.SQLiteDBHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HistoryEditPresenter {

    HistoryEditActivity activity;
    SQLiteDatabase historyLiteDB;
    ArrayList<String> itemIdList;
    ArrayList<String> itemNameList;
    ArrayList<String> itemContentList;
    String itemIdString = "";
    String itemNameString = "";
    String itemContentString = "";

    public HistoryEditPresenter(HistoryEditActivity historyEditActivity,SQLiteDatabase historyLiteDB){
        activity = historyEditActivity;
        this.historyLiteDB = historyLiteDB;
        itemIdList = new ArrayList<>();
        itemNameList = new ArrayList<>();
        itemContentList = new ArrayList<>();
    }

    public void insertDB(String date){
        if(activity.layout1.getChildCount() !=0){
            for(int i=0;i<activity.layout1.getChildCount();i++){
                itemIdString = itemIdString + (activity.layout1.getChildAt(i).getId() - 1000) + ",";
                TextView text = activity.findViewById(activity.layout1.getChildAt(i).getId() + 2000);
                itemNameString = itemNameString + text.getText() + ",";
                EditText edit = activity.findViewById(activity.layout1.getChildAt(i).getId() + 3000);
                if(edit.getText().toString().equals("")){
                    itemContentString = itemContentString + "z,";
                }else{
                    itemContentString = itemContentString + edit.getText().toString() + ",";
                }
            }

            String sql = "INSERT into '" + HistoryFormat.HISTORY_TABLE_NAME + "' ( '"  + HistoryFormat.date
                    + "','" + HistoryFormat.itemId
                    + "','" + HistoryFormat.itemName
                    + "','" + HistoryFormat.itemContent + "' ) " +
                    "VALUES (?,?,?,?)";
            Object[] mValue = new Object[]{date,itemIdString,itemNameString,itemContentString};

            historyLiteDB.execSQL(sql,mValue);
        }
    }

    public void updateDB(String date){
        for(int i=0;i<activity.layout1.getChildCount();i++){
            itemIdString = itemIdString + (activity.layout1.getChildAt(i).getId() - 1000) + ",";
            TextView text = activity.findViewById(activity.layout1.getChildAt(i).getId() + 2000);
            itemNameString = itemNameString + text.getText() + ",";
            EditText edit = activity.findViewById(activity.layout1.getChildAt(i).getId() + 3000);
            if(edit.getText().toString().equals("")){
                itemContentString = itemContentString + "z,";
            }else{
                itemContentString = itemContentString + edit.getText().toString() + ",";
            }
        }


        String updateSql = "UPDATE " + HistoryFormat.HISTORY_TABLE_NAME  +" SET '" +
                HistoryFormat.itemId + "'='" + itemIdString + "','" +
                HistoryFormat.itemName + "'='" + itemNameString + "','" +
                HistoryFormat.itemContent + "'='"  + itemContentString +
                "' WHERE " + HistoryFormat.date + "='" + date + "'";

        historyLiteDB.execSQL(updateSql);
    }

    public void addItem(String itemName,String itemContent,int itemCount){
        LinearLayout.LayoutParams lineLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout lineLayout = new LinearLayout(activity);
        lineLayout.setOrientation(LinearLayout.HORIZONTAL);
        lineLayout.setLayoutParams(lineLayoutParams);
        lineLayout.setGravity(Gravity.CENTER);
        lineLayout.setId(1000 + itemCount);
        activity.layout1.addView(lineLayout);

        ImageView imgDel = new ImageView(activity);
        TextView textItemName = new TextView(activity);
        EditText editItem = new EditText(activity);
        TextView text = new TextView(activity);

        LinearLayout.LayoutParams imgDelParams = new LinearLayout.LayoutParams(pixels(25),pixels(25));
        imgDel.setImageResource(R.drawable.button_del_item);
        imgDel.setId(2000 + itemCount);
        imgDel.setLayoutParams(imgDelParams);
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout = activity.findViewById(imgDel.getId()-1000);
                activity.layout1.removeView(linearLayout);
            }
        });
        imgDel.setPadding(0,10,15,0);

        textItemName.setId(3000 + itemCount);
        textItemName.setTextSize(25);
        textItemName.setText(itemName);

        LinearLayout.LayoutParams editItemParams = new LinearLayout.LayoutParams(pixels(50),LinearLayout.LayoutParams.WRAP_CONTENT);
        editItem.setLayoutParams(editItemParams);
        editItem.setId(4000 + itemCount);
        editItem.setText(itemContent);
        editItem.setInputType(InputType.TYPE_CLASS_NUMBER);

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(pixels(50),LinearLayout.LayoutParams.WRAP_CONTENT);
        text.setLayoutParams(textParams);
        text.setTextSize(25);
        text.setText("下");

        lineLayout.addView(imgDel);
        lineLayout.addView(textItemName);
        lineLayout.addView(editItem);
        lineLayout.addView(text);

        itemIdList.add(String.valueOf(itemCount));
        itemNameList.add(itemName);
    }

    public int pixels(int a){
        float pixels =  a * activity.getResources().getDisplayMetrics().density;
        return (int)pixels;
    }
}
