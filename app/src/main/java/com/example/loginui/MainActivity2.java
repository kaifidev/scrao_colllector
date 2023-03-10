package com.example.loginui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.loginui.databinding.ActivityMain2Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    DatabaseReference database ;
    FirebaseAuth firebaseAuth;
    ProgressDialog dialog;
    ActivityMain2Binding binding;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main2);
        Intiliazation();

    }

    private void Intiliazation() {

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://userinputbase-default-rtdb.firebaseio.com/").getReference().child("User_info");
        dialog  = new ProgressDialog(this);
        dialog.setTitle("User Account Creating");
        dialog.setMessage("please wait your account is creating...!!");
        binding.t2.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        });

       binding.btn14.setOnClickListener(view -> authData());
    }

    public  void authData(){
        String name = binding.e1.getText().toString();
        String email = binding.e2.getText().toString();
        String password = binding.e3.getText().toString();
        String cpass = binding.e4.getText().toString();

        Users users = new Users(name,email,password,cpass);




        if (name.equals("")){
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else if (email.equals("")){
            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else if (password.equals("")){
            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            dialog.dismiss();

        }
        else  if (cpass.equals("")){
            Toast.makeText(this, "Please Enter Your Confirm Password", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else  if (!cpass.equals(password)){
            YoYo.with(Techniques.Shake).delay(400).duration(2000).repeat(1).playOn(binding.e4);
            Toast.makeText(this, "Confirm password Doesn't Matched", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else{
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    database.push().setValue(users);
                    clearData();
                    Toast.makeText(MainActivity2.this, "Your Account Has been Created", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
            }).addOnFailureListener(e ->

                    Toast.makeText(MainActivity2.this, e.getMessage().toString() , Toast.LENGTH_LONG).show());
                      dialog.dismiss();
        }
    }
    public void clearData(){
        binding.e1.setText("");
        binding.e2.setText("");
        binding.e3.setText("");
        binding.e4.setText("");

    }

    }





