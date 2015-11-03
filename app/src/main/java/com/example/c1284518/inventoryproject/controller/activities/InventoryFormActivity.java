package com.example.c1284518.inventoryproject.controller.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c1284518.inventoryproject.R;
import com.example.c1284518.inventoryproject.controller.adapters.GenericAdapter;
import com.example.c1284518.inventoryproject.controller.adapters.GenericAdapterNormal;
import com.example.c1284518.inventoryproject.model.ImageManager;
import com.example.c1284518.inventoryproject.model.entities.Generico;
import com.example.c1284518.inventoryproject.model.entities.Product;
import com.example.c1284518.inventoryproject.model.persistence.generic.GenericRepository;
import com.example.c1284518.inventoryproject.model.service.GenericService;
import com.example.c1284518.inventoryproject.model.service.ProductService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

    public static final String PARAM_PRODUCT = "PARAM";
    private TextView editTextName;
    private TextView editTextValue;
    private ImageView imageViewProduct;
    private Button buttonImageInsert;
    private Product product;
    private Toolbar toolbar;
    private List<Generico> listTotal;
    private List<Generico> listFormGeneric;
    private Button buttonAddGeneric;
    private TextView textViewGenericList;
    private Spinner spinner;
    private InputStream inputStream;


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
        bindListGeneric();
        bindButtonGeneric();
        bindSpinnerGeneric();
        bindInputStream();
    }

    private void bindInputStream() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                //               try {
                try {
                    Uri uri = data.getData();
                    String path;
                    path = ImageManager.getImagePath(InventoryFormActivity.this, uri);
                    //path = getRealPathFromURI(InventoryFormActivity.this, uri);
                    ImageManager.imageSet(imageViewProduct, path, InventoryFormActivity.this);
                    product.setImage(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
////                    Bitmap image = ImageManager.decodeSampledBitmapFromResource(InventoryFormActivity.this, uri, imageViewProduct.getWidth(), imageViewProduct.getHeight());
////                    ImageManager.imageSet(imageViewProduct, uri, InventoryFormActivity.this);
////                    selectedImage = uri.toString();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    @Override
    protected void onResume() {
        updateGenericList();
        super.onResume();
    }

    //FIM DA FUNCOES DA ACTIVITY

    //INICIO DE MANIPULACAO DE OBJETOS DE LAYOUT


    private void bindSpinnerGeneric() {
        spinner = (Spinner) findViewById(R.id.spinnerListProdutFormGeneric);
        spinner.setPrompt("Generic");
        spinner.setAdapter(new GenericAdapterNormal(InventoryFormActivity.this, listTotal));
        registerForContextMenu(spinner);
        //textViewGenericList.setAdapter((SpinnerAdapter) new GenericAdapter(listTotal, InventoryFormActivity.this));
    }

    private void bindButtonGeneric() {
        buttonAddGeneric = (Button) findViewById(R.id.buttonGenericForm);

        buttonAddGeneric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(InventoryFormActivity.this);
                final EditText input = new EditText(InventoryFormActivity.this);
                alert.setTitle("Generic");
                alert.setView(input);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString().trim();
                        Generico generic = new Generico();
                        generic.setValor(value);
                        addListas(generic);
                        updateGenericList();
                    }
                })
                        .setNeutralButton("Não", null)
                        .create()
                        .show();
            }
        });
    }

    public void addListas(Generico generic) {
        listFormGeneric.add(generic);
    }


    private void bindImageViewProduct() {
        imageViewProduct = (ImageView) findViewById(R.id.imageViewProductInsert);
        if(product.getImage() != null){
            try {
                ImageManager.imageSet(imageViewProduct, product.getImage(), InventoryFormActivity.this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void bindToolbar() {
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void bindEditTextName() {
        editTextName = (TextView) findViewById(R.id.editTextProductName);
        editTextName.setText(product.getName() == null ? "" : product.getName() );
    }

    public void bindEditTextValue() {
        editTextValue = (TextView) findViewById(R.id.editTextProductValue);
        editTextValue.setText(product.getValue() == null ? "" : String.valueOf(product.getValue()));
    }

    private void bindButtonImageInsert() {
        buttonImageInsert = (Button) findViewById(R.id.buttonProductFormImageInsert);
        buttonImageInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent goToGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                /*caminhoArquivo = Environment.getExternalStorageDirectory().toString()+"/"+System.currentTimeMillis()+".png";
                File arquivo = new File(caminhoArquivo);
                Uri localArquivo = Uri.fromFile(arquivo);
                irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localArquivo);*/
                //startActivityForResult(goToGallery, 123);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select picture"), 123);
            }
        });
    }

//FIM DA MANIPULACAO DE OBJETOS DE LAYOUT

//INICIO DO BIND DE OBJETOS DE CLASSE

    private void initProduct() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            this.product = bundle.getParcelable(PARAM_PRODUCT);
        }
        product = product == null ? new Product() : this.product;
    }

    public void bindProduct() {
        product.setName(editTextName.getText().toString());
        product.setValue(editTextValue.getText().toString().equals("") ? 0 : Double.parseDouble(editTextValue.getText().toString()));
        product.setImage(product.getImage() == null ? "" : product.getImage());
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
        switch (item.getItemId()) {
            case R.id.menu_productForm_salvar:
                onMenuSave();
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuSave() {
        bindProduct();
        ProductService.save(product);
        for (Generico g : listFormGeneric){
            g.setProduct_id(product.get_id());
            GenericService.save(g);
        }
        finish();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_generic, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuContextEdit:
                onMenuEditClick();
                break;
            case R.id.menuContextRemove:
                onMenuRemoveClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuEditClick() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(InventoryFormActivity.this);

        final EditText input = new EditText(InventoryFormActivity.this);
        input.setHint("Value");
        alert.setTitle("Generic Manage");
        input.setPadding(5, 5, 5, 5);
        alert.setView(input);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = input.getText().toString();
                editFormGeneric(value);
            }
        });
        alert.setNeutralButton("Cancel", null);
        alert.create();
        alert.show();


    }

    private void editFormGeneric(String value) {
        Generico generic = (Generico) spinner.getSelectedItem();
        generic.setValor(value);
        if (generic.get_id() != null){
            GenericService.save(generic);
        }
        updateGenericList();
    }

    private void onMenuRemoveClick() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(InventoryFormActivity.this);
        dialog.setPositiveButton("Are you sure?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteGenericList();
            }
        });
        dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.setTitle("Generic Manager");
        dialog.show();


    }

    private void deleteGenericList() {
        Generico generic = (Generico) spinner.getSelectedItem();
        if(generic.get_id() != null) {
            GenericService.delete(generic.get_id());
        } else {
            listFormGeneric.remove(generic);
        }
        updateGenericList();
    }

//FIM MANIPULACAO DE MENUS


//INICIO DA MANIPULACAO LISTA

    private void updateGenericList() {
        listTotal = new ArrayList<>();
        listTotal = GenericService.findAll(product.get_id());
        listTotal.addAll(listFormGeneric);
        GenericAdapterNormal adapter = (GenericAdapterNormal) spinner.getAdapter();
        adapter.setItens(listTotal);
        adapter.notifyDataSetChanged();
    }

    private void bindListGeneric() {
        listTotal = new ArrayList<>();
        listFormGeneric = new ArrayList<>();
    }

    //FIM MAPIPULACAO DA LISTA




}
