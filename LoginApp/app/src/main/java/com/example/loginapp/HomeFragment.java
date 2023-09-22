package com.example.loginapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // creating a variables on below line.
    private static final String ARG_TITLE = "title";
    private RecyclerView mRecyclerView;
    private List<String> courseList;
    private String mTitle;

    public HomeFragment() {
        // Required empty public constructor
    }

    // creating a get instance method to get the data which we passed.
    public static HomeFragment getInstance(String title) {
        HomeFragment fra = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        fra.setArguments(bundle);
        return fra;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // on below line we are getting the data from bundles.
        Bundle bundle = getArguments();
        mTitle = bundle.getString(ARG_TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home2, container, false);
        // below method is use to load data in our array list.
        initData(mTitle);
        // initializing our variables for views.
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new RecyclerAdapter2(mRecyclerView.getContext(), courseList));
        return view;
    }

    private void initData(String heading) {
        // running a switch case to add data to our array list.
        switch (heading) {
            case "DSA":
                courseList = new ArrayList<>();
                courseList.add("Arrays");
                courseList.add("Recursion");
                courseList.add("Hashing");
                courseList.add("Binary Search Trees");
                courseList.add("Searching");
                courseList.add("Sorting");
                break;
            case "C++":
                courseList = new ArrayList<>();
                courseList.add("Variables");
                courseList.add("Data Types");
                courseList.add("Type Conversion");
                courseList.add("Operators");
                courseList.add("Sorting");
                courseList.add("C++ Syntax");
                courseList.add("Pointers");
                break;
            case "Java":
                courseList = new ArrayList<>();
                courseList.add("Intro to Java");
                courseList.add("Object Oriented Concepts");
                courseList.add("Variables");
                courseList.add("Conditional and Control Flow");
                courseList.add("Array List");
                break;
            default:
                courseList = new ArrayList<>();
                courseList.add("Basic Concepts of Python");
                courseList.add("Program Flow Control in Python");
                courseList.add("Lists and Tuples");
                courseList.add("Functions");
                courseList.add("Python Dictionaries and Sets");
                break;
        }
    }
}