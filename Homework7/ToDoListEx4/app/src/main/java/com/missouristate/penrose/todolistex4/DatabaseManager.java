package com.missouristate.penrose.todolistex4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "toDoListDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TODO_LIST = "todo_list_v2";
    private static final String ID = "id";
    private static final String ITEM = "item";
    private static final String DEADLINE = "dueDate";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // build SQL create statement
        String sqlQuery = "create table " + TABLE_TODO_LIST + "( ";
        sqlQuery += ID + " integer primary key autoincrement, "
                + ITEM + " text, " + DEADLINE + " text )";

        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop the old table if it exists
        String sqlQuery = "drop table if exists " + TABLE_TODO_LIST;
        db.execSQL(sqlQuery);

        // re-create the table
        onCreate(db);
    }

    public void insert(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "insert into " + TABLE_TODO_LIST;
        sqlQuery += " values( null, '" + item.getItemName() + "', '" + item.getDueDate() + "' )";

        db.execSQL(sqlQuery);
        db.close();
    }

    public ArrayList<Item> selectAll() {
        String sqlQuery = "select * from " + TABLE_TODO_LIST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Item> items = new ArrayList<>();
        while (cursor.moveToNext()) {
            Item item =
                    new Item(
                            Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getString(2));
            items.add(item);
        }

        db.close();
        return items;
    }

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "delete from " + TABLE_TODO_LIST;
        sqlQuery += " where " + ID + " = " + id;

        db.execSQL(sqlQuery);
        db.close();
    }
}