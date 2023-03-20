package com.example.loginapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodOrderCat_Post_Retrofit_Instance {

    public static FoodOrderCat_Post_Retrofit_Instance instance;

    FoodOrderCat_Post_API_Interface apiInterface;

    FoodOrderCat_Post_Retrofit_Instance(){
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://admin.p9bistro.com")
                .addConverterFactory(GsonConverterFactory.create())
                 .build();

        apiInterface = retrofit.create(FoodOrderCat_Post_API_Interface.class);
    }
    public static FoodOrderCat_Post_Retrofit_Instance getInstance()
    {
        if(instance == null)
        {
            instance =new FoodOrderCat_Post_Retrofit_Instance();
        }
        return instance;
    }
}