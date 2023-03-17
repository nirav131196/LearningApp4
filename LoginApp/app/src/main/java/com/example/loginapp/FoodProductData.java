package com.example.loginapp;

public class FoodProductData {

    String FoodDrinkImage;
    String FoodProductCatName;

    String FoodCategoryId;

    public FoodProductData(String foodProductCatName, String foodDrinkImage,String foodcategoryId) {

        this.FoodProductCatName = foodProductCatName;
        this.FoodDrinkImage = foodDrinkImage;
        this.FoodCategoryId = foodcategoryId;
    }
}
