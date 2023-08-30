package com.example.loginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterForRecyclerView extends RecyclerView.Adapter<AdapterForRecyclerView.viewHolder> {

    ArrayList listitems;


    public AdapterForRecyclerView(ArrayList listitems) {
        this.listitems = listitems;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.list_item,null);

        return new AdapterForRecyclerView.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.txtitems.setText((String) listitems.get(position));

    }
    @Override
    public int getItemCount() {
        return listitems.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{


        TextView txtitems;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            txtitems = itemView.findViewById(R.id.txtItems);
        }
    }
}
