package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminPage extends AppCompatActivity {
Button admin1,admin_product;
FirebaseAuth firebaseAuth;
ImageView adminLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        Initialization();
    }
    private void Initialization(){
        adminLogout = findViewById(R.id.adminlogoutbtn);
        admin1 =findViewById(R.id.adminshow);
        admin_product = findViewById(R.id.add_product);
        firebaseAuth = FirebaseAuth.getInstance();
        admin_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Product_image.class));
            }
        });
        admin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(),MainActivity3.class));

            }
        });

        adminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


    }

    }
