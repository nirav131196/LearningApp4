package com.example.loginapp;

import java.io.Serializable;

public class List_Data_File implements Serializable {

    private final String name;


    public List_Data_File(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
