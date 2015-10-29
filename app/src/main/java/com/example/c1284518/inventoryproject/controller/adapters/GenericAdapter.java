package com.example.c1284518.inventoryproject.controller.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.c1284518.inventoryproject.R;
import com.example.c1284518.inventoryproject.model.entities.Generico;

import java.util.List;

/**
 * Created by c1284518 on 29/10/2015.
 */
public class GenericAdapter extends RecyclerView.Adapter<GenericAdapter.MyViewHolder> {

    private List<Generico> listGenerico;
    private Activity context;

    public GenericAdapter(List<Generico> listGenerico, Activity context){
        this.listGenerico = listGenerico;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.list_item_generic, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(layout);
        return myViewHolder;
    }

    @Override
    public int getItemCount() {
        return listGenerico.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Generico current = listGenerico.get(position);
        holder.textViewValor.setText(current.getValor());
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textViewValor;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.textViewGenericList);
        }
    }


    public void addItem(Generico generic){
        listGenerico.add(generic);
    }

    public void removeItem(Generico generic){
        listGenerico.remove(generic);
    }

    public void setItens(List<Generico> itens) {
        listGenerico.clear();
        listGenerico.addAll(itens);
    }
}
