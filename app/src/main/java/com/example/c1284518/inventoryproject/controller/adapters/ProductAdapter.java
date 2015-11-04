package com.example.c1284518.inventoryproject.controller.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.c1284518.inventoryproject.R;
import com.example.c1284518.inventoryproject.util.ImageManager;
import com.example.c1284518.inventoryproject.model.entities.Product;

import java.io.IOException;
import java.util.List;

/**
 * Created by c1284518 on 28/10/2015.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    List<Product> listProduct;
    Activity context;

    public ProductAdapter(Activity context, List<Product> listProduct) {
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
        try {
            Product current = listProduct.get(position);
            holder.textViewName.setText(current.getName());
            holder.textViewValue.setText(current.getValue().toString());
            ImageManager.imageSet(holder.imageViewProductListImage, current.getImage(), context);
            if (current.getImage() == null || current.getImage().equals("")){
                holder.imageViewProductListImage.setImageResource(R.mipmap.ic_image_photo);
            } else {
                ImageManager.imageSet(holder.imageViewProductListImage, current.getImage(), context);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //holder.imageViewProductListImage.setImageBitmap(ImageManager.decodeSampledBitmapFromResource(context, uri, holder.imageViewProductListImage.getWidth(), holder.imageViewProductListImage.getHeight()));
        //ImageManager.imageSet(holder.imageViewProductListImage, uri, context);


    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public Product getItem(int position){
        return listProduct.get(position);
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewProductListImage;
        TextView textViewName;
        TextView textViewValue;
        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            imageViewProductListImage = (ImageView) itemView.findViewById(R.id.imageViewProductListImage);
            textViewName = (TextView) itemView.findViewById(R.id.textViewProductListName);
            textViewValue = (TextView) itemView.findViewById(R.id.textViewProductListValue);
        }

        public void animation(){
//            ImageView placeImage = (ImageView) v.findViewById(R.id.imageViewProductListImage);
//// 2
//            Pair<View, String> imagePair = Pair.create((View) placeImage, "tImage");
//            Pair<View, String> holderPair = Pair.create((View) placeNameHolder, "tNameHolder");
//// 3
//            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
//                    imagePair, holderPair);
//            ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
        }
    }

    public void setItens(List<Product> itens) {
        listProduct.clear();
        listProduct.addAll(itens);
    }
}
