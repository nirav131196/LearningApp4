package com.example.loginapp;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FoodOrderCat_Post_API_Interface {


    @POST("/index.php/addFavouriteProduct")
    Call<FoodOrderCat_BaseResponseData> postitems(@Header("authorization") String token, @Header("api-key") String apikey, @Body JsonObject jsonObject);


    @POST("/index.php/removeFavouriteProduct")
    Call<FoodOrderCat_RemovalBaseReData> getHeaders(@Header("authorization") String token,@Header("api-key") String apikey,@Body JsonObject jsonObject);
}
