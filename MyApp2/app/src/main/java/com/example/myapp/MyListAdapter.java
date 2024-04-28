package com.example.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {


    private listData[] listData;

    public MyListAdapter(listData[] listdata) {

        this.listData = listdata;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater  = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.students_info_list_design,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final listData listData1 = listData[position];
        holder.id.setText(listData1.getStudentId());
        holder.name.setText(listData1.getName());
        holder.surname.setText(listData1.getSurname());
        holder.rollNumber.setText(listData1.getRollNumber());
        holder.className.setText(listData1.getClassName());
        holder.schoolName.setText(listData1.getSchoolName());


    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView id,name,surname,rollNumber,className,schoolName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.id = (TextView) itemView.findViewById(R.id.dataStudentId);
            this.name = (TextView) itemView.findViewById(R.id.dataName);
            this.surname = (TextView) itemView.findViewById(R.id.dataSurname);
            this.rollNumber = (TextView) itemView.findViewById(R.id.dataRollNumber);
            this.className = (TextView) itemView.findViewById(R.id.dataClassName);
            this.schoolName = (TextView) itemView.findViewById(R.id.dataSchoolName);
        }
    }
}



