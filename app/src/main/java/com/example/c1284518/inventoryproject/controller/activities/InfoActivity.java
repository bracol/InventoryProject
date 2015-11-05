package com.example.c1284518.inventoryproject.controller.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.c1284518.inventoryproject.R;
import com.example.c1284518.inventoryproject.controller.adapters.GenericAdapterNormal;
import com.example.c1284518.inventoryproject.model.entities.Product;
import com.example.c1284518.inventoryproject.model.service.GenericService;
import com.example.c1284518.inventoryproject.util.ImageManager;

import java.io.IOException;

/**
 * Created by c1284518 on 05/11/2015.
 */
public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ImageView imageView = (ImageView) findViewById(R.id.imageViewProductInfo);
        TextView textViewName = (TextView) findViewById(R.id.textViewProductNameInfo);
        TextView textViewValue = (TextView) findViewById(R.id.textViewProductNameValue);
        ListView listView = (ListView) findViewById(R.id.listViewInfoGeneric);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bundle bundle = getIntent().getExtras();
            Product product = bundle.getParcelable("algo");
            imageView.setTransitionName("imageTrans");
            textViewName.setTransitionName("nameTrans");
            textViewValue.setTransitionName("valueTrans");
            try {
                ImageManager.imageSet(imageView, product.getImage(), InfoActivity.this);
                textViewName.setText(product.getName());
                textViewValue.setText(product.getValue().toString());
                listView.setAdapter(new GenericAdapterNormal(this, GenericService.findAll(product.get_id())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
