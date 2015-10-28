package com.example.c1284518.inventoryproject.controller.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.c1284518.inventoryproject.R;
import com.example.c1284518.inventoryproject.model.entities.Product;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by c1284518 on 28/10/2015.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    List<Product> listProduct;
    Activity context;

    public ProductAdapter(Activity context, List<Product> listProduct){
        this.context = context;
        this.listProduct = listProduct;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(context).inflate(R.layout.list_item_product, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(holder);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product current = listProduct.get(position);
        holder.textViewName.setText(current.getName());
        holder.textViewValue.setText(current.getValue().toString());
        holder.imageViewProductListImage.setImageURI(Uri.parse(current.getImage()));
//        Uri selectedImage = Uri.parse(current.getImage());
//        FileInputStream in = new FileInputStream()
//        InputStream imageStream = openInputStream(selectedImage);
//        Bitmap image = BitmapFactory.decodeStream(imageStream);
//        holder.imageViewImageProduct.setImageBitmap(image);
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewProductListImage;
        TextView textViewName;
        TextView textViewValue;


        public MyViewHolder(View itemView) {
            super(itemView);
            imageViewProductListImage = (ImageView) itemView.findViewById(R.id.imageViewProductListImage);
            textViewName = (TextView) itemView.findViewById(R.id.textViewProductListName);
            textViewValue = (TextView) itemView.findViewById(R.id.textViewProductListValue);
        }

    }

    public void setItens(List<Product> itens){
        listProduct.clear();
        listProduct.addAll(itens);
    }
}
