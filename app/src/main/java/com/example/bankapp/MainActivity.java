package com.example.bankapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bankapp.R;
import com.example.bankapp.database.dbCashTable;
import com.example.bankapp.lists.userListAdapter;
import com.example.bankapp.lists.ListAdapter_For_Users;
import com.example.bankapp.UserOperation.addUser;
import com.example.bankapp.UserOperation.updateUser;
import com.example.bankapp.UserOperation.clickUser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    GridView gridView;
    ArrayList<userListAdapter> list;
    ListAdapter_For_Users adapter = null;
    dbCashTable mdatabase_credit_table;
    ImageButton transaction_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set title for main screen
        this.setTitle("ALL USERS");

        transaction_btn = (ImageButton) findViewById(R.id.transaction_btn);
        gridView = (GridView) findViewById(R.id.gridview_for_users);
        list = new ArrayList<userListAdapter>();
        adapter = new ListAdapter_For_Users(this, R.layout.user_list, list);
        gridView.setAdapter(adapter);
        mdatabase_credit_table = new dbCashTable(this);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addUser.class);
                startActivity(intent);
            }
        });

        transaction_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, transLog.class);
                startActivity(intent);
            }
        });

        // Show Users info in the list
        Cursor data = mdatabase_credit_table.getData();
        while (data.moveToNext()) {
            int id = data.getInt(0);
            String name = data.getString(1);
            String email = data.getString(2);
            String accountno = data.getString(3);
            String credits = data.getString(4);

            list.add(new userListAdapter(id, name, email, accountno, credits));
        }
        adapter.notifyDataSetChanged();

        // View User
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                String email = adapterView.getItemAtPosition(i).toString();
                String accountno = adapterView.getItemAtPosition(i).toString();
                String credits = adapterView.getItemAtPosition(i).toString();

                Cursor data = mdatabase_credit_table.getData();

                int id = 0;

                int Itemid = -1;
                while (data.moveToNext()) {
                    if (i != Itemid) {
                        id = data.getInt(0);
                        name = data.getString(1);
                        email = data.getString(2);
                        accountno = data.getString(3);
                        credits = data.getString(4);
                    } else {
                        break;
                    }
                    Itemid++;
                }
                if (Itemid >= 0) {
                    list.add(new userListAdapter(id, name, email, accountno, credits));
                    Intent viewscreenIntent = new Intent(MainActivity.this, clickUser.class);
                    viewscreenIntent.putExtra("id", id);
                    viewscreenIntent.putExtra("name", name);
                    viewscreenIntent.putExtra("email", email);
                    viewscreenIntent.putExtra("accountno", accountno);
                    viewscreenIntent.putExtra("credits", credits);
                    startActivity(viewscreenIntent);
                    updatelist();
                }

            }
        });

        //Update and Delete User menu
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long l) {

                CharSequence[] items = {"UPDATE","DELETE"};
                AlertDialog.Builder dialog= new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("CHOOSE AN ACTION:");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                // Update User's info
                if (item==0){

                    String name = adapterView.getItemAtPosition(i).toString();
                    String email = adapterView.getItemAtPosition(i).toString();
                    String accountno = adapterView.getItemAtPosition(i).toString();
                    String credits = adapterView.getItemAtPosition(i).toString();

                    Cursor data = mdatabase_credit_table.getData();

                    int id = 0;

                    int Itemid = -1;
                    while (data.moveToNext()) {
                        if (i != Itemid) {
                            id = data.getInt(0);
                            name = data.getString(1);
                            email = data.getString(2);
                            accountno = data.getString(3);
                            credits = data.getString(4);
                        } else { break; }
                        Itemid++;
                    }
                    if (Itemid >= 0) {
                        list.add(new userListAdapter(id, name, email, accountno, credits));
                        Intent viewscreenIntent = new Intent(MainActivity.this, updateUser.class);
                        viewscreenIntent.putExtra("id", id);
                        viewscreenIntent.putExtra("name", name);
                        viewscreenIntent.putExtra("email", email);
                        viewscreenIntent.putExtra("accountno", accountno);
                        viewscreenIntent.putExtra("credits", credits);
                        startActivity(viewscreenIntent);
                    }

                }
                        else {
                            Cursor cursor= mdatabase_credit_table.getDataid();
                            ArrayList<Integer> arrayList=new ArrayList<Integer>();
                            while (cursor.moveToNext()){
                                arrayList.add(cursor.getInt(0));
                            }
                            showDialogDelete(arrayList.get(i));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

    }

    private void showDialogDelete(final int iddel)
    {
        // Delete User
        final AlertDialog.Builder dialogbuilder =new AlertDialog.Builder(MainActivity.this);
        dialogbuilder.setTitle("REMOVE USER");
        dialogbuilder.setMessage("Do you really want to remove this user?");

        dialogbuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialogbuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    mdatabase_credit_table.deletedata(iddel);
                    Toast.makeText(getApplicationContext(), "User removed successfully", Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                }
                updatelist();            }
        });

        dialogbuilder.show();
    }

    private void updatelist()
    {
        // Commit changes
        Cursor data = mdatabase_credit_table.getData();
        list.clear();
        while (data.moveToNext()){
            int id = data.getInt(0);
            String name = data.getString(1);
            String email = data.getString(2);
            String accountno = data.getString(3);
            String credits = data.getString(4);

            list.add(new userListAdapter(id, name, email, accountno, credits));
        }
        adapter.notifyDataSetChanged();
    }


}