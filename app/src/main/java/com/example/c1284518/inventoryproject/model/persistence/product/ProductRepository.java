package com.example.c1284518.inventoryproject.model.persistence.product;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.c1284518.inventoryproject.model.entities.Product;
import com.example.c1284518.inventoryproject.model.persistence.DatabaseHelper;

import java.util.List;

/**
 * Created by c1284518 on 28/10/2015.
 */
public class ProductRepository {

    public ProductRepository(){super();}

    public static long save(Product product){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        long id;
        ContentValues contentValues = ProductContract.getContentValues(product);

        if(product.get_id() == null){
            id = db.insert(ProductContract.TABLE_NAME, null, contentValues);
            return id;
        } else {
            String where =  ProductContract.ID + " = ? ";
            String[] params = {product.get_id().toString()};
            db.update(ProductContract.TABLE_NAME, contentValues, where, params);
        }

        db.close();
        databaseHelper.close();
        return product.get_id();
    }

    public static void delete(Long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = ProductContract.ID + " = ? ";
        String[] params = {id.toString()};
        db.delete(ProductContract.TABLE_NAME, where, params);
    }

    public static List<Product> getAll(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();


        Cursor cursor = db.query(ProductContract.TABLE_NAME, ProductContract.COLUMS, null, null, null, null, ProductContract.ID);
        List<Product> listProdct = ProductContract.getListProduct(cursor);

        db.close();
        databaseHelper.close();
        return listProdct;
    }
}
