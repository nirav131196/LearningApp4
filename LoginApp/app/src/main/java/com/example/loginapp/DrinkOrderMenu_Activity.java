package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrinkOrderMenu_Activity extends BaseActivity implements SelectListener  {

    RecyclerAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_order_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        token();

        recyclerView =(RecyclerView) findViewById(R.id.product_data);
    }
    @Override
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(DrinkOrderMenu_Activity.this, Dashboard_Activity_p9.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }

    private void getProductData(String access_token)
    {
        try
        {
            String url = "https://admin.p9bistro.com/index.php/getCategoryListondeptIds?deptids=[1]";

            JSONObject req = new JSONObject();
            try
            {
                req.put("deptids",1);
            }
            catch(Exception ex)
            {
                showToast("Exception "+ex);
            }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,req, response -> {
                try
                {
                    if(response.getBoolean("status"))
                    {

                   //     String message = response.getString("message");

                        JSONArray resarray = response.getJSONArray("data");

                        List<Product_Data> list = new ArrayList<>();

                        for(int i =0;i<resarray.length();i++)
                        {
                            JSONObject resobj = resarray.getJSONObject(i);

                            String id = resobj.getString("id");
                            String categoryId = resobj.getString("category_id");
                            String departmentId = resobj.getString("department_id");
                            String categoryTypeId = resobj.getString("category_type_id");
                            String catName = resobj.getString("cat_name");
                            String image = resobj.getString("image");
                            String catRank = resobj.getString("cat_rank");
                            String hasCategory = resobj.getString("has_category");
                            String isActive = resobj.getString("is_active");
                            String createdDate = resobj.getString("created_date");
                            String updatedDate = resobj.getString("updated_date");

                            list.add(new Product_Data(catName,image,categoryId));
                         }
                        adapter  =new RecyclerAdapter(list,getApplication(),this);
                        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                        Toast.makeText(getApplicationContext(),"Drink Product details fetched",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        showToast("Response false");
                    }
                }
                catch (JSONException ex)
                {
                    showToast("Error : "+ex);
                    ex.printStackTrace();
                }
            }, error -> showToast("Fail to get Response : "+error)){
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
            Toast.makeText(DrinkOrderMenu_Activity.this, "Error 3 : "+ex, Toast.LENGTH_SHORT).show();
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
                    getProductData(access_token);

                } catch (JSONException je) {
                    Toast.makeText(DrinkOrderMenu_Activity.this, "Error 2 : " + je, Toast.LENGTH_SHORT).show();
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i =new Intent(DrinkOrderMenu_Activity.this, Dashboard_Activity_p9.class);
        startActivity(i);
        finish();
    }
    @Override
    public void OnItemClicked(Product_Data data) {
        String id = data.category_id;
        Intent i =new Intent(DrinkOrderMenu_Activity.this, DrinkOrder_Itemas.class);
        i.putExtra("key",id);
        startActivity(i);
        finish();


    }
}