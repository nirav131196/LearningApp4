package com.example.loginapp;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodOrderCat_Post_Retrofit_Instance {

    public static FoodOrderCat_Post_Retrofit_Instance instance;


    FoodOrderCat_Post_API_Interface apiInterface;

    FoodOrderCat_Post_Retrofit_Instance(){

        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        final OkHttpClient okHttpClient = new OkHttpClient.Builder()

                .addInterceptor(loggingInterceptor)

                .protocols(Arrays.asList(Protocol.HTTP_1_1))

                .readTimeout(120, TimeUnit.SECONDS)

                .connectTimeout(120, TimeUnit.SECONDS)

                .build();

        Retrofit retrofit =new Retrofit.Builder().client(okHttpClient)
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