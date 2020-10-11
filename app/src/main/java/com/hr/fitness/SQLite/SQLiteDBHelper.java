package com.hr.fitness.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public SQLiteDBHelper(Context context) {super(context, BmiFormat.DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //final String SQL = "CREATE TABLE IF NOT EXISTS " + FeedReaderContract.TABLE_NAME + "( " +
        //        FeedReaderContract.id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        //        FeedReaderContract.folderName + " VARCHAR(50), " +
        //        FeedReaderContract.folderPath + " VARCHAR(50), " +
        //        FeedReaderContract.musicName + " VARCHAR(50)," +
        //        FeedReaderContract.musicPath + " VARCHAR(50)," +
        //        FeedReaderContract.musicUri + " VARCHAR(50)," +
        //        FeedReaderContract.musicLong + " LONG" +
        //        ");";
        //db.execSQL(SQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //final String SQL = "DROP TABLE " + FeedReaderContract.TABLE_NAME;
        //db.execSQL(SQL);
    }
}
