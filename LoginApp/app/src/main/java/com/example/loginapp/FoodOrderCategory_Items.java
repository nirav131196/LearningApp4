package com.example.loginapp;

import static com.example.loginapp.FoodOrderCategory_Retrofit_Instance.instance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodOrderCategory_Items extends BaseActivity {

        public static String url = "https://admin.p9bistro.com";
        RecyclerView recyclerView;
        List<FoodOrderCategory_Data> allUsersList;

        FoodOrderCategory_RecyclerAdapter adapter;

        String CAT_ID;

        String api_key,token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order_category_items);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = findViewById(R.id.FoodOrderIteams);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(getIntent().hasExtra("CAT_ID"))
        {
            CAT_ID = getIntent().getStringExtra("CAT_ID");
        }
        //setting shared preference
        SharedPreferences sh = getApplicationContext().getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        token = sh.getString("token","");

        // using api key of login activity
        SharedPreferences sh2 = getApplicationContext().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        api_key = sh2.getString("apiKey","");

        //RETROFIT API CODE
        FoodOrderCategory_Retrofit_Instance.getInstance().apiInterface.getDetails(token,CAT_ID).enqueue(new Callback<FoodOrderCategory_Data_BaseRes>() {
            @Override
            public void onResponse(Call<FoodOrderCategory_Data_BaseRes> call, Response<FoodOrderCategory_Data_BaseRes> response) {

                    FoodOrderCategory_Data_BaseRes baseresponse = response.body();

                    allUsersList = baseresponse.foodlist;
                    Log.e("Base response","response"+baseresponse.message);
                    if(allUsersList != null)
                    {
                        FoodOrderCategory_RecyclerAdapter adapter =new FoodOrderCategory_RecyclerAdapter(FoodOrderCategory_Items.this,getApplication(), allUsersList, new FoodOrderCategory_RecyclerAdapter.ItemClickedlistener() {
                            @Override
                            public void onItemClicked(int position, String choice) {
                                if(choice.equals("FAVOURITE"))
                                    PostFavouriteItem(position);
                                else
                                    RemoveFavouriteItem(position);
                            }
                        });
                        recyclerView.setAdapter(adapter);
                    }
                    else
                    {
                        showToast("Do Login First");
                        Intent i = new Intent(FoodOrderCategory_Items.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                        Log.e("FoodOrderCategory_Item","Null");
                    }
            }
            @Override
            public void onFailure(Call<FoodOrderCategory_Data_BaseRes> call, Throwable t) {

                Log.e("api","onFailure : "+t.getLocalizedMessage());
            }
        });
    }
    private void RemoveFavouriteItem(int position) {

        allUsersList.get(position).isfavourite = false;
        adapter.notifyItemChanged(position);
        showToast("UNFAVOURITE");
    }
    private void PostFavouriteItem(int position) {

     /* public static String addurl = "https://admin.p9bistro.com";*/

        FoodOrderCat_Post_Retrofit_Instance.getInstance().apiInterface.postitems(token,api_key).enqueue(new Callback<FoodOrderCat_BaseResponseData>() {
            @Override
            public void onResponse(Call<FoodOrderCat_BaseResponseData> call, Response<FoodOrderCat_BaseResponseData> response) {

                FoodOrderCat_BaseResponseData baseresponse2 = response.body();

                allUsersList = baseresponse2.foodlist;
                if(allUsersList != null)
                {
                    FoodOrderCategory_RecyclerAdapter adapter1 = new FoodOrderCategory_RecyclerAdapter(FoodOrderCategory_Items.this,getApplication(),allUsersList,null);
                    recyclerView.setAdapter(adapter1);
                    showToast("Item Added Successfully");
                    allUsersList.get(position).isfavourite = true;
                    adapter.notifyItemChanged(position);
                }
                else
                {
                    showToast("Do Login First");
                    Intent i = new Intent(FoodOrderCategory_Items.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                    Log.e("FoodOrderCategoryFavourite_Item","Null");
                }
            }
            @Override
            public void onFailure(Call<FoodOrderCat_BaseResponseData> call, Throwable t) {

                Log.e("api","onFailure : "+t.getLocalizedMessage());
            }
        });
    }
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(FoodOrderCategory_Items.this, FoodOrderMenu_Activity.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }
}