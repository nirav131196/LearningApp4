package com.example.loginapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class State_Adapter_File extends RecyclerView.Adapter<State_Adapter_File.viewholder>{


    ArrayList<String> statelist;

    public State_Adapter_File(ArrayList<String> statelist) {
        this.statelist = statelist;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view  = inflater.inflate(R.layout.state_item_view,null);
        return new State_Adapter_File.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {


        holder.txtStateItems.setText((String) statelist.get(position));

    }

    @Override
    public int getItemCount() {
        return statelist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        TextView txtStateItems;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            txtStateItems = itemView.findViewById(R.id.txtStateItems);
        }
    }
}
