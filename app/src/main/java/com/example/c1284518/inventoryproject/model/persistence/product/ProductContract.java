package com.example.c1284518.inventoryproject.model.persistence.product;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.c1284518.inventoryproject.model.entities.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c1284518 on 28/10/2015.
 */
public class ProductContract {

    public static final String TABLE_NAME = "product";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String VALUE = "value";
    public static final String IMAGE = "image";

    public static final String[] COLUMS = {ID, NAME, VALUE, IMAGE};

    public ProductContract(){super();}

    public static String getCreateScript(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME);
        sb.append("(");
        sb.append(ID + " INTEGER PRIMARY KEY, ");
        sb.append(NAME + " TEXT, ");
        sb.append(VALUE + " REAL, ");
        sb.append(IMAGE + " TEXT ");
        sb.append(" ); ");

        return sb.toString();
    }

    public static ContentValues getContentValues(Product product){
        ContentValues contentValues = new ContentValues();

        contentValues.put(ProductContract.ID, product.get_id());
        contentValues.put(ProductContract.NAME, product.getName());
        contentValues.put(ProductContract.VALUE, product.getValue());
        contentValues.put(ProductContract.IMAGE, product.getImage());

        return contentValues;
    }

    public static Product getProduct(Cursor cursor){
        Product product = new Product();
        product.set_id(cursor.getLong(cursor.getColumnIndex(ProductContract.ID)));
        product.setName(cursor.getString(cursor.getColumnIndex(ProductContract.NAME)));
        product.setValue((double) cursor.getLong(cursor.getColumnIndex(ProductContract.VALUE)));
        product.setImage(cursor.getString(cursor.getColumnIndex(ProductContract.IMAGE)));
        return product;
    }

    public static List<Product> getListProduct(Cursor cursor){
        List<Product> listProduct = new ArrayList<>();

        while (cursor.moveToNext()){
            listProduct.add(getProduct(cursor));
        }
        return listProduct;
    }
}
