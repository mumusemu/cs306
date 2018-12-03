package com.example.selin.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by Selin on 13.05.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "dbapp4.db";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";

    private static final String ITEM_NAME = "name";
    private static final String ITEM_to_CONTACT = "f_id";


    //SQLiteDatabase.CursorFactory factory;
    SQLiteDatabase db;// = openOrCreateDatabase("contacts", factory, null);

    private static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS contacts " +
            "(_id integer primary key AUTOINCREMENT,  " +
            "name text not null, " +
            "email text not null, " +
            "uname text not null, " +
            "pass text not null)";

    private static final String TABLE_CREATED = "CREATE TABLE IF NOT EXISTS items " +
            "(_id integer primary key AUTOINCREMENT, " +
            "name text not null, " +
            "f_id integer, foreign key (f_id) references contacts(_id))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATED);

        this.db = db;
    }


    public void insertContact(Contact c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();


        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getPass());


        db.insert("contacts", null, values);
        db.close();
    }


    public boolean insertItem(Item item) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ITEM_NAME, item.getItemName());
        values.put(ITEM_to_CONTACT, item.getContact());

        if (db.insert("items", null, values) > 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public void getItemsOfContact(Context context, int cid, ListView lv) {
        final String[] columns = new String[]{"name" , "_id"};
        int[] to = new int[]{R.id.text, R.id.id};

        db = this.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT name,_id FROM items where f_id =?", new String[] {String.valueOf(cid)});

        SimpleCursorAdapter cursorAdapter =
                new SimpleCursorAdapter(context, R.layout.row, c, columns, to, 0);

        lv.setAdapter(cursorAdapter);

        db.close();
    }


    public boolean deleteItem(int id)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (db.delete("items", "_id=?", new String[]{String.valueOf(id)}) > 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }


    public boolean login(String username, String password) {
        db = this.getReadableDatabase();
        String query = "select _id from contacts where uname = ? AND pass = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});


        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            currentUser.id = cursor.getInt(0);
            return true;
        } else {
            return false;
        }

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXIST items";
        String query2 = "DROP TABLE IF EXIST contacts";
        db.execSQL(query2);
        db.execSQL(query);
        this.onCreate(db);
    }


}
