package com.example.loginapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerMyAdapter extends FragmentPagerAdapter {

    // creating a string array for tab title.
    private final String[] mTitles;

    // creating an array list on below line for fragments.
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    // constructor for view pager
    public ViewPagerMyAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, String[] mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    // returning the size of our list.
    @Override
    public int getCount() {
        return mFragments.size();
    }

    // below method is to set the title for tab layout item.
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    // below method is use to get the item.
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

}
