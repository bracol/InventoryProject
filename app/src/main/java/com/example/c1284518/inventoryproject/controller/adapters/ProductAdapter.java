package com.example.c1284518.inventoryproject.controller.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c1284518.inventoryproject.R;
import com.example.c1284518.inventoryproject.controller.activities.InfoActivity;
import com.example.c1284518.inventoryproject.controller.activities.InventoryListActivity;
import com.example.c1284518.inventoryproject.model.entities.Product;
import com.example.c1284518.inventoryproject.model.service.ProductService;
import com.example.c1284518.inventoryproject.util.ImageManager;

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
            if (current.getImage() == null || current.getImage().equals("")) {
                holder.imageViewProductListImage.setImageResource(R.mipmap.ic_image_photo);
            } else {
                ImageManager.imageSet(holder.imageViewProductListImage, current.getImage(), context);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (position % 2 == 0) {
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.color1card));
        } else {
            holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.color2card));
        }

        //holder.imageViewProductListImage.setImageBitmap(ImageManager.decodeSampledBitmapFromResource(context, uri, holder.imageViewProductListImage.getWidth(), holder.imageViewProductListImage.getHeight()));
        //ImageManager.imageSet(holder.imageViewProductListImage, uri, context);


    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public Product getItem(int position) {
        return listProduct.get(position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageViewProductListImage;
        TextView textViewName;
        TextView textViewValue;
        TextView textViewEdit;
        TextView textViewRemove;
        CardView cardView;
        RelativeLayout relativeLayout;


        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayoutPrincipal);
            imageViewProductListImage = (ImageView) itemView.findViewById(R.id.imageViewProductListImage);
            textViewName = (TextView) itemView.findViewById(R.id.textViewProductListName);
            textViewValue = (TextView) itemView.findViewById(R.id.textViewProductListValue);
            textViewRemove = (TextView) itemView.findViewById(R.id.textViewProductListRemove);
            textViewEdit = (TextView) itemView.findViewById(R.id.textViewProductListEdit);
            textViewRemove.setOnClickListener(this);
            imageViewProductListImage.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageViewProductListImage:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Intent intent = new Intent(context, InfoActivity.class);
                        Pair<View, String> pair1 = Pair.create((View) imageViewProductListImage, imageViewProductListImage.getTransitionName());
                        Pair<View, String> pair2 = Pair.create((View) textViewName, textViewName.getTransitionName());
                        Pair<View, String> pair3 = Pair.create((View) textViewValue, textViewValue.getTransitionName());
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(context, pair1, pair2, pair3);
                        intent.putExtra("algo", listProduct.get(getAdapterPosition()));
                        context.startActivity(intent, options.toBundle());
                        break;
                    }
                case R.id.textViewProductListRemove:
                    deleteProduct();
                    break;
            }
        }

        private void deleteProduct() {
            ProductService.delete(getItem(getAdapterPosition()).get_id());
            listProduct.remove(getItem(getAdapterPosition()));
            notifyItemRemoved(getAdapterPosition());
        }
    }

    public void setItens(List<Product> itens) {
        listProduct.clear();
        listProduct.addAll(itens);
    }
}
