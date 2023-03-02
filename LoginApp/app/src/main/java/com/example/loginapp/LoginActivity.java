package com.example.loginapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity implements  AuthenticationListener {

    private static int RC_SIGN_IN = 100;
    private String token = null;
    AppPreferences appPreferences = null;
    ImageView profilePhoto;
    TextView txtUserid,txtUsername;
    EditText edtEmail,edtPassword;
    Button btnLogin;
    TextView txtSignuptv;
    String email_login,password_login;
    ImageView IVGogglelogo,IVFacebooklogo,IVInstagramlogo;
    GoogleSignInClient mGoogleSignInClient; // FOR GOOGLE
    CallbackManager callbackManager; // FOR FACEBOOK

    AuthenticationDialog  authenticationDialog = null;  // FOR INSTAGRAM
    public AuthenticationListener listener;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      /*  callbackManager = CallbackManager.Factory.create();*/

        initView();

        FaceBookManager();

        ClickEventGoogle();
        ClickEventFacebook();
        ClickEventInstagram();
        ClickEventTextView();

        GoogleConfiguration();

        ClickEventLoginbutton();
    }
    private void ClickEventLoginbutton() {
        btnLogin.setOnClickListener(new View.OnClickListener() {  // CLICK EVENT OF SIGN IN (LOG IN)
            @Override
            public void onClick(View v) {

                try
                {
                    email_login = edtEmail.getText().toString().trim();
                    password_login = edtPassword.getText().toString().trim();

                    if(email_login.length() > 0 && password_login.length() > 0)
                    {
                        if(Patterns.EMAIL_ADDRESS.matcher(email_login).matches())
                        {
                            // valid email address
                            if(isValidPassword(password_login))
                            {
                                if(password_login.length() > 7 && password_login.length() < 25)
                                {
                                    token();
                                }
                                else
                                {
                                    showToast("Password Length should be more then 7 and less than 25");
                                }
                            }
                            else
                            {
                                showToast("Please Enter Valid Password");
                            }
                        }
                        else
                        {
                            showToast("Please Enter valid Email address");
                        }
                    }
                    else
                    {
                        showToast("Please Enter All Details");
                    }
                }
                catch(Exception e)
                {
                    showToast("Please Enter All Details"+e);
                }

            }
        });
    }

    private void GoogleConfiguration() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

    }

    private void ClickEventTextView() {
        txtSignuptv.setOnClickListener(new View.OnClickListener() {  // CLICK EVENT OF SIGN UP (LINK OF SIGN UP)
            @Override
            public void onClick(View v) {

                Intent i =new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });

    }
    private void ClickEventInstagram() {
        IVInstagramlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try
                {
                    authenticationDialog = new AuthenticationDialog(LoginActivity.this,
                            listener);
                    authenticationDialog.setCancelable(true);
                    authenticationDialog.show();
                }
                catch (Exception ex)
                {
                    showToast("Error 1 "+ex);
                }

            }
        });
    }

    private void ClickEventFacebook() {
        IVFacebooklogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
                //    finish();

            }
        });
    }
    private void ClickEventGoogle() {
        IVGogglelogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();


            }
        });
    }
    private void FaceBookManager() {
        // FOR FACEBOOK
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        startActivity(new Intent(LoginActivity.this,WelcomeActivity.class));

                        showToast("Log In Successfully");
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    private void initView() {
        profilePhoto =findViewById(R.id.profilephoto);

        txtUserid = findViewById(R.id.user_id);
        txtUsername = findViewById(R.id.user_name);

        edtEmail =findViewById(R.id.edittext_emailaddress_login);
        edtPassword =findViewById(R.id.edittextpassword);

        txtSignuptv =findViewById(R.id.linkforlogin2);

        btnLogin =findViewById(R.id.login_button);

        IVGogglelogo =findViewById(R.id.googlelogo);
        IVFacebooklogo =findViewById(R.id.facebooklogo);
        IVInstagramlogo = findViewById(R.id.instagramlogo);
    }
    private void postLoginData(String access_token)
    {
        try
        {
            String url = "https://admin.p9bistro.com/index.php/SignIn";

          //  RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

            JSONObject req = new JSONObject();

            try
            {
                req.put("username",email_login);
                req.put("password",password_login);
                req.put("login_by",5);
            }
            catch(Exception ex)
            {
                showToast("Exception "+ex);
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,req,new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response)
                {
                    try
                    {
                        if(response.getBoolean("status"))
                        {
                            String message = response.getString("message");
                            showToast(message);

                            JSONObject resobj = response.getJSONObject("data");
                            String id = resobj.getString("id");
                            String username = resobj.getString("username");
                            String mobile_no = resobj.getString("mobile_no");
                            String email2 = resobj.getString("email");
                            String profile = resobj.getString("profile");
                            String api_key = resobj.getString("api_key");

                            edtEmail.setText("");
                            edtPassword.setText("");

                            Intent i = new Intent(LoginActivity.this,WelcomeActivity.class);
                            startActivity(i);
                            finish();

                            Log.e("RESPONSE GOT", id + username + mobile_no + email2 + profile + api_key);
                            Log.e("INPUT DATA",email_login + password_login);
                        }
                    }
                    catch (JSONException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    showToast("Fail to get Response : "+error);
                }
            }){
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
            Toast.makeText(LoginActivity.this, "Error 3 : "+ex, Toast.LENGTH_SHORT).show();
        }
    }
    private void token()
    {
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
                    postLoginData(access_token);

                } catch (JSONException je) {
                    Toast.makeText(LoginActivity.this, "Error 2 : " + je, Toast.LENGTH_SHORT).show();
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
            public Map<String,String> getHeaders() throws AuthFailureError{
                HashMap<String,String> headers = new HashMap<>();
                headers.put("x-api-key","XABRTYUX@123YTUFGB");
                return headers;
            }
        };
        RequestQueue requestquese = Volley.newRequestQueue(getApplicationContext());
        requestquese.add(stringRequest);
    }
    public static boolean isValidPassword(final String password)  // 1 number , 1 Uppercase , 1 special symbol , 1 lowercase
    {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);  // FOR FACEBOOK INTEGRATION

        // FOR GOOGLE LOGIN
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null)
            {
                String personGivenName = acct.getGivenName();
                showToast("Welcome "+personGivenName);
            }
            startActivity(new Intent(LoginActivity.this,WelcomeActivity.class));

            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e(TAG, "Message" + e.toString());

        }
    }
    @Override
    public void onTokenReceived(String auth_token) {
        if (auth_token == null)
            return;
        appPreferences.putString(AppPreferences.TOKEN, auth_token);
        token = auth_token;
        getUserInfoByAccessToken(token);
    }
    private void getUserInfoByAccessToken(String token)
    {
        try {
            new RequestInstagramAPI().execute();
        }
        catch (Exception ex)
        {
            showToast("Error 2 "+ex);
        }
    }
    private class RequestInstagramAPI extends AsyncTask<Void,String,String>
    {
        @Override
        protected String doInBackground(Void... voids) {

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(getResources().getString(R.string.get_user_info_url) + token);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                return EntityUtils.toString(httpEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String response)
        {
            super.onPostExecute(response);
            if(response != null)
            {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonData = jsonObject.getJSONObject("data");
                    if (jsonData.has("id")) {
                        appPreferences.putString(AppPreferences.USER_ID, jsonData.getString("id"));
                        appPreferences.putString(AppPreferences.USER_NAME, jsonData.getString("username"));
                        appPreferences.putString(AppPreferences.PROFILE_PIC, jsonData.getString("profile_picture"));
                        login();
                    }
                }
                catch(JSONException ex)
                {
                    ex.printStackTrace();
                }
            }
            else
            {
                showToast("Login Error!");
            }
        }
    }
    public void login()  // FOR INSTAGRAM
    {
        try {
            Picasso.get().load(appPreferences.getString(AppPreferences.PROFILE_PIC)).into(profilePhoto);
            txtUserid.setText(appPreferences.getString(AppPreferences.USER_ID));
            txtUsername.setText(appPreferences.getString(AppPreferences.USER_NAME));
        }
        catch (Exception ex)
        {
            showToast("Exception : "+ex);
        }
    }
}