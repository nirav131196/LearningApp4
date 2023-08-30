package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FliterAPI_Activity extends AppCompatActivity {

    ArrayList<FilterTypeModel> filterTypeModelArrayList;
    RecyclerView rvfilter;
    FilterData_Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fliter_api);

        filterTypeModelArrayList = new ArrayList<>();
        rvfilter = findViewById(R.id.rvFilter);

        tokenforType();
    }
    private void tokenforType() {

        String url = "https://admin.saqqara.in/index.php/generate_auth_token";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("checkResponse of Filter Offer Token : ", response + "");
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    String access_token = jsonObject.getString("access_token");
                    Log.e("ACCESSTOKEN", access_token);
                    getTypes(access_token);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("checklog", error + "");
                Toast.makeText(getApplicationContext(), "Timeout Error", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-api-key", "SHXVDFTRWSQ@!TYUS");
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
    private void getTypes(String access_token){

        filterTypeModelArrayList.clear();
        Log.e("Hi","chk5");
        String url = "https://admin.saqqara.in/index.php/api/businessCategory";
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("responcccccce_TYpe", response + "");
                try {
                    JSONObject object = new JSONObject(String.valueOf(response));
                    //  Log.e("respo", response + "");
                    String message = object.getString("message");
                    //Toast.makeText(FavouriteActivity.this, message, Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray = object.getJSONArray("data");

                    Log.e("JSONArray_Length : ", jsonArray.length()+"" );

                    if (object.getString("status").equals("true")) {

                        for (int i = 0; i < jsonArray.length(); i++) {

                            FilterTypeModel filterTypeModel = new FilterTypeModel();

                            JSONObject tableObject = jsonArray.getJSONObject(i);
                            filterTypeModel.setId(tableObject.getString("id"));
                            filterTypeModel.setName(tableObject.getString("name"));
                            filterTypeModel.setImage(tableObject.getString("image"));

                            filterTypeModelArrayList.add(filterTypeModel);

                        }

                        rvfilter.setLayoutManager(new LinearLayoutManager(FliterAPI_Activity.this,  LinearLayoutManager.VERTICAL, false));
                        adapter = new FilterData_Adapter(filterTypeModelArrayList);
                        rvfilter.setAdapter(adapter);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("MYapp", "somethingg went wrong");
                        //  Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        try {
                            String responseBody = new String(error.networkResponse.data, "utf-8");
                            JSONObject data = new JSONObject(responseBody);
                            Log.e("data ", data.toString());
                            Toast.makeText(getApplicationContext(), data.getString("message"), Toast.LENGTH_LONG).show();             } catch (JSONException e) {
                        } catch (UnsupportedEncodingException errorr) {
                        }
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("Authorization",access_token);
                return header;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}