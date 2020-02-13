package com.example.clubcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.podcopic.animationlib.library.AnimationType;
import com.podcopic.animationlib.library.StartSmartAnimation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                findViewById(R.id.icon).setVisibility(View.VISIBLE);
            }
        }, 500);

        //It adds animation to the logo.
        StartSmartAnimation.startAnimation( findViewById(R.id.icon) , AnimationType.DropOut , 2000 , 100 , true );

        int SPLASH_TIME_OUT = 4000;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(MainActivity.this, userChoiceActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
