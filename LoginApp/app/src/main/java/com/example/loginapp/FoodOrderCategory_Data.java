package com.example.loginapp;

public class FoodOrderCategory_Data {

    String id;
    String product_name;
    String Rate;
    String Description;

    public FoodOrderCategory_Data(String id, String product_name, String rate, String description) {
        this.id = id;
        this.product_name = product_name;
        this.Rate = rate;
        this.Description = description;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
