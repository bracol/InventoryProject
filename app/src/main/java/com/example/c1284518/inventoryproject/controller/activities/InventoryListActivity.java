package com.example.c1284518.inventoryproject.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.c1284518.inventoryproject.R;
import com.example.c1284518.inventoryproject.controller.adapters.ProductAdapter;
import com.example.c1284518.inventoryproject.model.entities.Product;
import com.example.c1284518.inventoryproject.model.persistence.product.ProductRepository;
import com.example.c1284518.inventoryproject.model.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import com.melnykov.fab.FloatingActionButton;

/**
 * Created by Wanilton on 28/10/2015.
 */
public class InventoryListActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private Product product;
    List<Product> listProduct;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        bindFab();
        bindToolbar();
        bindListView();
    }

    private void bindFab() {
        fab = (FloatingActionButton) findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMainActivity = new Intent(InventoryListActivity.this, InventoryFormActivity.class);
                startActivity(goToMainActivity);
            }
        });
    }

    @Override
    protected void onResume() {
        updateListProduct();
        super.onResume();
    }

    private void updateListProduct() {
        listProduct = ProductService.findAll();
        ProductAdapter adapter = (ProductAdapter) recyclerView.getAdapter();
        adapter.setItens(listProduct);
        adapter.notifyDataSetChanged();
    }

    //INICIO DA MANIPULACAO DE OBJETOS DO LAYOUT
    private void bindListView() {
        listProduct = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclewViewProduct);
        recyclerView.setAdapter(new ProductAdapter(InventoryListActivity.this, listProduct));
        recyclerView.setLayoutManager(new LinearLayoutManager(InventoryListActivity.this));
    }

    private void bindToolbar() {
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //FIM DA MANIPULACAO DE OBJETOS DO LAYOUT


}
