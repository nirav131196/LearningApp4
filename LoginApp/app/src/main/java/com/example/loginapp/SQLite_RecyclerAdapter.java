package com.example.loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SQLite_RecyclerAdapter extends RecyclerView.Adapter<SQLite_RecyclerAdapter.EmpViewHolder> {

        List<SQLiteEmployeeData> data;
        SQLiteEmployeeData model;

        public SQLite_RecyclerAdapter(List<SQLiteEmployeeData> data)
        {
            this.data =data;
        }
    @Override
    public SQLite_RecyclerAdapter.EmpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.sqlite_select_data_views,parent,false);
        return new EmpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SQLite_RecyclerAdapter.EmpViewHolder holder, int position) {

        model = data.get(position);
        holder.id.setText(model.getId());
        holder.name.setText(model.getName());
        holder.post.setText(model.getDesignation());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class EmpViewHolder extends RecyclerView.ViewHolder
    {

        TextView id,name,post;

        public EmpViewHolder(@NonNull View itemView) {
            super(itemView);

            id= (TextView) itemView.findViewById(R.id.txtEmpID);
            name=(TextView) itemView.findViewById(R.id.txtEmpName);
            post=(TextView) itemView.findViewById(R.id.txtEmpDesignation);
        }
    }


}
