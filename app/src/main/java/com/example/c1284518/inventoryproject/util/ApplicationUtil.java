package com.example.c1284518.inventoryproject.util;

import android.content.Context;

/**
 * Created by c1284518 on 28/10/2015.
 */
public class ApplicationUtil {

    private static Context APPLICATION_CONTEXT;

    private ApplicationUtil() {
        super();
    }

    public static void setContext(Context context) {
        APPLICATION_CONTEXT = context;
    }

    public static Context getContext() {
        return ApplicationUtil.APPLICATION_CONTEXT;
    }

}
