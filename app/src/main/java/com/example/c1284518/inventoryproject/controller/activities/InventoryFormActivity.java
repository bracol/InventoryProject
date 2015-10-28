package com.example.c1284518.inventoryproject.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.c1284518.inventoryproject.R;
import com.example.c1284518.inventoryproject.model.entities.Product;
import com.example.c1284518.inventoryproject.model.service.ProductService;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by c1284518 on 28/10/2015.
 */

//INDICE
    //FUNCOES DA ACTIVITY
    //FUNCOES DE MANIPULACAO DE LAYOUT
    //FUNCOES DE MANIPULACAO DE MENU
    //BIND DE OBJETOS

public class InventoryFormActivity extends AppCompatActivity {

    private TextView editTextName;
    private TextView editTextValue;
    private ImageView imageViewProduct;
    private Button buttonImageInsert;
    private Product product;
    private Toolbar toolbar;
    Uri selectedImage;
    Uri caminhoArquivo;


    //INICIO FUNCOES DA ACTIVITY
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);
        initProduct();

        bindEditTextName();
        bindEditTextValue();
        bindImageViewProduct();
        bindButtonImageInsert();
        bindToolbar();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 123){
            if(resultCode == Activity.RESULT_OK){
                selectedImage = data.getData();
                imageViewProduct.setImageURI(selectedImage);
            }
        }
    }

    //FIM DA FUNCOES DA ACTIVITY

    //INICIO DE MANIPULACAO DE OBJETOS DE LAYOUT

    private void bindImageViewProduct() {
        imageViewProduct = (ImageView) findViewById(R.id.imageViewProductInsert);
    }

    private void bindToolbar() {
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
    }

    public void bindEditTextName(){
        editTextName = (TextView) findViewById(R.id.editTextProductName);
    }

    public void bindEditTextValue(){
        editTextValue = (TextView) findViewById(R.id.editTextProductValue);
    }

    public void bindProduct(){
        product.setName(editTextName.getText().toString());
        product.setValue(editTextValue.getText().toString().equals("") ? 0 : Double.parseDouble(editTextValue.getText().toString()));
        product.setImage(selectedImage == null ? "" : selectedImage.toString());

    }

    private void bindButtonImageInsert() {
        buttonImageInsert = (Button) findViewById(R.id.buttonProductFormImageInsert);
        buttonImageInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                /*caminhoArquivo = Environment.getExternalStorageDirectory().toString()+"/"+System.currentTimeMillis()+".png";
                File arquivo = new File(caminhoArquivo);
                Uri localArquivo = Uri.fromFile(arquivo);
                irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localArquivo);*/
                startActivityForResult(goToGallery, 123);
            }
        });
    }

    //FIM DA MANIPULACAO DE OBJETOS DE LAYOUT

    //INICIO DO BIND DE OBJETOS DE CLASSE

    private void initProduct() {
        product = product == null ? new Product() : this.product;
    }

    //FIM DO BIND DE OBJETOS DE CLASSE

    //INICIO MANIPULACAO DE MENUS

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_productForm_salvar:
                onMenuSave();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuSave() {
        bindProduct();
        ProductService.save(product);
        finish();
    }

    //FIM MANIPULACAO DE MENUS


}
