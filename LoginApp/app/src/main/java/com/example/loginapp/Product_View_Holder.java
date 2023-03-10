package com.example.loginapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;

import org.w3c.dom.Text;

public class Product_View_Holder extends RecyclerView.ViewHolder {

    TextView id,category_id,department_id,category_name;
    View view;


    public Product_View_Holder(@NonNull View itemView) {
        super(itemView);

        id = (TextView)itemView.findViewById(R.id.product_id);
        category_id = (TextView)itemView.findViewById(R.id.category_id);
        department_id = (TextView)itemView.findViewById(R.id.department_id);
        category_name = (TextView)itemView.findViewById(R.id.cat_name);

        view = itemView;
    }
}
