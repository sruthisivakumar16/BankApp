package com.example.bankapp.UserOperation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankapp.MainActivity;
import com.example.bankapp.database.dbCashTable;
import com.example.bankapp.R;


public class addUser extends AppCompatActivity{

    dbCashTable mdatabase_credit_table;
    private Button btnadd;
    private EditText et_name,et_email,et_accountno,et_credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        et_name=(EditText)findViewById(R.id.et_name);
        et_email=(EditText)findViewById(R.id.et_email);
        et_accountno=(EditText) findViewById(R.id.et_accountno);
        et_credit=(EditText) findViewById(R.id.et_cash);
        btnadd=(Button)findViewById(R.id.btnadd);
        mdatabase_credit_table= new dbCashTable(this);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedatainDB();
            }
        });
    }

    // Adding new User
    public void AddData(String name, String email, String accountno, String credits){
        boolean insertData = mdatabase_credit_table.addData(name, email, accountno, credits);
        if (insertData){
            Intent intent= new Intent(addUser.this, MainActivity.class);
            startActivity(intent);
            toastmessage("Data successfully inserted!");
        }
        else {
            toastmessage("something wrong");
        }
    }

    private void toastmessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }

    boolean savedatainDB() {

                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String accountno = et_accountno.getText().toString();
                String credits = et_credit.getText().toString();

        if (name.length() != 0 && email.length() != 0 && accountno.length() != 0 ) {
                    AddData(name, email, accountno,credits);
                    et_name.setText("");
                    et_email.setText("");
                    et_accountno.setText("");
                    et_credit.setText("");

                } else {
                    toastmessage("fill details in every fields");
                }

        return true;
    }

}
