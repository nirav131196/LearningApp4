package com.example.loginapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodOrderCat_BaseResponseData {

    String status;
    String message;

    @SerializedName("data")
    FoodOrderCat_POST_Fav_Data favFoodItem;
}
