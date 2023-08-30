package com.example.loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterFile extends RecyclerView.Adapter<MyAdapterFile.MyviewHolder> {


    ArrayList<MyDataModel> list;
    MyDataModel data;

    public MyAdapterFile(ArrayList<MyDataModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder_sqlite,parent,false);
        return new MyAdapterFile.MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        data = list.get(position);
        holder.txtId.setText(data.getId());
        holder.txtName.setText(data.getName());
        holder.txtSurname.setText(data.getSurname());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class MyviewHolder extends  RecyclerView.ViewHolder{

        TextView txtId,txtName,txtSurname;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            txtId = itemView.findViewById(R.id.txtId);
            txtName =itemView.findViewById(R.id.txtName);
            txtSurname =itemView.findViewById(R.id.txtSurname);
        }
    }
}

