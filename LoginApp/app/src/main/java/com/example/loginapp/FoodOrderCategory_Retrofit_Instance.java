package com.example.loginapp;

import static com.example.loginapp.FoodOrderCategory_Items.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodOrderCategory_Retrofit_Instance {

    public static FoodOrderCategory_Retrofit_Instance instance;
    FoodOrderCategory_API_Interface apiInterface;

    FoodOrderCategory_Retrofit_Instance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(FoodOrderCategory_API_Interface.class);
    }
    public static FoodOrderCategory_Retrofit_Instance getInstance()
    {
        if(instance == null)
        {
            instance = new FoodOrderCategory_Retrofit_Instance();
        }
        return instance;
    }

}
