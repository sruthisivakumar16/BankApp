package com.example.bankapp.UserOperation;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bankapp.MainActivity;
import com.example.bankapp.database.dbCashTable;
import com.example.bankapp.database.dbTransTable;
import com.example.bankapp.R;


public class clickUser extends AppCompatActivity {
    dbCashTable mdatabase_credit_table;
    dbTransTable mdatabase_transaction_table;

    private TextView sender_name,sender_email,sender_accountno,sender_credits;
    private int selected_id;
    private String selected_name;
    private String selected_email;
    private String selected_accountno,selected_credits;

    private EditText et_rname,et_raccountno,et_amount;
    private Button btn_transfer;


    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.click_user);

        sender_name=(TextView) findViewById(R.id.name);
        sender_email=(TextView) findViewById(R.id.email);
        sender_accountno=(TextView) findViewById(R.id.accountno);
        sender_credits=(TextView) findViewById(R.id.credits);

        et_rname=(EditText) findViewById(R.id.et_rname);
        et_raccountno=(EditText) findViewById(R.id.et_raccountno);
        et_amount=(EditText) findViewById(R.id.et_amount);
        btn_transfer=(Button) findViewById(R.id.btn_transfer);

        mdatabase_credit_table=new dbCashTable(this);
        mdatabase_transaction_table=new dbTransTable(this);

        Intent recievedIntent= getIntent();
        selected_id=recievedIntent.getIntExtra("id",-1);
        selected_name=recievedIntent.getStringExtra("name");
        selected_email=recievedIntent.getStringExtra("email");
        selected_accountno=recievedIntent.getStringExtra("accountno");
        selected_credits=recievedIntent.getStringExtra("credits");

        sender_name.setText(selected_name);
        sender_email.setText(selected_email);
        sender_accountno.setText(selected_accountno);
        sender_credits.setText(selected_credits);

        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Update_data_to_credit_table();
            }
        });

    }

    // Adding user data to transaction table
    public void Add_Data_to_transaction_table(String transactionid, String sender_name, String sender_accountno, String reciever_name, String reciever_accountno,String amount)
    {
        boolean insertData = mdatabase_transaction_table.addData(transactionid, sender_name, sender_accountno, reciever_name, reciever_accountno, amount);
        if (insertData){
            Intent intent= new Intent(clickUser.this, MainActivity.class);
            startActivity(intent);
            toastmessage("Transaction successful!");
        }
        else {
            toastmessage("something went wrong");
        }
    }

    private void toastmessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }

    // Updating user data credits in credit table
    boolean Update_data_to_credit_table()
    {
        String reciever_name = et_rname.getText().toString();
        String reciever_accountno = et_raccountno.getText().toString();
        String amount = et_amount.getText().toString();
        String user_id= Integer.toString(selected_id);
        String transactionid = "TRx"+selected_accountno+"x"+amount;


        if (reciever_name.length() != 0 && reciever_accountno.length() != 0 && amount.length() != 0)
        {
            int sender_credit= Integer.parseInt(selected_credits);
            int amt= Integer.parseInt(amount);
            int final_sender_amount= sender_credit-amt;

            Cursor data = mdatabase_credit_table.get_reciever_credits(reciever_accountno);
            data.moveToNext();

            try
            {
                String reciever_id = data.getString(0);
                String reciever_name_ = data.getString(1);
                String reciever_credits = data.getString(4);

                    if(reciever_name.equals(reciever_name_)) {

                    int reciever_credit = Integer.parseInt(reciever_credits);
                    int final_reciever_amount = reciever_credit + amt;

                    String new_sender_credits = Integer.toString(final_sender_amount);
                    String new_reciever_credits = Integer.toString(final_reciever_amount);

                    boolean isTransferred_sender_credits = mdatabase_credit_table.update_senders_credit(user_id, new_sender_credits);
                    boolean isTransferred_reciever_credits = mdatabase_credit_table.update_recievers_credit(reciever_id, new_reciever_credits);

                    Add_Data_to_transaction_table(transactionid, selected_name, selected_accountno, reciever_name, reciever_accountno, amount);


                    if ((isTransferred_sender_credits = true) && (isTransferred_reciever_credits = true)) {
                        et_rname.setText("");
                        et_raccountno.setText("");
                        et_amount.setText("");
                        Intent intent = new Intent(clickUser.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Transaction successfull", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "something wrong errrrrrrreorr", Toast.LENGTH_LONG).show();
                    }
                } else{Toast.makeText(getApplicationContext(), "Please enter correct name", Toast.LENGTH_LONG).show(); }
            }
            catch (CursorIndexOutOfBoundsException e)
            {
                Toast.makeText(getApplicationContext(),"Please enter correct account no",Toast.LENGTH_LONG).show(); }
            }

        else { Toast.makeText(getApplicationContext(),"Please fill all the details",Toast.LENGTH_LONG).show(); }

        return true;
    }


}
