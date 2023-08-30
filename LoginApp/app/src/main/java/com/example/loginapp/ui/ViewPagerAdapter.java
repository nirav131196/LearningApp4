package com.example.loginapp.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.loginapp.AlgorithmFragment;
import com.example.loginapp.CourseFragment;
import com.example.loginapp.LoginFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        if(position == 0)
        {
            fragment = new AlgorithmFragment();
        }
        else if(position == 1)
        {
            fragment = new CourseFragment();
        }
        else if(position == 2)
        {
            fragment = new LoginFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "Algorithm";
        else if (position == 1)
            title = "Courses";
        else if (position == 2)
            title = "Login";
        return title;
    }
}
