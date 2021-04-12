package com.missouristate.penrose.candystore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "candyStoreDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CANDY = "candy";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRICE = "price";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // build SQL create statement

        String sqlQuery = "create table " + TABLE_CANDY + "( " + ID;
        sqlQuery += " integer primary key autoincrement, " + NAME;
        sqlQuery += " text, " + PRICE + " real )";

        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop the old table if it exists
        String sqlQuery = "drop table if exists " + TABLE_CANDY;
        db.execSQL(sqlQuery);

        // re-create the table
        onCreate(db);
    }

    public void insert(Candy candy) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "insert into " + TABLE_CANDY;
        sqlQuery += " values( null, '" + candy.getName();
        sqlQuery += "', '" + candy.getPrice() + "' )";

        db.execSQL(sqlQuery);
        db.close();
    }

    public ArrayList<Candy> selectAll() {
        String sqlQuery = "select * from " + TABLE_CANDY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Candy> candies = new ArrayList<>();
        while (cursor.moveToNext()) {
            Candy currentCandy =
                    new Candy(
                            Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getDouble(2));
            candies.add(currentCandy);
        }

        db.close();
        return candies;
    }

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "delete from " + TABLE_CANDY;
        sqlQuery += " where " + ID + " = " + id;

        db.execSQL(sqlQuery);
        db.close();
    }

    public void updateById(int id, String name, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "update " + TABLE_CANDY;
        sqlQuery += " set " + NAME + " = '" + name + "', ";
        sqlQuery += PRICE + " = '" + price + "'";
        sqlQuery += " where " + ID + " = " + id;

        db.execSQL(sqlQuery);
        db.close();
    }
}
