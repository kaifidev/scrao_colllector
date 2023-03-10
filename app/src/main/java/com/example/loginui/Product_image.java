package com.example.loginui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.loginui.databinding.ActivityProductImageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Product_image extends AppCompatActivity {

    DatabaseReference firebaseDatabase;
    ProgressDialog progressDialog;
    StorageReference reference;
    Uri imageuri;
    ActivityProductImageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_product_image);
        intialization();
    }

    private void intialization() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Upload Products");
        progressDialog.setMessage("Please wait your product is adding...!!");

        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Product_detail");
        reference = FirebaseStorage.getInstance().getReference().child("Images");

        binding.imageproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenimageGallery();
            }
        });
        binding.productbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {if (imageuri != null){
                progressDialog.show();
                UploadToFirebase(imageuri);
                
            }
            else {
                Toast.makeText(Product_image.this, "Please Select An Image", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
            }
        });

    }



    private void data_Transfer(Uri imageuri) {
        String Product_name = binding.productname.getText().toString();
        String Product_desc =binding.productdesc.getText().toString();
        String Product_date = binding.productdate.getText().toString();
        String Product_price = binding.productprice.getText().toString();
        String Product_quantity = binding.productquantity.getText().toString();
        if (Product_name.equals("")){
            Toast.makeText(this, "Enter Your Product Name", Toast.LENGTH_SHORT).show();

            
        }
        else if (Product_desc.equals("")){
            Toast.makeText(this, "Enter your Product Description", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
        else if (Product_date.equals("")){
            Toast.makeText(this, "Enter Your Product Date", Toast.LENGTH_SHORT).show();


        }
        else  if (Product_price.equals("")){
            Toast.makeText(this, "Enter Your Product Price", Toast.LENGTH_SHORT).show();

        }
        else  if (Product_quantity.equals("")){
            Toast.makeText(this, "Enter Your Product Quantity", Toast.LENGTH_SHORT).show();

        }
        else{
           
            Product_model product_model = new Product_model(Product_name,Product_desc,Product_date,Product_price,Product_quantity,imageuri.toString());
            firebaseDatabase.push().setValue(product_model);
            Toast.makeText(this, "Product Added Successfully", Toast.LENGTH_SHORT).show();
            clear();



        }


    }
    private void OpenimageGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,2);
    }
    private void clear(){
        binding.productname.setText("");
        binding.productdesc.setText("");
        binding.productdate.setText("");
        binding.productprice.setText("");
        binding.productquantity.setText("");
        binding.imageproduct.setImageResource(R.drawable.ic_baseline_add_photo_alternate_24);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==2 && resultCode == RESULT_OK && data != null){
            imageuri = data.getData();
            binding.imageproduct.setImageURI(imageuri);}
    }

    private  void UploadToFirebase(Uri uri){
        final StorageReference fileref = reference.child(System.currentTimeMillis()+"."+getFileExtension(uri));
        fileref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        data_Transfer(uri);
                        progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Product_image.this, "Uploading failed !!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        
    }
    private String getFileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));}
}

    