package com.example.loginapp;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.loginapp.FoodOrderCategory_Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface FoodOrderCategory_API_Interface {

    @GET("/index.php/getSubCateogryProductList?deptids=[2]&sub_id=0")

    Call<FoodOrderCategory_Data_BaseRes> getDetails(@Header("authorization") String token, @Query("cat_id")String id);

}

