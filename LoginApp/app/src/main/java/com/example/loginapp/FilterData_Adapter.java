package com.example.loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.mozilla.javascript.Context;

import java.util.ArrayList;

public class FilterData_Adapter extends  RecyclerView.Adapter{


    ArrayList<FilterTypeModel> filterTypeModelArrayList;


    public FilterData_Adapter(ArrayList<FilterTypeModel> filterTypeModelArrayList) {

        this.filterTypeModelArrayList = filterTypeModelArrayList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.filter_data_item,null);
        return new FilterData_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder =(ViewHolder) holder;
        FilterTypeModel filtertypemodel =filterTypeModelArrayList.get(position);

        viewHolder.chkType.setText(filtertypemodel.getName());
    }

    @Override
    public int getItemCount()
    {
        return filterTypeModelArrayList.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox chkType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            chkType = itemView.findViewById(R.id.checkBox);
        }
    }
}
