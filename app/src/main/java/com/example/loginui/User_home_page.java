package com.example.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.loginui.databinding.ActivityUserHomePageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User_home_page extends AppCompatActivity {
  ActivityUserHomePageBinding binding;
    FirebaseAuth firebaseAuth;
    DatabaseReference firebaseDatabase;
    ArrayList<Product_model>arrayList;
    Productadpter productadpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_home_page);
        initialization();
    }


    private  void initialization(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Product_detail");
        arrayList = new ArrayList<>();
        binding.userhomelogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

       binding.recycler2.setLayoutManager(new GridLayoutManager(this,2));
        binding.recycler2.setHasFixedSize(true);
        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product_model product_model = dataSnapshot.getValue(Product_model.class);
                    arrayList.add(product_model);
                }
                productadpter = new Productadpter(getApplicationContext(),arrayList);
                binding.recycler2.setAdapter(productadpter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}