package com.example.bankapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;

import com.example.bankapp.database.dbTransTable;
import com.example.bankapp.lists.Transaction_Items;
import com.example.bankapp.R;
import com.example.bankapp.lists.transListAdapter;


import java.util.ArrayList;

public class transLog extends AppCompatActivity {
    GridView gridView;
    ArrayList<Transaction_Items> list;
    transListAdapter adapter = null;
    dbTransTable mdatabase_transaction_table;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trans_log);

        gridView = (GridView) findViewById(R.id.gridview_for_transaction);
        list = new ArrayList<Transaction_Items>();
        adapter = new transListAdapter(this, R.layout.trans_list, list);
        gridView.setAdapter(adapter);
        mdatabase_transaction_table = new dbTransTable(this);

        // Show Users info in the list
        Cursor data = mdatabase_transaction_table.getData();
        while (data.moveToNext()) {
            int id = data.getInt(0);
            String transaction_id = data.getString(1);
            String sender_name = data.getString(2);
            String sender_accountno = data.getString(3);
            String reciever_name = data.getString(4);
            String reciever_accountno = data.getString(5);
            String transferred_amount = data.getString(6);

            list.add(new Transaction_Items(id, transaction_id, sender_name, sender_accountno, reciever_name, reciever_accountno, transferred_amount));
        }
        adapter.notifyDataSetChanged();

    }

}