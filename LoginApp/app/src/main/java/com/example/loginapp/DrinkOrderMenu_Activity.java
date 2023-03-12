package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrinkOrderMenu_Activity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_order_menu);

        token();

        List<Product_Data> list = new ArrayList<>();
        list =getData();
        recyclerView =(RecyclerView) findViewById(R.id.product_data);
        adapter  =new RecyclerAdapter(list,getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DrinkOrderMenu_Activity.this));
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
                        Log.e("position","position");
                        String message = response.getString("message");

                        JSONObject resobj = response.getJSONObject("data");

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

                      //  Toast.makeText(getApplicationContext(),"Id ",Toast.LENGTH_LONG).show();

                      /*  List<Product_Data> list = new ArrayList<>();

                        list.add(new Product_Data(id,categoryId,departmentId,catName));
                        showToast(id + categoryId + departmentId + categoryTypeId + catName + image + catRank + hasCategory + isActive + createdDate + updatedDate);*/

                        Log.d("RESPONSE GOT : ", id + categoryId + departmentId + categoryTypeId + catName + image + catRank + hasCategory + isActive + createdDate + updatedDate);
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
             //   showToast("Details Fetched");
            }, error -> showToast("Fail to get Response : "+error)){
                /*@Override
                protected Map<String,String>getParams()
                {
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("email",email_login);
                    params.put("password",password_login);
                    params.put("login_by", String.valueOf(5));
                    return params;
                }*/
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
    private List<Product_Data> getData()
    {
        List<Product_Data> list = new ArrayList<>();
        list.add(new Product_Data("9191","7262","838383","CLOTHES"));

        return list;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i =new Intent(DrinkOrderMenu_Activity.this, Dashboard_Activity_p9.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}