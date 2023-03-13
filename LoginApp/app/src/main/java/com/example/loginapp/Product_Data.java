package com.example.loginapp;

public class Product_Data {

    String product_id;
    String category_id;
    String department_id;
    String cat_name;
    String image;



    public Product_Data(String product_id, String category_id, String department_id, String cat_name,String image) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.department_id = department_id;
        this.cat_name = cat_name;
        this.image = image;
    }
}
