package com.example.c1284518.inventoryproject.util.async;

import android.os.AsyncTask;

import com.example.c1284518.inventoryproject.model.entities.Product;
import com.example.c1284518.inventoryproject.model.service.ProductService;

import java.util.List;

/**
 * Created by c1284518 on 04/11/2015.
 */
public class FindAllAsync extends AsyncTask<Void, Void, List<Product>> {
    @Override
    protected List<Product> doInBackground(Void... params) {
        return ProductService.findAll();
    }
}
