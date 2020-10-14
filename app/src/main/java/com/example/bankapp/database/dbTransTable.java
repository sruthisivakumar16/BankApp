package com.example.bankapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbTransTable extends SQLiteOpenHelper {
    private static final String TAG="DatabaseHelper";
    private static final String TABLE_NAME="transaction_table";
    private static final String COL0="ID";
    private static final String COL1="transaction_id";
    private static final String COL2="sender_name";
    private static final String COL3="sender_account_no";
    private static final String COL4="reciever_name";
    private static final String COL5 ="reciever_account_no";
    private static final String COL6 ="transferred_amount";

    // Create table
    public dbTransTable(Context context) {
        super(context, TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE transaction_table(ID INTEGER PRIMARY KEY AUTOINCREMENT,transaction_id VARCHAR,sender_name VARCHAR,sender_account_no VARCHAR,reciever_name VARCHAR,reciever_account_no VARCHAR,transferred_amount INT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS transaction_table");
        onCreate(db);
    }

    // get transaction data from the table
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query= "SELECT * FROM transaction_table";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    // get ID of transaction
    public Cursor getDataid() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id FROM transaction_table";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    // Adding transaction data into the table
    public boolean addData(String transaction_id,String sender_name, String sender_account_no,String reciever_name, String reciever_account_no, String transferred_amount){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1,transaction_id);
        contentValues.put(COL2,sender_name);
        contentValues.put(COL3,sender_account_no);
        contentValues.put(COL4,reciever_name);
        contentValues.put(COL5,reciever_account_no);
        contentValues.put(COL6,transferred_amount);
        long result;
        result = db.insert(TABLE_NAME,null,contentValues);

        if (result==-1){
            return false;
        } else {
            return true;
        }
    }
}
