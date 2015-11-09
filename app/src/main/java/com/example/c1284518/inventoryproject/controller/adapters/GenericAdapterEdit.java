package com.example.c1284518.inventoryproject.controller.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.example.c1284518.inventoryproject.R;
import com.example.c1284518.inventoryproject.model.entities.Generico;

import java.util.List;

/**
 * Created by c1284518 on 06/11/2015.
 */
public class GenericAdapterEdit extends BaseAdapter {

    Activity context;
    List<Generico> listGeneric;

    public GenericAdapterEdit(Activity context, List<Generico> listGeneric){
        this.context = context;
        this.listGeneric = listGeneric;
    }


    @Override
    public int getCount() {
        return listGeneric.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }

    /*public class Listener implements TextWatcher {
        EditText editText;

        public Listener(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (editText.getId() == getCount()) {
                final EditText editTextInside = new EditText(context.getBaseContext());
                addMethodEdit(editTextInside);
                containerLayout.addView(editTextInside);
            }
            if (editText.getText().toString().equals("") && editText.getId() < sizeEdit && (editText.getId() + 1) == sizeEdit) {
                containerLayout.removeView(findViewById(editText.getId() + 1));
            }
        }
    }*/
}
