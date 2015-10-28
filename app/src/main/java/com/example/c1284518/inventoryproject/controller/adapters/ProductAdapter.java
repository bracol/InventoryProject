package com.example.c1284518.inventoryproject.controller.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.c1284518.inventoryproject.R;

/**
 * Created by c1284518 on 28/10/2015.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewImageProduct;
        TextView textViewName;
        TextView textViewValue;


        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.imageViewProductInsert);
        }
    }
}
