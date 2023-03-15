package com.example.loginapp;

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
    public DrinkOrderCategory_View_Holder(@NonNull View itemView) {
        super(itemView);

        txtSubCategoryName = (TextView)itemView.findViewById(R.id.subcategoryname);
        txtSubCategoryDescription = (TextView)itemView.findViewById(R.id.subcategorydescription);
        txtPrice = (TextView)itemView.findViewById(R.id.subcategoryprice);
        txtid = (TextView)itemView.findViewById(R.id.product_id);
        image = (ImageView)itemView.findViewById(R.id.favourite_icon);
        view = itemView;
        count = 0;

        image.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(count == 0)
                {
                    ++count;
                    image.setImageDrawable(null);
                    image.setBackgroundResource(R.drawable.fullheart);
                    token();
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

                    postFavouriteData(access_token);
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
    private void postFavouriteData(String access_token)
    {

        Log.e("Error Point","Error Point");

        Log.e("Error Point 2","Error Point 2");

        JSONObject req = new JSONObject();
        try
        {
            Log.e("Error Point 3","Error Point 3");

            req.put("product_id",842);  // DATA OF field which we will enter while adding favourite item

            Log.e("Error Point 4","Error Point 4");
        }
        catch(Exception ex)
        {
            Toast.makeText( getApplicationContext(), "Exception : "+ex, Toast.LENGTH_SHORT).show();
        }
        Log.e("Error Point 5",req.toString());
      /*  Log.e("Error Point 5","Error Point 5");*/
        String url = "https://admin.p9bistro.com/index.php/addFavouriteProduct";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,req, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    Log.e("Error Point 5", "Error Point 5");
                    if (response.getBoolean("status")) {
                        String message = response.getString("message");
                        /*   Log.e("Error Point 6","Error Point 6");*/
                        JSONObject jsonData = response.getJSONObject("data");  // responses which we got after successful run
                        /*   Log.e("Error Point 7","Error Point 7");*/
                        String user_id = jsonData.getString("user_id");
                        String product_id = jsonData.getString("product_id");
                        /*  Log.e("Error Point 8","Error Point 8");*/
                 /*   Log.e("USER ID ", user_id);
                    Log.e("PRODUCT ID ", product_id);
*/
                        Toast.makeText(getApplicationContext(), "Iteam Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Fail to get Response : "+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String,String> getHeaders()throws AuthFailureError
            {
                SharedPreferences sh = getApplicationContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                String api = sh.getString("apiKey","");

                Map<String,String>params = new HashMap<String ,String>();

                params.put("authorization",access_token);
                params.put("api-key",api);
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        RequestQueue requestQuese = Volley.newRequestQueue(getApplicationContext());
        requestQuese.add(request);
    }
    /*private void postFavouriteData2(String access_token) {

        Log.e("Error Point", "Error Point");

        Log.e("Error Point 2", "Error Point 2");

        JSONObject req = new JSONObject();
        try {
            Log.e("Error Point 3", "Error Point 3");

            req.put("product_id", 842);  // DATA OF field which we will enter while adding favourite item

            Log.e("Error Point 4", "Error Point 4");
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Exception : " + ex, Toast.LENGTH_SHORT).show();
        }
        Log.e("Error Point 5", req.toString());
        *//*  Log.e("Error Point 5","Error Point 5");*//*
        String url = "https://admin.p9bistro.com/index.php/addFavouriteProduct"+req;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

             *//*   finish();*//*
            }
        }){ @Override
        public Map<String,String> getHeaders()throws AuthFailureError
        {
            SharedPreferences sh = getApplicationContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
            String api = sh.getString("apiKey","");

            Map<String,String>params = new HashMap<String ,String>();

            params.put("authorization",access_token);
            params.put("api-key",api);
            params.put("Content-Type", "application/json");
            return params;
        }};
        RequestQueue requestQuese = Volley.newRequestQueue(getApplicationContext());
        requestQuese.add(stringRequest);
    }*/
}
