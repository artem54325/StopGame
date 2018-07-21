package ru.stopgame.artem.stopgame.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ru.stopgame.artem.stopgame.models.MenuBaseItem;

import static android.content.ContentValues.TAG;


public class DataBaseService extends SQLiteOpenHelper {

    private Gson gson = new Gson();
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MENU = "menu";
    private static final String DATABASE_TABLE = "menu_table";

    private static final String DATABASE_CREATE = "create table "
            + DATABASE_TABLE + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_MENU
            + " text not null);";

    public DataBaseService(Context context) {
        super(context, "DBService", null, 1);
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

    public void addAllMenu(List<MenuBaseItem> menuList){
        ContentValues cv = null;
        SQLiteDatabase db = this.getWritableDatabase();
        removeAll(db);
        for (MenuBaseItem menuBaseItem:menuList){
            cv = new ContentValues();
            cv.put(COLUMN_MENU, gson.toJson(menuBaseItem));
            db.insert(DATABASE_TABLE, null, cv);
        }
        db.close();
    }
    public List<MenuBaseItem> getAllMenuList(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<MenuBaseItem> menuList = new ArrayList<>();
        Cursor cursor = db.query(DATABASE_TABLE,
                new String[]{COLUMN_ID, COLUMN_MENU}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MenuBaseItem item = cursorToMenuBaseItem(cursor);
            menuList.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return menuList;
    }

    private void removeAll(SQLiteDatabase db){
        db.beginTransaction();
        try {
            // Order of deletions is important when foreign key relationships exist.
            db.delete(DATABASE_TABLE, null, null);
//            db.delete(TABLE_USERS, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to delete all posts and users");
        } finally {
            db.endTransaction();
        }
    }

    private MenuBaseItem cursorToMenuBaseItem(Cursor cursor) {
        MenuBaseItem item = gson.fromJson(cursor.getString(1), MenuBaseItem.class);
        return item;
    }
}
