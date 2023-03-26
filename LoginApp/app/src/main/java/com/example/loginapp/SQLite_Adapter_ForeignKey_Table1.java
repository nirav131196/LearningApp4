package com.example.loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SQLite_Adapter_ForeignKey_Table1 extends RecyclerView.Adapter<SQLite_Adapter_ForeignKey_Table1.MyEmpViewHolder>
{

    List<SQLiteEmpData> data;
    SQLiteEmpData model;

    public SQLite_Adapter_ForeignKey_Table1(List<SQLiteEmpData> data)
    {
        this.data = data;
    }
    @NonNull
    @Override
    public MyEmpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.sqlite_views_table1,parent,false);
        return new MyEmpViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull MyEmpViewHolder holder, int position) {

        model = data.get(position);
        holder.txtid.setText(model.getId());
        holder.txtmobileno.setText(model.getMobileno());
        holder.txtgroupid.setText(model.getGroupid());
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MyEmpViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtid,txtmobileno,txtgroupid;

        public MyEmpViewHolder(@NonNull View itemView) {
            super(itemView);

            txtid= (TextView) itemView.findViewById(R.id.txtid);
            txtmobileno= (TextView) itemView.findViewById(R.id.txtmobileno);
            txtgroupid= (TextView) itemView.findViewById(R.id.txtgroupid);
        }
    }
}