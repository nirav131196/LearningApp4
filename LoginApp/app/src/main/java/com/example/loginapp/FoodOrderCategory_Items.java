package com.example.loginapp;

import static com.example.loginapp.FoodOrderCategory_Retrofit_Instance.instance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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

        String CAT_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order_category_items);

        recyclerView = findViewById(R.id.FoodOrderIteams);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(getIntent().hasExtra("CAT_ID"))
        {
            CAT_ID = getIntent().getStringExtra("CAT_ID");
        }
        SharedPreferences sh = getApplicationContext().getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String api = sh.getString("token","");

        FoodOrderCategory_Retrofit_Instance.getInstance().apiInterface.getDetails(api,CAT_ID).enqueue(new Callback<FoodOrderCategory_Data_BaseRes>() {
            @Override
            public void onResponse(Call<FoodOrderCategory_Data_BaseRes> call, Response<FoodOrderCategory_Data_BaseRes> response) {

                    FoodOrderCategory_Data_BaseRes baseresponse = response.body();

                    allUsersList = baseresponse.foodlist;
                    Log.e("Base response","response"+baseresponse.message);
                    if(allUsersList != null)
                    {
                        FoodOrderCategory_RecyclerAdapter adapter =new FoodOrderCategory_RecyclerAdapter(FoodOrderCategory_Items.this, allUsersList);
                        recyclerView.setAdapter(adapter);
                    }
                    else
                    {
                        Log.e("FoodOrderCategory_Item","Null");
                    }
            }
            @Override
            public void onFailure(Call<FoodOrderCategory_Data_BaseRes> call, Throwable t) {

                Log.e("api","onFailure : "+t.getLocalizedMessage());
            }
        });
    }
}