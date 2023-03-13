package com.example.loginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class FoodRecyclerAdapter extends RecyclerView.Adapter<Food_Product_View_Holder>{

    List<FoodProductData> foodlist = Collections.emptyList();
    Context context;

    public FoodRecyclerAdapter(List<FoodProductData> foodlist,Context context) {
        this.foodlist = foodlist;
        this.context = context;
    }

    @NonNull
    @Override
    public Food_Product_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context =parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View foodlayout = inflater.inflate(R.layout.food_drink_views,parent,false);

        Food_Product_View_Holder viewHolder2= new Food_Product_View_Holder(foodlayout);
        return viewHolder2;
    }
    @Override
    public void onBindViewHolder(@NonNull Food_Product_View_Holder holder, int position) {


        holder.txtProductCatName.setText(foodlist.get(position).FoodProductCatName);
        Picasso.get().load(foodlist.get(position).FoodDrinkImage).placeholder(R.drawable.googlelogo).error(R.drawable.facelogo).into(holder.IVFoodProductCatName);
    }
    @Override
    public int getItemCount() {
        return foodlist.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
