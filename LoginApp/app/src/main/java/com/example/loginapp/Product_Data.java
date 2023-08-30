package com.example.loginapp;

public class Product_Data {

    /*String product_id;

    String department_id;*/
    String category_id;
    String cat_name;
    String image;

    public Product_Data(String cat_name,String image,String category_id) {
       /* this.product_id = product_id;

        this.department_id = department_id;*/
        this.cat_name = cat_name;
        this.image = image;
        this.category_id = category_id;
    }
}
