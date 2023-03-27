package com.example.loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SQLite_Adapter_ForeignKey_Table2 extends RecyclerView.Adapter<SQLite_Adapter_ForeignKey_Table2.MyEmpViewHolder>
{

    List<SQLiteEmpData2> data2;
    SQLiteEmpData2 model2;

    public SQLite_Adapter_ForeignKey_Table2(List<SQLiteEmpData2> data2)
    {
        this.data2 = data2;
    }
    @NonNull
    @Override
    public MyEmpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.sqlite_views_table2,parent,false);
        return new MyEmpViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull MyEmpViewHolder holder, int position) {

        model2 = data2.get(position);

        holder.txtgroupid2.setText(model2.getGroupid2());
        holder.txtmobileno2.setText(model2.getMobileno2());
    }
    @Override
    public int getItemCount() {
        return data2.size();
    }
    public class MyEmpViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtgroupid2,txtmobileno2;

        public MyEmpViewHolder(@NonNull View itemView) {
            super(itemView);

            txtgroupid2= (TextView) itemView.findViewById(R.id.txtgroupid2);
            txtmobileno2= (TextView) itemView.findViewById(R.id.txtmobileno2);

        }
    }
}