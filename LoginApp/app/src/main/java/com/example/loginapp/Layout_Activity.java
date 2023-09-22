package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;

public class Layout_Activity extends AppCompatActivity {

    // creating variables for coordinator layout
    private CoordinatorTabLayout mCoordinatorTabLayout;

    // creating an integer array for images and colors.
    private int[] mImageArray, mColorArray;

    // creating array list for our fragments.
    private ArrayList<Fragment> mFragments;

    // creating a string array for our tab title in tab layout.
    private final String[] mTitles = {"DSA", "Java", "C++", "Python"};

    // creating a variable for view pager.
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        // calling method to initialize
        // our fragments and view pager.
        initFragments();
        initViewPager();

        // adding images to our int array on below line.
        mImageArray = new int[]{
                R.drawable.cancel_sign4,
                R.drawable.cancel_sign4,
                R.drawable.cancel_sign4,
                R.drawable.cancel_sign4};

        // adding color to our color array on below line.
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light};

        // initializing our coordinator layout
        mCoordinatorTabLayout = findViewById(R.id.coordinatortablayout);

        // on below line we are setting title to our tool bar
        // and image array, color array and view pager
        // to our coordinator layout
        mCoordinatorTabLayout.setTranslucentStatusBar(this)
                .setTitle("Geeks For Geeks")
                .setBackEnable(true)
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(mViewPager);
    }

    private void initFragments() {

        // initializing our array list.
        mFragments = new ArrayList<>();

        // adding fragment to our array list on below line.
        for (String title : mTitles) {
            mFragments.add(HomeFragment.getInstance(title));
        }
    }

    private void initViewPager() {
        // initializing our view pager and setting adapter to it.
        mViewPager = findViewById(R.id.vp);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new ViewPagerMyAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }
}