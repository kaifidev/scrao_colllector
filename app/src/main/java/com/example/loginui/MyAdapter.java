package com.example.loginui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.example.loginui.databinding.ListBinding;

import java.util.List;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.Myviewholder> {
    Context context;
    List<Users> list;

    public MyAdapter(Context context, List<Users> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       ListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.list,parent,false);
        return new Myviewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.Myviewholder holder, int position) {
        Users users = list.get(position);
        holder.binding.setUsers(users);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        ListBinding binding;

        public Myviewholder(@NonNull ListBinding listBinding) {
            super(listBinding.getRoot());
            this.binding = listBinding;


        }
    }
}
