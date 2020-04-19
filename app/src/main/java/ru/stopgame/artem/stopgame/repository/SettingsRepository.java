package ru.stopgame.artem.stopgame.repository;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

//({"checkstyle:Indentation", "checkstyle:CommentsIndentation"})
public class SettingsRepository {
    DBHelper dbHelper;
    Gson gson = new Gson();
//    Type type = new TypeToken<List<TrainingModel>>() {}.getType();

    /*
    * 1-switch notification
    * 2-spinner change notufication
    * 3-spinner style
    * 4-spinner size text post
    * 5-spinner size text news
    * 6-spinner size text head news
    * 7-
    * 8-
    * */

    //({"checkstyle:Indentation", "checkstyle:WhitespaceAround", "checkstyle:MissingJavadocMethod"})
    public SettingsRepository(Context context) {
        dbHelper = new DBHelper(context);
        if (get(3)==null) {
            set("Темный стиль", 3);
            set("14", 4);
            set("14", 5);
            set("14", 6);
        }
    }

    //({"checkstyle:Indentation", "checkstyle:WhitespaceAround", "checkstyle:MissingJavadocMethod"})
    public void set(String name, int numberRep){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("id", String.valueOf(numberRep));
        cv.put("lists", name);
        if (get(numberRep)==null) {
            db.insert("mytable", null, cv);
        }else {
            db.update("mytable", cv, "id = ?",
                    new String[] { String.valueOf(numberRep) });
        }
        db.close();
    }

    //({"checkstyle:Indentation", "checkstyle:CommentsIndentation", "checkstyle:WhitespaceAround", "checkstyle:MissingJavadocMethod"})
    public String get(int numberRep){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("mytable", null, null, null, null, null,null);
        String name = null;
        try {
            String s=null;
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(0).equals(String.valueOf(numberRep))){
                        s = cursor.getString(cursor.getColumnIndex("lists"));
                        break;
                    }
                } while (cursor.moveToNext());
            }
            name=s;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
//        db.close();
        return name;
    }



    //({"checkstyle:Indentation", "checkstyle:AbbreviationAsWordInName"})
    private class DBHelper extends SQLiteOpenHelper {


        public DBHelper(Context context) {
            super(context, "stopgameDB", null, 1);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "lists text);");
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
