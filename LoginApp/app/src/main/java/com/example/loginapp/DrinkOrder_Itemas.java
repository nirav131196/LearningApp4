package com.example.loginapp;

import static com.facebook.FacebookSdk.getApplicationContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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
    String token,api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_order_itemas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //For Adding unique item ids for selecting sub-category list items
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            id = extras.getString("key");
        }
        // using token of login activity
        SharedPreferences sh = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        token = sh.getString("token","");

        // using api key of login activity
        SharedPreferences sh2 = getApplicationContext().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        api = sh2.getString("apiKey","");

        //Calling api method for sub category items
        getSubCategoryItemsData(token);

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


            // API CODE OF SUB-CATEGORY ITEMS
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
                            adapter = new DrinkOrderCategory_RecyclerAdapter(list, getApplication(), new DrinkOrderCategory_RecyclerAdapter.ItemClickListener() {

                                @Override
                                public void OnItemClicked(int position,String choice) {

                                    if(choice.equals("favourite"))
                                        postFavouriteData(token,list,position);
                                    else
                                        RemoveFavouriteData(token,list,position);
                                }
                            });
                            recyclerView.setLayoutManager(new LinearLayoutManager(DrinkOrder_Itemas.this));
                            recyclerView.setAdapter(adapter);
                            Toast.makeText(getApplicationContext(), "Sub-category Product details fetched", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            showToast("First Do Login");
                            Intent i =new Intent(DrinkOrder_Itemas.this,LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    } catch (JSONException ex)
                    {
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
    // FAVOURITE ITEM ADDING METHOD (API)
    private void postFavouriteData(String access_token,List<DrinkOrderCategory_Data> list,int position)
    {
        JSONObject req = new JSONObject();
        try
        {
            req.put("product_id",list.get(position).productid);  // DATA OF field which we will enter while adding favourite item
        }
        catch(Exception ex)
        {
            Toast.makeText( getApplicationContext(), "My Exception : "+ex, Toast.LENGTH_SHORT).show();
        }

        String url = "https://admin.p9bistro.com/index.php/addFavouriteProduct";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,req, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if (response.getBoolean("status")) {
                        String message = response.getString("message");

                        JSONObject jsonData = response.getJSONObject("data");  // responses which we got after successful run

                        String user_id = jsonData.getString("user_id");
                        String product_id = jsonData.getString("product_id");
                        Toast.makeText(getApplicationContext(), "Item Added Successfully", Toast.LENGTH_SHORT).show();
                        list.get(position).isfourite = true;
                        adapter.notifyItemChanged(position);
                    }
                    else
                    {
                        Log.e("STATUS","FALSE");
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
    // REMOVING FAVOURITE ITEM METHOD (API)
    private void RemoveFavouriteData(String access_token,List<DrinkOrderCategory_Data> list,int position)
    {
        JSONObject req = new JSONObject();
        try
        {
            req.put("product_id",list.get(position).productid);  // DATA OF field which we will enter while adding favourite item
        }
        catch(Exception ex)
        {
            Toast.makeText( getApplicationContext(), "Exception : "+ex, Toast.LENGTH_SHORT).show();
        }
        String url = "https://admin.p9bistro.com/index.php/removeFavouriteProduct";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,url,req, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    if (response.getBoolean("status")) {

                        Toast.makeText(getApplicationContext(), "Item Removed Successfully", Toast.LENGTH_SHORT).show();
                        list.get(position).isfourite = false;
                        adapter.notifyItemChanged(position);
                    }
                    else
                    {
                        showToast("Status False");
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
}