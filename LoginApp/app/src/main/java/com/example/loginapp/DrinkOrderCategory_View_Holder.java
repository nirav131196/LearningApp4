package com.example.loginapp;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;
import static androidx.core.app.NotificationCompat.getExtras;
import static com.facebook.FacebookSdk.getApplicationContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.LoggingBehavior;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DrinkOrderCategory_View_Holder extends RecyclerView.ViewHolder {

    TextView txtSubCategoryName,txtSubCategoryDescription,txtPrice,txtid;
    View view;
    ImageView image;
    int count;
    String id;
    public DrinkOrderCategory_View_Holder(@NonNull View itemView) {
        super(itemView);

        txtSubCategoryName = (TextView)itemView.findViewById(R.id.subcategoryname);
        txtSubCategoryDescription = (TextView)itemView.findViewById(R.id.subcategorydescription);
        txtPrice = (TextView)itemView.findViewById(R.id.subcategoryprice);
        txtid = (TextView)itemView.findViewById(R.id.product_id);
        image = (ImageView)itemView.findViewById(R.id.favourite_icon);
        view = itemView;
    }
    private void token()
    {
        String url = "https://admin.p9bistro.com/index.php/generate_auth_token";

        StringRequest request =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject = null;
                try
                {
                    jsonObject = new JSONObject(response);
                    String access_token = jsonObject.getString("access_token");
                    Log.e("ACCESSTOKEN", access_token);



                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyToken", MODE_PRIVATE);
                    SharedPreferences.Editor edit =sharedPreferences.edit();
                    edit.putString("token",access_token);
                    edit.apply();
                }
                catch (JSONException Je)
                {
                    Toast.makeText(getApplicationContext(), "Error 2 : " + Je, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("checklog",error + "");
                Toast.makeText(getApplicationContext(), "Timeout Error", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError
            {
                HashMap<String,String> headers = new HashMap<>();
                headers.put("x-api-key","XABRTYUX@123YTUFGB");
                return headers;
            }
        };
        RequestQueue requestquese = Volley.newRequestQueue(getApplicationContext());
        requestquese.add(request);
    }



    private void token2()
    {
        String url = "https://admin.p9bistro.com/index.php/generate_auth_token";

        StringRequest request =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject = null;
                try
                {
                    jsonObject = new JSONObject(response);
                    String access_token = jsonObject.getString("access_token");
                    Log.e("ACCESSTOKEN", access_token);


                }
                catch (JSONException Je)
                {
                    Toast.makeText(getApplicationContext(), "Error 2 : " + Je, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("checklog",error + "");
                Toast.makeText(getApplicationContext(), "Timeout Error", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError
            {
                HashMap<String,String> headers = new HashMap<>();
                headers.put("x-api-key","XABRTYUX@123YTUFGB");
                return headers;
            }
        };
        RequestQueue requestquese = Volley.newRequestQueue(getApplicationContext());
        requestquese.add(request);
    }
}
