package com.example.loginui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class Detail_page extends AppCompatActivity {

    TextView detailedproductname,detailedproductprice,detailedproductdesc,showvalue,quantityaddtocard,priceaddtocart;
    MaterialButton increasment,decreament;
    ImageView detaillogoutbutton,detailimageview;
    FirebaseAuth firebaseAuth;
    private int value = 0;
    CardView cardView;
    int totalprice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        intialization();
    }

    private void intialization() {
        Intent intent = getIntent();

        detailedproductname = findViewById(R.id.detailedproductname);
        detailedproductprice= findViewById(R.id.detailedproductprice);
        detailedproductdesc= findViewById(R.id.detailedproductdesc);
        detaillogoutbutton = findViewById(R.id.detaillogoutbtn);
        detailimageview = findViewById(R.id.detailedimgview);
        showvalue = findViewById(R.id.showvalue);
        increasment = findViewById(R.id.increment);
        decreament = findViewById(R.id.decrement);
        cardView = findViewById(R.id.cardviewdeatiledcheckout);
        quantityaddtocard = findViewById(R.id.quantityaddtocart);
        priceaddtocart = findViewById(R.id.priceaddtocart);
        firebaseAuth = FirebaseAuth.getInstance();


        Picasso.get().load(intent.getStringExtra("img")).into(detailimageview);
        detailedproductname.setText(intent.getStringExtra("name"));
        detailedproductprice.setText(intent.getStringExtra("price"));
        detailedproductdesc.setText(intent.getStringExtra("desc"));
        totalprice = Integer.parseInt(intent.getStringExtra("price"));
         subpart();
         detaillogoutbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 firebaseAuth.signOut();
                 startActivity(new Intent(getApplicationContext(),MainActivity.class));
             }
         });



    }
    private  void subpart(){
        increasment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value ++ ;
                String valuee = String.valueOf(value);
                quantityaddtocard.setText(valuee);
                showvalue.setText(valuee);
                cardView.setVisibility(View.VISIBLE);
                int total  = totalprice*value;
                String stotal = String.valueOf(total);
                priceaddtocart.setText("$ " +total );


            }
        });
        decreament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value --;
                String valuee = String.valueOf(value);
                quantityaddtocard.setText(valuee);
                int total  = totalprice*value;
                String stotal = String.valueOf(total);
                priceaddtocart.setText("$ " +total );



              if (value > 0){
                  showvalue.setText(valuee);
                  cardView.setVisibility(View.VISIBLE);
              }

              else{
                  Toast.makeText(Detail_page.this, "Maximum Your Quantity Should be One", Toast.LENGTH_SHORT).show();
              }
            }
        });
    }
}