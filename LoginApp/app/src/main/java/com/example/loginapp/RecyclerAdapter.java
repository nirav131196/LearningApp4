package com.example.loginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<Product_View_Holder>
{

    List<Product_Data> list = Collections.emptyList();
    Context context;
    public RecyclerAdapter(List<Product_Data> list, Context context) {
        this.list = list;
        this.context = context;

    }
    @NonNull
    @Override
    public Product_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);
        View photoview = inflater.inflate(R.layout.product_details,parent,false);

        Product_View_Holder viewHolder = new Product_View_Holder(photoview);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final Product_View_Holder holder, final int position) {

      //  final index = holder.getAdapterPosition();
        holder.id.setText(list.get(position).product_id);
        holder.category_id.setText(list.get(position).category_id);
        holder.department_id.setText(list.get(position).department_id);
        holder.category_name.setText(list.get(position).cat_name);
       /* holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.click(index);
            }
        });*/
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }
}
