package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends BaseActivity
{

    EditText edtUsername,edtPassword,edtMobileno,edtEmail;
    Button btnRegister;
    TextView txtSignIn;
    String username,password,mobileno,email;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        ClickEventTextView();
        ClickEventRegister();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }
    private void ClickEventRegister() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = edtUsername.getText().toString().trim();
                password = edtPassword.getText().toString().trim();
                mobileno = edtMobileno.getText().toString().trim();
                email = edtEmail.getText().toString().trim();

                try
                {
                    if(username.length() > 0  && password.length() > 0  && mobileno.length() > 0  && email.length() > 0)
                    {
                        if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
                        {
                            if(username.length() > 1)
                            {
                                if(isValidMobile(mobileno))
                                {
                                    if(isValidPassword(password))
                                    {
                                        token();// CREATING TOKEN FOR REGISTRATION
                                    }
                                    else
                                    {
                                        showToast("Invalid password format");
                                    }
                                }
                                else
                                {
                                    showToast("Invalid mobile no format");
                                }
                            }
                            else
                            {
                                showToast("Username length should be more than 1 character");
                            }
                        }
                        else
                        {
                            showToast("Please Enter valid Email address");
                        }
                    }
                    else
                    {
                        showToast("Enter All Details");
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(MainActivity.this, "Exception : "+ex, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void ClickEventTextView() {
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
                  finish();

            }
        });
    }

    private void initView()
    {
        edtUsername = findViewById(R.id.edittextusername);
        edtPassword = findViewById(R.id.edittextpassword);
        edtMobileno = findViewById(R.id.edittextmobileno);
        edtEmail = findViewById(R.id.edittextemailaddress);
        btnRegister = findViewById(R.id.registerbutton);
        txtSignIn = findViewById(R.id.linkforlogin2);
    }
    private void postRegisterData(String access_token)
    {
        String url = "https://admin.p9bistro.com/index.php/SignUp";

        JSONObject req = new JSONObject();
        try
        {
            req.put("username",edtUsername.getText().toString());  // DATA OF field which we will enter while doing sign up
            req.put("password",edtPassword.getText().toString());
            req.put("mobile_no",edtMobileno.getText().toString());
            req.put("email",edtEmail.getText().toString());
            req.put("profile","nirav");
            req.put("register_by", 5);
        }
        catch(Exception ex)
        {
            Toast.makeText( MainActivity.this, "Exception : "+ex, Toast.LENGTH_SHORT).show();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,req, new Response.Listener<JSONObject>() {

            @Override

            public void onResponse(JSONObject response)
            {
                try
                {
                    if(response.getBoolean("status"))
                    {
                        String message = response.getString("message");
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                        JSONObject jsonData = response.getJSONObject("data");  // responses which we got after successful run

                        String id = jsonData.getString("id");
                        String username = jsonData.getString("username");
                        String mobile_no = jsonData.getString("mobile_no");
                        String email = jsonData.getString("email");
                        String profile = jsonData.getString("profile");
                        String api_key = jsonData.getString("api_key");

                        Log.e("Data",id + username + mobile_no + email + profile + api_key);

                        edtUsername.setText("");
                        edtPassword.setText("");
                        edtMobileno.setText("");
                        edtEmail.setText("");
                    }
                    Log.e("Check Data", username + password + email + mobileno);

                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText( MainActivity.this, "Fail to get Response : "+error, Toast.LENGTH_SHORT).show();
            }
        }){
            /*  @Override
              protected Map<String,String> getParams()
              {
                  Map<String,String> params = new HashMap<String,String>();

                  params.put("username",username_edt.getText().toString());  // DATA OF field which we will enter while doing sign up
                  params.put("password",password_edt.getText().toString());
                  params.put("mobile_no",mobileno_edt.getText().toString());
                  params.put("email",email_edt.getText().toString());
                  params.put("profile","nirav");
                  params.put("register_by", String.valueOf(5));
                  return params;

                   //   Log.e("Data",username+password+mobileno);

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
        RequestQueue requestQuese = Volley.newRequestQueue(getApplicationContext());
        requestQuese.add(request);

    }
    //Generating Token For Registration
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
                    postRegisterData(access_token);
                }
                catch (JSONException Je)
                {
                    Toast.makeText(MainActivity.this, "Error 2 : " + Je, Toast.LENGTH_SHORT).show();
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
    // VALIDATING MOBILE_NO FUNCTIONALITY
    private boolean isValidMobile(String phone)
    {
        if(!Pattern.matches("[a-zA-Z]+[@#$%^&+=!.]+", phone)) {
            return phone.length() == 10;
        }
        return false;
    }
    // VALIDATING PASSWORD FUNCTIONALITY
    public static boolean isValidPassword(final String password)  // 1 number , 1 Uppercase , 1 special symbol , 1 lowercase
    {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}