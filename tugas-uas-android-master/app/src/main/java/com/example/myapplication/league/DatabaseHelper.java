package com.example.myapplication.league;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "League.db";
    public static final String TABLE_NAME = "pertandingan";
    private static final int DATABASE_VERSION = 1;

    public static final String COL_1 = "id";
    public static final String COL_2 = "strEvent";
    public static final String COL_3 = "intHomeScore";
    public static final String COL_4 = "intAwayScore";
    public static final String COL_5 = "strThumb";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table pertandingan (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "strEvent TEXT  NULL, " +
                "intHomeScore TEXT  NULL," +
                "intAwayScore TEXT  NULL," +
                "strThumb TEXT NULL" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String strEvent, String intHomeScore, String intAwayScore, String strThumb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,strEvent);
        contentValues.put(COL_3,intHomeScore);
        contentValues.put(COL_4,intAwayScore);
        contentValues.put(COL_5,strThumb);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from pertandingan", null);
        return res;
    }


    public boolean updateData(String id, String strEvent, String intHomeScore, String intAwayScore, String strThumb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,strEvent);
        contentValues.put(COL_3,intHomeScore);
        contentValues.put(COL_4,intAwayScore);
        contentValues.put(COL_5,strThumb);

        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] {id});
        return true;
    }


    public int deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
}
