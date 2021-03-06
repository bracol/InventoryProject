package com.example.c1284518.inventoryproject.model.service;

import com.example.c1284518.inventoryproject.model.entities.Product;
import com.example.c1284518.inventoryproject.model.persistence.generic.GenericRepository;
import com.example.c1284518.inventoryproject.model.persistence.product.ProductContract;
import com.example.c1284518.inventoryproject.model.persistence.product.ProductRepository;

import java.util.List;

/**
 * Created by c1284518 on 28/10/2015.
 */
public class ProductService {


    private ProductService() {
        super();
    }

    public static List<Product> findAll(){
        return ProductRepository.getAll();
    }

    public static void save(Product product){
        long id = ProductRepository.save(product);
        product.set_id(id);
    }

    public static void delete(Long id){
        GenericRepository.deleteByProductID(id);
        ProductRepository.delete(id);
    }

}