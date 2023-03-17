package com.example.loginapp;

import static com.example.loginapp.FoodOrderCategory_Retrofit_Instance.instance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class FoodOrderCategory_Items extends AppCompatActivity {

        public static String url = "https://admin.p9bistro.com/index.php";

        RecyclerView recyclerView;
        FoodOrderCategory_RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order_category_items);

        recyclerView = findViewById(R.id.FoodOrderIteams);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        instance.getInstance().apiInterface.getDetails().enqueue(new Callback<List<FoodOrderCategory_Data>>() {
            @Override
            public void onResponse(Call<List<FoodOrderCategory_Data>> call, Response<List<FoodOrderCategory_Data>> response) {

                List<FoodOrderCategory_Data> allUsersList = new ArrayList<>();
                allUsersList = response.body();

                for(int i=0;i<allUsersList.size();i++)
                {
                    String id = allUsersList.get(i).getId();
                    String name = allUsersList.get(i).getProduct_name();
                    String description = allUsersList.get(i).getDescription();
                    String price = allUsersList.get(i).getRate();
                    allUsersList.add(new FoodOrderCategory_Data(id,name,price,description));

                    Log.e("api","onResponse : "+allUsersList.get(i).getProduct_name()+allUsersList.get(i).getDescription()+allUsersList.get(i).getId()+allUsersList.get(i).getRate());
                }

                recyclerView.setAdapter(new FoodOrderCategory_RecyclerAdapter(FoodOrderCategory_Items.this,allUsersList));
                recyclerView.setLayoutManager(new LinearLayoutManager(FoodOrderCategory_Items.this));

            }
            @Override
            public void onFailure(Call<List<FoodOrderCategory_Data>> call, Throwable t) {

                Log.e("api","onFailure : "+t.getLocalizedMessage());
            }
        });
    }
}