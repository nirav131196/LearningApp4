package com.example.loginapp;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class DrinkOrderCategory_RecyclerAdapter extends RecyclerView.Adapter<DrinkOrderCategory_View_Holder>
{
    List<DrinkOrderCategory_Data> list = Collections.emptyList();
    Context context;
    int count=0;

    ItemClickListener itemClickListener;

    public DrinkOrderCategory_RecyclerAdapter(List<DrinkOrderCategory_Data> list, Context context,ItemClickListener itemClickListener) {
        this.list = list;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }
    @NonNull
    @Override
    public DrinkOrderCategory_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);
        View photoview = inflater.inflate(R.layout.drink_order_category_views,parent,false);

        DrinkOrderCategory_View_Holder viewHolder = new DrinkOrderCategory_View_Holder(photoview);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final DrinkOrderCategory_View_Holder holder,int position) {

        holder.txtSubCategoryName.setText(list.get(position).subCategoryName);
        holder.txtSubCategoryDescription.setText(list.get(position).subCategoryDescription);
        holder.txtid.setText(list.get(position).productid);
        holder.txtPrice.setText(list.get(position).price);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    itemClickListener.OnItemClicked(position);
                    holder.image.setImageDrawable(null);
                    holder.image.setBackgroundResource(R.drawable.fullheart);

                    count=0;
                    itemClickListener.OnItemClicked2(position);
                    holder.image.setImageDrawable(null);
                    holder.image.setBackgroundResource(R.drawable.favourite_icon);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public interface ItemClickListener{
        void OnItemClicked(int position);
        void OnItemClicked2(int position);
    }
}
