package com.example.loginapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImageAPI extends AppCompatActivity {

    RecyclerView rvImageAPI;
    ImageAPI_Adapter adapter;
    ArrayList<Model_ImageAPI> myarray;
    String access_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_api);

        myarray = new ArrayList<>();
        rvImageAPI = findViewById(R.id.rvImageAPI);
        generateAccessTokenEvent();
    }
    private void generateAccessTokenEvent() {
        String urlLogin = "https://admin.saqqara.in/index.php/generate_auth_token";
//        Log.e("Hi",urlLogin);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlLogin, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResponse(String response) {
                Log.e("Hi", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
//                    Log.e("Hi","chk");
                    access_token = jsonObject.getString("access_token");
//                    Log.e("The_new_token", access_token );
                    //calling login user with access token
                    upcomingEvent(access_token);
//                    Log.e("Hi","chk6");
//                    mProgressDialog.hide();


                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ImageAPI.this, "Error "+error, Toast.LENGTH_SHORT).show();

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
    private void upcomingEvent(String access_token)
    {
        myarray.clear();
        String url = "https://admin.saqqara.in/index.php/eventList";
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        StringRequest jsonStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("DATA", response);
                    try
                    {
                        JSONObject object =  new JSONObject(String.valueOf(response));
                        String message = object.getString("message");

                        if(object.getString("status").equals("true")){
                            JSONArray jsonArray = object.getJSONArray("data");
                            if(jsonArray.length() > 0)
                            {
                                for(int i =0;i < jsonArray.length();i++)
                                {
                                    Model_ImageAPI model = new Model_ImageAPI();
                                    JSONObject tableobject = jsonArray.getJSONObject(i);
                                    model.setId(tableobject.getString("eventId"));
                                    model.setTitle(tableobject.getString("eventTitle"));
                                    model.setDescription1(tableobject.getString("eventDesc"));
                                    model.setImage(tableobject.getString("eventImage"));
                                    Log.e("EV_IMG_Logo : ", "onResponse: " + model.getImageLogo());
                                    Log.e("EV_IMG : ", "onResponse: " + model.getImage());
                                    model.setImageLogo(tableobject.getString("partnerLogo"));
                                    model.setPartner_id(tableobject.getString("partnerId"));
                                    model.setYear(tableobject.getString("year"));
                                    model.setMonth(tableobject.getString("month"));
                                    model.setDay(tableobject.getString("day"));
                                    model.setEvent_status(tableobject.getString("event_status"));
                                    model.setDefault_image(tableobject.getString("defaultImage"));
                                    myarray.add(model);

                                }
                                rvImageAPI.setLayoutManager(new LinearLayoutManager(ImageAPI.this, LinearLayoutManager.HORIZONTAL,false));
                                adapter = new ImageAPI_Adapter(myarray);
                                rvImageAPI.setAdapter(adapter);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(ImageAPI.this, "Response is False", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(getApplicationContext(),"Error : "+ex.getMessage(),Toast.LENGTH_LONG).show();
                        Log.e("ERROR"," "+ex.getMessage());
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

                Map<String, String> header = new HashMap<String, String>();
                header.put("Authorization", access_token);
                //   header.put("api-key",api_key);
                return header;
            }
        };
        requestQueue.add(jsonStringRequest);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}