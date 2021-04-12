package com.missouristate.penrose.friendlistex2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "friendListDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FRIENDS = "friends";
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL_ADDRESS = "email_address";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // build SQL create statement
        String sqlQuery = "create table " + TABLE_FRIENDS + "( ";
        sqlQuery += ID + " integer primary key autoincrement, ";
        sqlQuery += FIRST_NAME + " text, " + LAST_NAME + " text, " + EMAIL_ADDRESS + " text )";

        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop the old table if it exists
        String sqlQuery = "drop table if exists " + TABLE_FRIENDS;
        db.execSQL(sqlQuery);

        // re-create the table
        onCreate(db);
    }

    public void insert(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "insert into " + TABLE_FRIENDS;
        sqlQuery += " values( null, '"
                + person.getFirstName() + "', '" + person.getLastName() + "', '"
                + person.getEmailAddress() + "' )";

        db.execSQL(sqlQuery);
        db.close();
    }

    public ArrayList<Person> selectAll() {
        String sqlQuery = "select * from " + TABLE_FRIENDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Person> friends = new ArrayList<>();
        while (cursor.moveToNext()) {
            Person friend =
                    new Person(
                            Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3));
            friends.add(friend);
        }

        db.close();
        return friends;
    }

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "delete from " + TABLE_FRIENDS;
        sqlQuery += " where " + ID + " = " + id;

        db.execSQL(sqlQuery);
        db.close();
    }

    public void updateById(int id, String firstName, String lastName, String emailAddress) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "update " + TABLE_FRIENDS;
        sqlQuery += " set ";
        sqlQuery += FIRST_NAME + " = '" + firstName + "', ";
        sqlQuery += LAST_NAME + " = '" + lastName + "', ";
        sqlQuery += EMAIL_ADDRESS + " = '" + emailAddress + "'";
        sqlQuery += " where " + ID + " = " + id;

        db.execSQL(sqlQuery);
        db.close();
    }
}