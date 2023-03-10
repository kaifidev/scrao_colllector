package com.example.loginui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.accounts.AccountManagerFuture;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.loginui.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ProgressDialog dialog;
    ActivityMainBinding binding;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        Initialization();
    }

    private void Initialization() {
        firebaseAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setTitle("User Loging In");
        dialog.setMessage("please wait for a while..!");
       binding.iconimg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        binding.forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity4.class));

            }
        });
        binding.logintosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                finish();
            }
        });
        binding.btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authentdata();

            }
        });
    }

    public  void authentdata(){
        String email = binding.el1.getText().toString();
        String password = binding.el2.getText().toString();
        dialog.show();


        if (email.equals("Admin@gmail.com") && password.equals("123456")){
            startActivity(new Intent(getApplicationContext(),AdminPage.class));
            finish();
            dialog.dismiss();
        }
        if (email.equals("")){
            Toast.makeText(this, "Enter Your Email", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else if (password.equals("")){
            Toast.makeText(this, "Enter Your Password", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }
        else{
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(MainActivity.this, "User loged In", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        startActivity(new Intent(getApplicationContext(),User_home_page.class));
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage().toString() , Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),User_home_page.class));
        }
    }
    private  void clear(){
       binding.el1.setText("");
       binding.el2.setText("");
    }


    }
