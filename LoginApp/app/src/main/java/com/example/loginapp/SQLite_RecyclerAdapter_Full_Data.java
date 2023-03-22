package com.example.loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SQLite_RecyclerAdapter_Full_Data extends RecyclerView.Adapter<SQLite_RecyclerAdapter_Full_Data.EmpFullViewHolder> {

    List<SQLiteEmployeeData_All> alldata;
    SQLiteEmployeeData_All model;


    public SQLite_RecyclerAdapter_Full_Data(List<SQLiteEmployeeData_All> alldata)
    {
            this.alldata = alldata;
    }
    @NonNull
    @Override
    public SQLite_RecyclerAdapter_Full_Data.EmpFullViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.sqlite_select_data_all_views,parent,false);
        return new SQLite_RecyclerAdapter_Full_Data.EmpFullViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SQLite_RecyclerAdapter_Full_Data.EmpFullViewHolder holder, int position) {

            model = alldata.get(position);
            holder.txtId.setText(model.getId());
            holder.txtName.setText(model.getName());
            holder.txtSurname.setText(model.getSurname());
            holder.txtDesignation.setText(model.getDesignation());
            holder.txtDob.setText(model.getDob());
            holder.txtJoiningDate.setText(model.getJoindate());
            holder.txtSalary.setText(model.getSalary());
            holder.txtaddress.setText(model.getAddress());
            holder.txtCity.setText(model.getCity());
    }
    @Override
    public int getItemCount() {
        return alldata.size();
    }

    public class EmpFullViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtId,txtName,txtSurname,txtDesignation,txtDob,txtJoiningDate,txtSalary,txtaddress,txtCity;

        public EmpFullViewHolder(@NonNull View itemView) {
            super(itemView);

            txtId =(TextView)itemView.findViewById(R.id.txtEmpID);
            txtName =(TextView)itemView.findViewById(R.id.txtEmpName);
            txtSurname =(TextView)itemView.findViewById(R.id.txtEmpSurname);
            txtDesignation =(TextView)itemView.findViewById(R.id.txtEmpDesignation);
            txtDob =(TextView)itemView.findViewById(R.id.txtEmpDOB);
            txtJoiningDate =(TextView)itemView.findViewById(R.id.txtEmpJoiningDate);
            txtSalary =(TextView)itemView.findViewById(R.id.txtEmpSalary);
            txtaddress =(TextView)itemView.findViewById(R.id.txtEmpAddress);
            txtCity =(TextView)itemView.findViewById(R.id.txtEmpCity);
        }
    }
}
