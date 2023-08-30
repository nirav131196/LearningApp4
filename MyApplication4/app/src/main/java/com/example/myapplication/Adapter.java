package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends  RecyclerView.Adapter<Adapter.viewHolder>{


    ArrayList<MyModelFile> list;
    MyModelFile data;


    @NonNull
    @Override
    public Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_view_home,null);
        return new Adapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viewHolder holder, int position) {


        data = list.get(position);

        holder.txtid.setText(data.getId());
        holder.txtname.setText(data.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder{


        TextView txtid,txtname;

        public viewHolder(@NonNull View itemView) {
            super(itemView);


            txtid = itemView.findViewById(R.id.txtId);
            txtname  = itemView.findViewById(R.id.txtName);


        }
    }
}
