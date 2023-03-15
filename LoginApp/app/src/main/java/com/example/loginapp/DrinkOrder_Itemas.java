package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrinkOrder_Itemas extends BaseActivity {

    DrinkOrderCategory_RecyclerAdapter adapter;
    RecyclerView recyclerView;
    String id2;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_order_itemas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            id = extras.getString("key");
        }
        token();

        recyclerView =(RecyclerView) findViewById(R.id.drinkOrderIteams);
    }
    private void getSubCategoryItemsData(String access_token)
    {
        try
        {
            String url2 = "https://admin.p9bistro.com/index.php/getSubCateogryProductList?deptids=[1]&cat_id=&sub_id=0";
            String stringtobeinserted = id;
            int index = 81;
            String url = insertString(url2,stringtobeinserted,index);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        if (response.getBoolean("status")) {
                            String message = response.getString("message");

                            JSONArray resarray = response.getJSONArray("Product List");

                            List<DrinkOrderCategory_Data> list = new ArrayList<>();

                            for (int i = 0; i < resarray.length(); i++) {
                                JSONObject resobj = resarray.getJSONObject(i);

                                id2 = resobj.getString("id");
                                String product_name = resobj.getString("product_name");
                                String Description = resobj.getString("Description");
                                String Rate = resobj.getString("Rate");

                                list.add(new DrinkOrderCategory_Data(product_name, Description, Rate, id2));
                            }

                            Log.e("Product id is ", id2);
                            SharedPreferences sharedPreferences = getSharedPreferences("PRODUCT_ID", MODE_PRIVATE);
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.putString("product_id", id2);
                            edit.apply();

                            adapter = new DrinkOrderCategory_RecyclerAdapter(list, getApplication());
                            recyclerView.setLayoutManager(new LinearLayoutManager(DrinkOrder_Itemas.this));
                            recyclerView.setAdapter(adapter);
                            Toast.makeText(getApplicationContext(), "Sub category Product details fetched", Toast.LENGTH_LONG).show();
                        } else {
                            showToast("Response false");
                        }
                    } catch (JSONException ex) {
                        showToast("Error : " + ex);
                        ex.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    showToast("Fail to get Response : "+error);
                }
            }){
                @Override
                public Map<String,String> getHeaders()throws AuthFailureError
                {
                    Map<String,String>params = new HashMap<String ,String>();
                    params.put("authorization",access_token);
                    params.put("Content-Type", "application/json");
                    return params;
                }
            };
            RequestQueue requestQueue =Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
        }
        catch (Exception ex)
        {
            Toast.makeText(DrinkOrder_Itemas.this, "Error 3 : "+ex, Toast.LENGTH_SHORT).show();
        }
    }
    private void token() {
        String url = "https://admin.p9bistro.com/index.php/generate_auth_token";
        Log.e("checklog", url + "");

        StringRequest stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("checklog", response + "");
                JSONObject jsonObject = null;
                try
                {
                    jsonObject = new JSONObject(response);
                    String access_token = jsonObject.getString("access_token");
                    Log.e("ACCESSTOKEN", access_token);
                    getSubCategoryItemsData(access_token);

                } catch (JSONException je) {
                    Toast.makeText(DrinkOrder_Itemas.this, "Error 2 : " + je, Toast.LENGTH_SHORT).show();
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
            public Map<String,String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<>();
                headers.put("x-api-key","XABRTYUX@123YTUFGB");
                return headers;
            }
        };
        RequestQueue requestquese = Volley.newRequestQueue(getApplicationContext());
        requestquese.add(stringRequest);
    }
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(DrinkOrder_Itemas.this, DrinkOrderMenu_Activity.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i =new Intent(DrinkOrder_Itemas.this, DrinkOrderMenu_Activity.class);
        startActivity(i);
        finish();
    }
    public static String insertString(String original,String stringtobeinserted,int index)
    {
        String newstring = original.substring(0,index+1)+stringtobeinserted+original.substring(index+1);

        return newstring;
    }
}