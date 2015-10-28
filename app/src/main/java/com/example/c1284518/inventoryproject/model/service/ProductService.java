package com.example.c1284518.inventoryproject.model.service;

import com.example.c1284518.inventoryproject.model.entities.Product;
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
        ProductRepository.save(product);
    }

    public static void delete(Long id){
        ProductRepository.delete(id);
    }

}