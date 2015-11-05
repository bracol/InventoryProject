package com.example.c1284518.inventoryproject.controller.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c1284518.inventoryproject.R;
import com.example.c1284518.inventoryproject.controller.adapters.ProductAdapter;
import com.example.c1284518.inventoryproject.model.entities.Product;
import com.example.c1284518.inventoryproject.model.service.ProductService;
import com.example.c1284518.inventoryproject.util.async.FindAllAsync;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
//import com.melnykov.fab.FloatingActionButton;

/**
 * Created by Wanilton on 28/10/2015.
 */
public class InventoryListActivity extends AppCompatActivity {

    public static final String PARAM_PRODUCT = "PARAM";
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private Product product;
    List<Product> listProduct;
    private Button fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        bindFab();
        bindToolbar();
        bindListView();
    }


    private void bindFab() {
        fab = (Button) findViewById(R.id.fabAdd);
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
        atualizaListAsync();
        super.onResume();
    }

    private void updateListProduct() {
        listProduct = new ArrayList<>();
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
        recyclerView.addOnItemTouchListener(new RecycleTouchListener(InventoryListActivity.this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                ProductAdapter adapter = (ProductAdapter) recyclerView.getAdapter();
                product = adapter.getItem(position);
                createDialog();
            }
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(InventoryListActivity.this));
        registerForContextMenu(recyclerView);
    }

    private void bindToolbar() {
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

    }

    //FIM DA MANIPULACAO DE OBJETOS DO LAYOUT

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_generic, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    //INICIO MANIPULACAO MENUS


    //FIM MANIPULACAO MENUS

    //INICIO MANIPULACAO ASYNC

    public void atualizaListAsync(){
        new FindAllAsync() {

            ProgressDialog pdialog;
            @Override
            protected void onPreExecute() {
                pdialog = new ProgressDialog(InventoryListActivity.this);
                pdialog.setMessage("Updating List");
                pdialog.show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(List<Product> products) {
                updateListProduct();
                pdialog.dismiss();
                super.onPostExecute(products);
            }
        }.execute();
    }

    //FIM MANIPULACAO ASYNC

    public void createDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(InventoryListActivity.this);
        dialog.setTitle("Choose one");
        dialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent goToInventoryForm = new Intent(InventoryListActivity.this, InventoryFormActivity.class);
                goToInventoryForm.putExtra(PARAM_PRODUCT, product);
                startActivity(goToInventoryForm);
            }
        });
        dialog.setNegativeButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProductService.delete(product.get_id());
                updateListProduct();
            }

        });
        dialog.show();
    }




    public static class RecycleTouchListener implements RecyclerView.OnItemTouchListener {

        ClickListener clickListener;
        GestureDetector gestureDetector;

        public RecycleTouchListener(Context context, final RecyclerView rv, ClickListener cl) {
            this.clickListener = cl;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {

                    View child = rv.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, rv.getChildAdapterPosition(child));
                    }

                    super.onLongPress(e);
                }
            });

        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }
    }


    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }
}
