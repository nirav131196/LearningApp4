package com.example.loginapp;

import static androidx.core.content.ContextCompat.startActivity;
import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginapp.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Favourite_Iteam_View_Holder extends RecyclerView.ViewHolder {

   /* RecyclerView recyclerView;*/

    TextView txtProductId,txtProductName;
    View view;
    ImageView IVCancelOption;

    public Favourite_Iteam_View_Holder(@NonNull View itemView) {
        super(itemView);

        txtProductId = (TextView)itemView.findViewById(R.id.favouriteProductId);
        txtProductName = (TextView)itemView.findViewById(R.id.favouriteProductName);
        IVCancelOption = (ImageView) itemView.findViewById(R.id.cancel_icon);
     /*   recyclerView =(RecyclerView) itemView.findViewById(R.id.favouriteMenuItems);*/
        view = itemView;

    }
}
