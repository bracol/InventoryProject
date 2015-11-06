package com.example.c1284518.inventoryproject.controller.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.c1284518.inventoryproject.R;
import com.example.c1284518.inventoryproject.model.entities.Generico;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by c1284518 on 29/10/2015.
 */
public class GenericAdapterNormal extends BaseAdapter {

    private List<Generico> listGeneric;
    private Activity context;

    public GenericAdapterNormal(Activity context, List<Generico> listGeneric){
        this.listGeneric = listGeneric;
        this.context = context;
    }


    @Override
    public int getCount() {
        return listGeneric.size();
    }

    @Override
    public Generico getItem(int position) {
        return listGeneric.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Generico current = getItem(position);

        View layout = context.getLayoutInflater().inflate(R.layout.list_item_generic, parent, false);
        TextView textViewValue = (TextView) layout.findViewById(R.id.editTextGenericList);
        textViewValue.setText(current.getValor());


        return layout;
    }

    public void setItens(List<Generico> itens) {
        listGeneric.clear();
        listGeneric.addAll(itens);
    }

}
