package com.example.loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SQLite_RecyclerAdapter extends RecyclerView.Adapter<SQLite_RecyclerAdapter.EmpViewHolder> {

        List<SQLiteEmployeeData> data;
        SQLiteEmployeeData model;
        ItemClickListener itemClickListener;


        public SQLite_RecyclerAdapter(List<SQLiteEmployeeData> data,ItemClickListener itemClickListener)
        {
            this.data =data;
            this.itemClickListener =itemClickListener;
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
        holder.txtid.setText(model.getId());
        holder.txtname.setText(model.getName());
        holder.txtpost.setText(model.getDesignation());
        holder.txtSalary.setText(model.getSalary());
        holder.IVDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                itemClickListener.onDeleteClicked(position);
            }
        });
        holder.IVUpdateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onUpdateClicked(position);
            }
        });
        holder.IVSHOWItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onShowClicked(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class EmpViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtid,txtname,txtpost,txtSalary;
        ImageView IVDeleteItem,IVUpdateItem,IVSHOWItem;

        public EmpViewHolder(@NonNull View itemView) {
            super(itemView);

            txtid= (TextView) itemView.findViewById(R.id.txtEmpID);
            txtname=(TextView) itemView.findViewById(R.id.txtEmpName);
            txtpost=(TextView) itemView.findViewById(R.id.txtEmpDesignation);
            txtSalary=(TextView) itemView.findViewById(R.id.txtEmpSalary);

            IVDeleteItem =itemView.findViewById(R.id.IVDeleteRecord);
            IVUpdateItem =itemView.findViewById(R.id.IVUpdateRecord);
            IVSHOWItem =itemView.findViewById(R.id.IVViewRecord);
        }
    }
    public interface ItemClickListener
    {
        void onDeleteClicked(int position);

        void onUpdateClicked(int position);

        void onShowClicked(int position);
    }
}
