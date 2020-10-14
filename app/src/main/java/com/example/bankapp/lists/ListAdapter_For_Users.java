package com.example.bankapp.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bankapp.R;

import java.util.ArrayList;


public class ListAdapter_For_Users extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<userListAdapter> list;

    public ListAdapter_For_Users(Context context, int layout, ArrayList<userListAdapter> list) {
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
        TextView name,email,accountno,credits;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder= new ViewHolder();
        if (row==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);

            holder.name=(TextView)row.findViewById(R.id.name);
            holder.email=(TextView)row.findViewById(R.id.email);
            holder.accountno=(TextView)row.findViewById(R.id.accountno);
            holder.credits=(TextView)row.findViewById(R.id.credits);

            row.setTag(holder);
        }
        else {
            holder=(ViewHolder)row.getTag();
        }

        userListAdapter item=list.get(pos);
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail().toLowerCase());
        holder.accountno.setText(item.getAccountno());
        holder.credits.setText(item.getCredits());

        return row;
    }
}
