package com.example.c1284518.inventoryproject.model.service;

import com.example.c1284518.inventoryproject.model.entities.Generico;
import com.example.c1284518.inventoryproject.model.persistence.generic.GenericRepository;

import java.util.List;

/**
 * Created by c1284518 on 29/10/2015.
 */
public class GenericService {

    private GenericService() {
        super();
    }

    public static List<Generico> findAll(){
        return GenericRepository.getAll();
    }

    public static void save(Generico generic){
        GenericRepository.save(generic);
    }

    public static void delete(Long id){
        GenericRepository.delete(id);
    }
}
