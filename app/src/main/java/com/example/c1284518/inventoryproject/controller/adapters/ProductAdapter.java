package com.example.c1284518.inventoryproject.controller.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.c1284518.inventoryproject.R;
import com.example.c1284518.inventoryproject.model.entities.Product;

import java.util.List;

/**
 * Created by c1284518 on 28/10/2015.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    List<Product> listProduct;
    Context context;

    public ProductAdapter(Context context, List<Product> listProduct){
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
        current.setName(current.getName());
        current.setValue(current.getValue());
        current.setImage(current.getImage());
        MyViewHolder.setaImagem(current.getImage());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        static ImageView imageViewImageProduct;
        TextView textViewName;
        TextView textViewValue;


        public MyViewHolder(View itemView) {
            super(itemView);
            imageViewImageProduct= (ImageView) itemView.findViewById(R.id.imageViewProductInsert);
            textViewName = (TextView) itemView.findViewById(R.id.textViewProductListName);
            textViewValue = (TextView) itemView.findViewById(R.id.textViewProductListValue);
        }

        public static void setaImagem(String path){
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            imageViewImageProduct.setImageBitmap(bitmap);
        }
    }
}
