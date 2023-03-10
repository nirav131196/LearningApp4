package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginapp.databinding.ActivityDashboardP9Binding;

public class Dashboard_Activity_p9 extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDashboardP9Binding binding;
    Button btnDrinkOrder,btnFoodOrder;
    TextView txtViewEvents,txtViewSocialMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardP9Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        ClickEventDrinkOrder();

        // BOTTOM NAVIGATION VIEW
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);

        //set order id selected
     /*   bottomNavigationView.setSelectedItemId(R.id.orderlist);
        bottomNavigationView.setSelectedItemId(R.id.favourite);
        bottomNavigationView.setSelectedItemId(R.id.history);
        bottomNavigationView.setSelectedItemId(R.id.notification);*/
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case  R.id.orderlist:
                        Intent i =new Intent(Dashboard_Activity_p9.this,OrderList_Activity.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.favourite:
                        Intent i2 =new Intent(Dashboard_Activity_p9.this,Favourite_Activity.class);
                        startActivity(i2);
                        finish();
                        return true;
                    case R.id.history:
                        Intent i3 =new Intent(Dashboard_Activity_p9.this,OrderHistory.class);
                        startActivity(i3);
                        finish();
                        return true;
                    case R.id.notification:
                        Intent i4 =new Intent(Dashboard_Activity_p9.this,Notification_Activity.class);
                        startActivity(i4);
                        finish();
                        return true;

                }
                return false;
            }
        });

        setSupportActionBar(binding.appBarDashboardActivityP9.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboard_activity_p9);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.nav_order:
                        Intent i2 =new Intent(Dashboard_Activity_p9.this,OrderList_Activity.class);
                        startActivity(i2);
                        finish();
                        return true;
                    case R.id.nav_history:
                        Intent i3 =new Intent(Dashboard_Activity_p9.this,OrderHistory.class);
                        startActivity(i3);
                        finish();
                        return true;
                    case R.id.nav_favourite:
                        Intent i4 =new Intent(Dashboard_Activity_p9.this,Favourite_Activity.class);
                        startActivity(i4);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }

    private void ClickEventDrinkOrder() {
        btnDrinkOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Dashboard_Activity_p9.this, DrinkOrderMenu_Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void initView()
    {
        btnDrinkOrder = (Button)findViewById(R.id.Drink_Order_button);
        btnFoodOrder = (Button)findViewById(R.id.Food_Order_button);
        txtViewEvents = (TextView)findViewById(R.id.TextView_Viewmore);
        txtViewSocialMedia = (TextView)findViewById(R.id.TextView_Viewmore_SM);
    }
    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard__activity_p9, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.action_settings:
                Intent i =new Intent(Dashboard_Activity_p9.this,LoginActivity.class);
                startActivity(i);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboard_activity_p9);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}