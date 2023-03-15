package com.example.loginapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;

public class Favourite_Iteam_View_Holder extends RecyclerView.ViewHolder {

    TextView txtProductId,txtProductName;
    View view;
    public Favourite_Iteam_View_Holder(@NonNull View itemView) {
        super(itemView);

        txtProductId = (TextView)itemView.findViewById(R.id.favouriteProductId);
        txtProductName = (TextView)itemView.findViewById(R.id.favouriteProductName);
        view = itemView;
    }
}
