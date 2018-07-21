package ru.stopgame.artem.stopgame.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;


public class SettingDataBase extends SQLiteOpenHelper {

    private Gson gson = new Gson();
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MENU = "menu";
    private static final String DATABASE_TABLE = "menu_table_setting";

    private static final String DATABASE_CREATE = "create table "
            + DATABASE_TABLE + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_MENU
            + " text not null);";

    public SettingDataBase(Context context) {
        super(context, "DBsetting", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
}
