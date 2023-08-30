package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterHome extends RecyclerView.Adapter<MyAdapterHome.viewHolder> {


    ArrayList<MyModelFile> list;
    MyModelFile data;

    public MyAdapterHome(ArrayList<MyModelFile> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapterHome.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_view_home,parent,false);
        return new MyAdapterHome.viewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {


        data = list.get(position);

        holder.txtId.setText(data.getId());
        holder.txtName.setText(data.getName());
        holder.txtemail.setText(data.getEmail());
        holder.txtgender.setText(data.getGender());
        holder.txtdob.setText(data.getDob());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class viewHolder extends  RecyclerView.ViewHolder{

        TextView txtId,txtName,txtemail,txtgender,txtdob;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtName = itemView.findViewById(R.id.txtName);
            txtemail = itemView.findViewById(R.id.txtEmail);
            txtgender = itemView.findViewById(R.id.txtGender);
            txtdob = itemView.findViewById(R.id.txtDOB);
        }
    }
}
