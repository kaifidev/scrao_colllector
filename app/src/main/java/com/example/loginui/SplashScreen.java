package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashScreen extends AppCompatActivity {
LottieAnimationView animationView;
TextView t1;
Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        animationView = findViewById(R.id.cycle);
        t1 = findViewById(R.id.t1);
        YoYo.with(Techniques.FadeIn).delay(500).duration(3000).repeat(0).playOn(t1);
        YoYo.with(Techniques.FadeIn).duration(2000).repeat(0).playOn(animationView);
        animationView.animate().setDuration(7000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        },8000);
    }
}