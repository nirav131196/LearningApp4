package com.example.loginapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodOrderCategory_Data_BaseRes {

    String status;
    String message;

    @SerializedName("Product List")
    List<FoodOrderCategory_Data> foodlist;
}
