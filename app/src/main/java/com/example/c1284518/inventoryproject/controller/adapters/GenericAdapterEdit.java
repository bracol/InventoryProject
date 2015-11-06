package com.example.c1284518.inventoryproject.controller.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.c1284518.inventoryproject.R;
import com.example.c1284518.inventoryproject.model.entities.Generico;

import java.util.List;

/**
 * Created by c1284518 on 06/11/2015.
 */
public class GenericAdapterEdit extends RecyclerView.Adapter<GenericAdapterEdit.MyViewHolder> {

    Activity context;
    List<Generico> listGeneric;

    public GenericAdapterEdit(Activity context, List<Generico> listGeneric){
        this.context = context;
        this.listGeneric = listGeneric;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.list_item_generic, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(layout);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listGeneric.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        EditText editText;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.editTextGenericList);
        }
    }
}
