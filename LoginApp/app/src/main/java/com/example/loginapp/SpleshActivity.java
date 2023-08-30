package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SpleshActivity extends AppCompatActivity {

    ImageView mImage, mImageText;
    Animation mAnimation, mAnimation1;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh);

        mImage= (ImageView) findViewById(R.id.imgLogo);
        mAnimation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.together);
        mImage.startAnimation(mAnimation);

        mImageText= (ImageView) findViewById(R.id.imgText);
        mAnimation1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bottom_animation);
        mImageText.startAnimation(mAnimation1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SpleshActivity.this, DialogBox.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}