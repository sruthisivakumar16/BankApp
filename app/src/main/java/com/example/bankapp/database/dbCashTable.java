package com.example.bankapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class dbCashTable extends SQLiteOpenHelper {
    private static final String TAG="DatabaseHelper";
    private static final String TABLE_NAME="credit_table";
    private static final String COL0="ID";
    private static final String COL1="name";
    private static final String COL2="email";
    private static final String COL3 ="accountno";
    private static final String COL4 ="credits";

    // Create table
    public dbCashTable(Context context) {
        super(context, TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE credit_table(ID INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,email VARCHAR,accountno VARCHAR,credits INT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS credit_table");
        onCreate(db);
    }

    // get user's data from the table
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query= "SELECT * FROM credit_table";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    // get ID of user
    public Cursor getDataid() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id FROM credit_table";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    // Adding user data in the table
    public boolean addData(String name, String email, String accountno, String credits){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL1,name);
        contentValues.put(COL2,email);
        contentValues.put(COL3,accountno);
        contentValues.put(COL4,credits);
        long result;
        result = db.insert(TABLE_NAME,null,contentValues);

        if (result==-1){
            return false;
        } else {
            return true;
        }
    }

    // getting reciever data using reciever account no
    public Cursor get_reciever_credits(String Reciever_accountno){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("select * from credit_table where accountno='"+Reciever_accountno+"'",null);
        return data;
    }

    // update user information
    public boolean update_user(String id, String name, String email, String accountno, String credits){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL0,id);
        contentValues.put(COL1,name);
        contentValues.put(COL2,email);
        contentValues.put(COL3,accountno);
        contentValues.put(COL4,credits);
        db.update(TABLE_NAME, contentValues, "ID=?",new String[] {id});
        return true;
    }

    // update sender credits information
    public boolean update_senders_credit(String id, String newcredits){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL0,id);
        contentValues.put(COL4,newcredits);
        db.update(TABLE_NAME, contentValues, "ID=?",new String[] {id});
        return true;
    }

    // update reciever credits information
    public boolean update_recievers_credit(String id, String new_reciever_credits){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL0,id);
        contentValues.put(COL4,new_reciever_credits);
        db.update(TABLE_NAME, contentValues, "ID=?",new String[] {id});
        return true;
    }

    // delete user
    public void deletedata(int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql= "DELETE FROM credit_table WHERE id=?";
        SQLiteStatement st=db.compileStatement(sql);
        st.clearBindings();
        st.bindDouble(1,(double)id);
        st.execute();
        db.close();
    }
}
