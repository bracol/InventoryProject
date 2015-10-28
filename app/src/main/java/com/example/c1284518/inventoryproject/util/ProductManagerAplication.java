package com.example.c1284518.inventoryproject.util;

import android.app.Application;

/**
 * Created by c1284518 on 28/10/2015.
 */
public class ProductManagerAplication extends Application {

    public void onCreate(){
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }
}
