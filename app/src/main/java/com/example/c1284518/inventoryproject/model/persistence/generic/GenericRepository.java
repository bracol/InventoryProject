package com.example.c1284518.inventoryproject.model.persistence.generic;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.c1284518.inventoryproject.model.entities.Generico;
import com.example.c1284518.inventoryproject.model.persistence.DatabaseHelper;
import com.example.c1284518.inventoryproject.model.persistence.product.ProductContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c1284518 on 29/10/2015.
 */
public class GenericRepository {

    public static void save(Generico generico){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues contentValues = GenericContract.getContentValue(generico);

        if (generico.get_id() == null){
            db.insert(GenericContract.TABLE_NAME, null, contentValues);
        } else {
            String where = GenericContract.ID + " = ? ";
            String[] params = {String.valueOf(generico.get_id())};
            db.update(GenericContract.TABLE_NAME, contentValues, where, params);
        }
        db.close();
        databaseHelper.close();
    }

    public static void delete(long id){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = GenericContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        db.delete(GenericContract.TABLE_NAME, where, params);
        db.close();
        databaseHelper.close();
    }

    public static List<Generico> getAll(){
        List<Generico> listGeneric = new ArrayList<>();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(GenericContract.TABLE_NAME, GenericContract.COLUMNS, null, null, null, null, GenericContract.ID);
        listGeneric = GenericContract.getListGeneric(cursor);

        db.close();
        databaseHelper.close();
        return listGeneric;
    }

    public static long getId(String value){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = GenericContract.ID + " = ? ";
        String [] params = {value};

        Cursor cursor = db.query(GenericContract.TABLE_NAME, GenericContract.COLUMNS, where, params, null, null, null);
        Generico generic = GenericContract.getGeneric(cursor);

        databaseHelper.close();
        db.close();
        return generic.get_id();
    }

}
