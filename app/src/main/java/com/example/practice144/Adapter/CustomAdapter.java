package com.example.practice144.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.practice144.Models.Contact;
import com.example.practice144.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Contact> data = new ArrayList<>();
    ArrayList<Contact> tmpData = new ArrayList<>();
    LayoutInflater inflater = null;

    public CustomAdapter(Context context, ArrayList<Contact> data) {
        this.context = context;
        this.data = data;
        this.tmpData = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{
        public TextView txtName;
        public TextView txtPhone;
        public TextView txtEmail;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        ViewHolder holder;

        if (convertView == null) {
            view = inflater.inflate(R.layout.items, null);
            holder = new ViewHolder();

            holder.txtName = view.findViewById(R.id.rowItemTxtName);
            holder.txtPhone = view.findViewById(R.id.rowItemTxtPhone);
            holder.txtEmail = view.findViewById(R.id.rowItemTxtEmail);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        if (data.size() != 0){
            holder.txtName.setText(data.get(position).getName());
            holder.txtPhone.setText(data.get(position).getPhone());
            holder.txtEmail.setText(data.get(position).getEmail());
        }

        return view;
    }
}
