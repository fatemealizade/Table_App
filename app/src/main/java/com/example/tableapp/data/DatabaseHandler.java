package com.example.tableapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tableapp.model.Item;
import com.example.tableapp.util.Constants;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private final Context context;

    public DatabaseHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_INFO_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
                + Constants.KEY_NAME + " TEXT,"
                + Constants.KEY_FAMILY + " TEXT,"
                + Constants.KEY_AGE + " INTEGER,"
                + Constants.KEY_PHONE_NUM + " TEXT,"
                + Constants.KEY_DATE + " LONG" + ")";
        sqLiteDatabase.execSQL(CREATE_INFO_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void addItem(Item item) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NAME, item.getItemName());
        values.put(Constants.KEY_FAMILY, item.getItemFamily());
        values.put(Constants.KEY_AGE, item.getItemAge());
        values.put(Constants.KEY_PHONE_NUM, item.getItemPhone());
        values.put(Constants.KEY_DATE, java.lang.System.currentTimeMillis());

        sqLiteDatabase.insert(Constants.TABLE_NAME, null, values);
        Log.d("DBHandler", "Added Item: ");
    }

    public Item getItem(int id) {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID,
                        Constants.TABLE_NAME,
                        Constants.KEY_FAMILY,
                        Constants.KEY_AGE,
                        Constants.KEY_PHONE_NUM,
                        Constants.KEY_DATE},
                Constants.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        Item item = new Item();

        item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
        item.setItemName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME)));
        item.setItemFamily(cursor.getString(cursor.getColumnIndex(Constants.KEY_FAMILY)));
        item.setItemAge(cursor.getInt(cursor.getColumnIndex(Constants.KEY_AGE)));
        item.setItemPhone(cursor.getInt(cursor.getColumnIndex(Constants.KEY_PHONE_NUM)));

        DateFormat dateFormat = DateFormat.getDateInstance();
        String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE))).getTime());

        item.setDateItem(formattedDate);

        return item;


    }

    public List<Item> getAllItems() {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Item> itemList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query(Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID,
                        Constants.KEY_NAME,
                        Constants.KEY_FAMILY,
                        Constants.KEY_AGE,
                        Constants.KEY_PHONE_NUM,
                        Constants.KEY_DATE},
                null, null, null, null, Constants.KEY_DATE + "DECS ");

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(cursor.getInt(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID)))));
                item.setItemName(cursor.getString(cursor.getColumnIndex(Constants.KEY_NAME)));
                item.setItemFamily(cursor.getString(cursor.getColumnIndex(Constants.KEY_FAMILY)));
                item.setItemAge(cursor.getInt(cursor.getColumnIndex(Constants.KEY_AGE)));
                item.setItemPhone(cursor.getInt(cursor.getColumnIndex(Constants.KEY_PHONE_NUM)));

                DateFormat dateFormat = DateFormat.getDateInstance();
                String dateFormatted = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE))).getTime());
                item.setDateItem(dateFormatted);

                itemList.add(item);
            } while (cursor.moveToNext());
        }
        return itemList;

    }

    public void DeleteItem(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(Constants.TABLE_NAME, Constants.KEY_ID + "=?", new String[]{String.valueOf(id),});

        sqLiteDatabase.close();


    }

    public int updateItem(Item item) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constants.KEY_NAME, item.getItemName());
        values.put(Constants.KEY_FAMILY, item.getItemFamily());
        values.put(Constants.KEY_AGE, item.getItemAge());
        values.put(Constants.KEY_PHONE_NUM, item.getItemPhone());
        values.put(Constants.KEY_DATE, java.lang.System.currentTimeMillis());

        return sqLiteDatabase.update(Constants.TABLE_NAME, values, Constants.KEY_ID + "=?", new String[]{String.valueOf(item.getId())});

    }

    public int getItemCount(){

        String CountQuery = " SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(CountQuery, null );
        return cursor.getCount();
    }
}
