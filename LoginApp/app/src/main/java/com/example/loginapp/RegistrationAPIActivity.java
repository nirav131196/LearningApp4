package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

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
import com.facebook.login.LoginClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationAPIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_apiactivity);




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

                }
                catch (JSONException Je)
                {
                    Toast.makeText(RegistrationAPIActivity.this, "Error 2 : " + Je, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("checklog",error + "");
                Toast.makeText(getApplicationContext(), "Timeout Error "+error, Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError
            {
                HashMap<String,String> headers = new HashMap<>();
                headers.put("x-api-key", "SHXVDFTRWSQ@!TYUS");
                return headers;
            }
        };
        RequestQueue requestquese = Volley.newRequestQueue(getApplicationContext());
        requestquese.add(request);
    }
    public void postData()
    {
        String url = "https://admin.p9bistro.com/index.php/SignUp";




        // raw data
        JSONObject req = new JSONObject();
        try {

            req.put("username","nirav");
            req.put("password","girnari");
        }
        catch(JSONException ex)
        {
            ex.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, req, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    if(response.getBoolean("status"))
                    {

                        String message = response.getString("message");

                        JSONObject jsondata = response.getJSONObject("data");
                        String id = jsondata.getString("id");
                        String name = jsondata.getString("name");
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"Error "+error,Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String,String> headers = new HashMap<>();
                    headers.put("authorization","accesstoken");
                return headers;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }

    private void loginData()
    {
        String url = "";

        JSONObject req = new JSONObject();
        try {
                req.put("email","nirav@gmail.com");
                req.put("password","@123");
        }
        catch (Exception ex)
        {

        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, req, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getBoolean("status"))
                    {
                        String message = response.getString("message");

                        JSONObject jsondata = response.getJSONObject("data");
                        String id = jsondata.getString("id");
                        String name = jsondata.getString("name");
                        String surname = jsondata.getString("surname");
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("par1","aaa");

                return headers;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }

    public void loginData2()
    {
        String url = "https://admin.p9bistro.com/index.php/SignIn";

        JSONObject req = new JSONObject();
        try
        {
            req.put("username","username");
            req.put("password","passwword");
            req.put("login by","login by");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, req, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getBoolean("status"))
                    {
                        String message = response.getString("message");

                        JSONObject jsondata = response.getJSONObject("data");
                        String id = jsondata.getString("ID");
                        String name = jsondata.getString("NAME");
                        String surname = jsondata.getString("SURNAME");

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error : "+error,Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> hashmap = new HashMap<>();
                hashmap.put("authorization","access_token");
                hashmap.put("Content-Type","application/json");
                return hashmap;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }
}