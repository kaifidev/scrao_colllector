package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;

public class MainActivity4 extends AppCompatActivity {
    TextInputEditText ef;
    Button button;
    ImageView img2;
    FrameLayout frameLayout;
    DatabaseReference database;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        img2 = findViewById(R.id.img2);
        YoYo.with(Techniques.FadeIn).duration(2000).repeat(0).playOn(img2);
        ef = findViewById(R.id.ef);
        frameLayout = findViewById(R.id.fmly2);
        YoYo.with(Techniques.FadeIn).duration(2000).repeat(0).playOn(frameLayout);
        button = findViewById(R.id.btn13);
        database = FirebaseDatabase.getInstance().getReference().child("User_Email");
        button.setOnClickListener(v -> emailVerify_data());
    }
    public void emailVerify_data(){
    String vEmail = ef.getText().toString();

        if (vEmail.isEmpty()){
            YoYo.with(Techniques.Shake).duration(1000).repeat(1).playOn(ef);
            Toast.makeText(MainActivity4.this, "Enter Your Email!", Toast.LENGTH_SHORT).show();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(vEmail).matches()){
            YoYo.with(Techniques.Shake).duration(1000).repeat(1).playOn(ef);
            Toast.makeText(MainActivity4.this, "Invalid Email Address!", Toast.LENGTH_SHORT).show();
        }
        else{

            Toast.makeText(MainActivity4.this, "Verification is sent!", Toast.LENGTH_SHORT).show();
            Emailverifycls emailverifycls =new Emailverifycls(vEmail);
            database.setValue(emailverifycls);
            empty_clear();

        }



    }
    public  void empty_clear(){
        ef.setText("");
    }
}