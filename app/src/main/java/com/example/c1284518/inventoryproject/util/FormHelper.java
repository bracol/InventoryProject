package com.example.c1284518.inventoryproject.util;

import android.widget.EditText;

import com.example.c1284518.inventoryproject.model.entities.Generico;

import java.util.List;

/**
 * Created by c1284518 on 06/11/2015.
 */
public class FormHelper {

    private FormHelper(){}

    public static boolean verifyNumeric(EditText editText){
        try {
            String s = editText.getText().toString();
            Double.parseDouble(s);
            return false;
        }catch (NumberFormatException e){
            editText.setError("Only Numeric!");
            return true;
        }
    }


}
