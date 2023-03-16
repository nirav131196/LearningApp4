package com.example.loginapp;

public class FavouriteData {

    private boolean selected;

    public FavouriteData(boolean selected) {
        this.selected = selected;
    }
    public  boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected =selected;
    }
}
