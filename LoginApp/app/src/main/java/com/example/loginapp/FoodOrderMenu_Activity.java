package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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

public class FoodOrderMenu_Activity extends BaseActivity implements FoodCatItemSelectListener{

    FoodRecyclerAdapter adapter;
    RecyclerView  recyclerView;

    String access_token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //token();

        // using token of login activity
        SharedPreferences sh = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        access_token = sh.getString("token","");

        getFoodProductData(access_token);

        recyclerView = (RecyclerView) findViewById(R.id.food_order_menu);
    }
    @Override
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(FoodOrderMenu_Activity.this, Dashboard_Activity_p9.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }
    private  void getFoodProductData(String access_token)
    {
        try {
                String url = "https://admin.p9bistro.com/index.php/getCategoryListondeptIds?deptids=[2]";

                JSONObject req = new JSONObject();

                try {
                    req.put("deptids",2);
                }
                catch (Exception ex)
                {
                    showToast("Exception 1 : "+ex);
                }
            JsonObjectRequest request  = new JsonObjectRequest(Request.Method.GET, url, req, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        if (response.getBoolean("status")) {

                            JSONArray resarray = response.getJSONArray("data");

                            List<FoodProductData> foodlist =new ArrayList<>();

                            for(int i = 0;i<resarray.length();i++)
                            {
                                JSONObject resobj = resarray.getJSONObject(i);

                                String id = resobj.getString("id");
                                String category_id = resobj.getString("category_id");
                                String departmentId = resobj.getString("department_id");
                                String categoryTypeId = resobj.getString("category_type_id");
                                String cat_name = resobj.getString("cat_name");
                                String image = resobj.getString("image");
                                String catRank = resobj.getString("cat_rank");
                                String category = resobj.getString("has_category");
                                String active = resobj.getString("is_active");
                                String created_date = resobj.getString("created_date");
                                String updated_date = resobj.getString("updated_date");

                                Log.e("Data is :",cat_name);

                                foodlist.add(new FoodProductData(cat_name,image,category_id));
                            }
                            adapter = new FoodRecyclerAdapter(foodlist,getApplicationContext(),FoodOrderMenu_Activity.this);
                            GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);

                            Toast.makeText(getApplicationContext(),"Food details fetched",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            showToast("Response false");
                        }
                    } catch (JSONException ex) {
                        showToast("Exception 2 : " + ex);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    showToast("Fail to get response : "+error);

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
            showToast("Exception 3 : "+ex);
        }
    }
    private void token() {

        String url = "https://admin.p9bistro.com/index.php/generate_auth_token";
        Log.e("API URL", url + "");

        StringRequest stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("checklog", response + "");
                JSONObject jsonObject = null;
                try
                {
                    jsonObject = new JSONObject(response);
                    String access_token = jsonObject.getString("access_token");
                    Log.e("ACCESS TOKEN", access_token);
                    getFoodProductData(access_token);

                } catch (JSONException je) {
                    Toast.makeText(FoodOrderMenu_Activity.this, "Error 2 : " + je, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Error Response ",error + "");
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i  =new Intent(FoodOrderMenu_Activity.this, Dashboard_Activity_p9.class);
        startActivity(i);
        finish();
    }

    @Override
    public void OnItemClicked(FoodProductData data)
    {
        String cat_id = data.FoodCategoryId;
        Intent i =new Intent(FoodOrderMenu_Activity.this,FoodOrderCategory_Items.class);
        i.putExtra("CAT_ID",cat_id);
        startActivity(i);
        finish();

    }
}