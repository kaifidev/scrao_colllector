package com.example.loginui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginui.databinding.ShowProductListBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Productadpter extends RecyclerView.Adapter<Productadpter.Myviewholder> {
    Context context;
    ArrayList<Product_model>arrayList;

    public Productadpter(Context context, ArrayList<Product_model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Productadpter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       ShowProductListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.show_product_list,parent,false);
        return new Myviewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Productadpter.Myviewholder holder, int position) {
        Product_model product_model = arrayList.get(position);
       holder.binding.setProducts(product_model);
        Picasso.get().load(product_model.getImageid()).into(holder.binding.showproductimage);
       holder.binding.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Detail_page.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("img" , product_model.getImageid());
                intent.putExtra("name" ,product_model.getProduct_name());
                intent.putExtra("price" ,product_model.getProduct_price());
                intent.putExtra("desc" ,product_model.getProduct_desc());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        ShowProductListBinding binding;

        public Myviewholder(@NonNull ShowProductListBinding showProductListBinding) {
            super(showProductListBinding.getRoot());

          this.binding = showProductListBinding;


        }
    }
}
