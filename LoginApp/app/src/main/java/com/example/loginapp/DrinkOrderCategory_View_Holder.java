package com.example.loginapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DrinkOrderCategory_View_Holder extends RecyclerView.ViewHolder {

    TextView txtSubCategoryName,txtSubCategoryDescription,txtPrice;
    View view;

    public DrinkOrderCategory_View_Holder(@NonNull View itemView) {
        super(itemView);

        txtSubCategoryName = (TextView)itemView.findViewById(R.id.subcategoryname);
        txtSubCategoryDescription = (TextView)itemView.findViewById(R.id.subcategorydescription);
        txtPrice = (TextView)itemView.findViewById(R.id.subcategoryprice);
        view = itemView;
    }
}
