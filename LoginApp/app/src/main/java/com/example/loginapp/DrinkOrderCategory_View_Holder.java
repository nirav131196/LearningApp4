package com.example.loginapp;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DrinkOrderCategory_View_Holder extends RecyclerView.ViewHolder {

    TextView txtSubCategoryName,txtSubCategoryDescription,txtPrice;
    View view;
    ImageView image;
    int count;

    public DrinkOrderCategory_View_Holder(@NonNull View itemView) {
        super(itemView);

        txtSubCategoryName = (TextView)itemView.findViewById(R.id.subcategoryname);
        txtSubCategoryDescription = (TextView)itemView.findViewById(R.id.subcategorydescription);
        txtPrice = (TextView)itemView.findViewById(R.id.subcategoryprice);
        image = (ImageView)itemView.findViewById(R.id.favourite_icon);
        view = itemView;
        count = 0;

        image.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(count == 0)
                {
                    // POST CODE TO ADD ITEAM
                    ++count;
                    image.setImageDrawable(null);
                    image.setBackgroundResource(R.drawable.fullheart);
                }
                else
                {
                    // REMOVE ITEAM CODE
                    count=0;
                    image.setImageDrawable(null);
                    image.setBackgroundResource(R.drawable.favourite_icon);
                }

            }
        });
    }
}
