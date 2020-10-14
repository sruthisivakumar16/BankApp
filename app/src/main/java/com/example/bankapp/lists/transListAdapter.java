package com.example.bankapp.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bankapp.R;

import java.util.ArrayList;


public class transListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Transaction_Items> list;

    public transListAdapter(Context context, int layout, ArrayList<Transaction_Items> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    private class ViewHolder{
        TextView transaction_id,sender_name,sender_accountno,reciever_name,reciever_accountno,transferred_amount,recieved_amount;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder= new ViewHolder();
        if (row==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);

            holder.transaction_id=(TextView)row.findViewById(R.id.transaction_id);
            holder.sender_name=(TextView)row.findViewById(R.id.sender_name);
            holder.sender_accountno=(TextView)row.findViewById(R.id.sender_accountno);
            holder.reciever_name=(TextView)row.findViewById(R.id.reciever_name);
            holder.reciever_accountno=(TextView)row.findViewById(R.id.reciever_accountno);
            holder.transferred_amount=(TextView)row.findViewById(R.id.transferred_amount);
            holder.recieved_amount=(TextView)row.findViewById(R.id.recieved_amount);

            row.setTag(holder);
        }
        else {
            holder=(ViewHolder)row.getTag();
        }

        Transaction_Items item=list.get(pos);
        holder.transaction_id.setText(item.getTransaction_id());
        holder.sender_name.setText(item.getSender_name());
        holder.sender_accountno.setText(item.getSender_accountno());
        holder.reciever_name.setText(item.getReciever_name());
        holder.reciever_accountno.setText(item.getReciever_accountno());
        holder.transferred_amount.setText(item.getTransferred_amount());
        holder.recieved_amount.setText(item.getTransferred_amount());

        return row;
    }
}
