package ru.stopgame.artem.stopgame.dataBase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PostsDataBase  extends SQLiteOpenHelper {
    public PostsDataBase(Context context) {
        super(context, "DBPosts", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}