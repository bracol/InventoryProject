package com.example.c1284518.inventoryproject.model.persistence.generic;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.c1284518.inventoryproject.model.entities.Generico;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c1284518 on 29/10/2015.
 */
public class GenericContract {

    public static final String TABLE_NAME = "generic";
    public static final String ID = "id";
    public static final String VALOR = "valor";
    public static final String PRODUCT_ID = "product_id";

    public static final String[] COLUMNS = {ID, VALOR, PRODUCT_ID};

    public static String getCreateScript(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME);
        sb.append(" ( ");
        sb.append(ID + " INTEGER PRIMARY KEY, ");
        sb.append(VALOR + " TEXT, ");
        sb.append(PRODUCT_ID + " INTEGER ");
        sb.append(" ); ");
        return sb.toString();
    }

    public static ContentValues getContentValue(Generico generic){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, generic.get_id());
        contentValues.put(VALOR, generic.getValor());
        contentValues.put(PRODUCT_ID, generic.getProduct_id());

        return contentValues;
    }

    public static Generico getGeneric(Cursor cursor){
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Generico generico = new Generico();
            generico.set_id(cursor.getLong(cursor.getColumnIndex(ID)));
            generico.setValor(cursor.getString(cursor.getColumnIndex(VALOR)));
            generico.setProduct_id(cursor.getLong(cursor.getColumnIndex(PRODUCT_ID)));
            return generico;
        }


        return null;
    }

    public static List<Generico> getListGeneric(Cursor cursor){
        List<Generico> listGeneric = new ArrayList<>();

        while (cursor.moveToNext()){
            listGeneric.add(getGeneric(cursor));
        }

        return  listGeneric;
    }

}
