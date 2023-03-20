package com.example.loginapp;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FoodOrderCat_Post_API_Interface {


    @POST("/index.php/addFavouriteProduct")
    Call<FoodOrderCat_BaseResponseData> postitems(@Header("authorization") String token,@Header("api-key") String apikey);
}
