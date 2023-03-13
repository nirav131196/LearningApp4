package com.example.loginapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Food_Product_View_Holder extends RecyclerView.ViewHolder {

    TextView txtProductCatName;
    View view;

    ImageView IVFoodProductCatName;
    public Food_Product_View_Holder(@NonNull View itemView) {
        super(itemView);

        txtProductCatName =(TextView)itemView.findViewById(R.id.product_category_image);
        IVFoodProductCatName=(ImageView)itemView.findViewById(R.id.food_drink_product_image);

        view  = itemView;
    }
}
