package com.example.bankapp.UserOperation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bankapp.MainActivity;
import com.example.bankapp.database.dbCashTable;
import com.example.bankapp.R;

public class updateUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    dbCashTable mdatabase_credit_table;
    private Button btnupdate;
    private EditText et_name,et_email,et_accountno,et_credit;
    private int selected_id;
    private String selected_name,selected_email,selected_accountno,selected_credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user);

        et_name=(EditText) findViewById(R.id.et_name);
        et_email=(EditText) findViewById(R.id.et_email);
        et_accountno=(EditText) findViewById(R.id.et_accountno);
        et_credit=(EditText) findViewById(R.id.et_cash);

        mdatabase_credit_table=new dbCashTable(this);

        Intent recievedIntent= getIntent();
        selected_id=recievedIntent.getIntExtra("id",-1);
        selected_name=recievedIntent.getStringExtra("name");
        selected_email=recievedIntent.getStringExtra("email");
        selected_accountno=recievedIntent.getStringExtra("accountno");
        selected_credits=recievedIntent.getStringExtra("credits");

        et_name.setText(selected_name);
        et_email.setText(selected_email);
        et_accountno.setText(selected_accountno);
        et_credit.setText(selected_credits);

        btnupdate=(Button)findViewById(R.id.btnadd);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modify_user();
            }
        });

    }

    // Update user info
    boolean modify_user() {

        String name=et_name.getText().toString().trim();
        String email=et_email.getText().toString().trim();
        String accountno=et_accountno.getText().toString().trim();
        String credits=et_credit.getText().toString().trim();
        String user_id= Integer.toString(selected_id);

        if (!name.equals("") && !email.equals("") && !accountno.equals("") && !credits.equals(""))
        {
            boolean isUpdated = mdatabase_credit_table.update_user(user_id, name, email, accountno, credits);
            if(isUpdated == true)
            {
                et_name.setText("");
                et_email.setText("");
                et_accountno.setText("");
                et_credit.setText("");
                Toast.makeText(getApplicationContext(),"updated successfully",Toast.LENGTH_LONG).show();
                Intent intent= new Intent(updateUser.this, MainActivity.class);
                startActivity(intent);
            }
            else
            {  Toast.makeText(getApplicationContext(),"errrrrrrreorr",Toast.LENGTH_LONG).show(); }

        }

        else { Toast.makeText(getApplicationContext(),"Fill all the fields",Toast.LENGTH_LONG).show(); }

        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        String text = adapterView.getItemAtPosition(pos).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}