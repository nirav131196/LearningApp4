package com.example.loginapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;

import org.w3c.dom.Text;

public class Product_View_Holder extends RecyclerView.ViewHolder {

    TextView id,category_id,department_id,category_name;
    View view;
    CardView cardView;

    ImageView photo;

    public Product_View_Holder(@NonNull View itemView) {
        super(itemView);

       /* id = (TextView)itemView.findViewById(R.id.product_id);

        department_id = (TextView)itemView.findViewById(R.id.department_id);*/
      /*  category_id = (TextView)itemView.findViewById(R.id.category_id);*/
        category_name = (TextView)itemView.findViewById(R.id.cat_name);
        photo = (ImageView)itemView.findViewById(R.id.product_image);
        cardView=itemView.findViewById(R.id.MainCardView);

        view = itemView;
    }
}
