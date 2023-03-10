package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity5 extends AppCompatActivity {
    LottieAnimationView animationView;
    Handler handler = new Handler();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        animationView = findViewById(R.id.l1);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        },4000);
    }
}