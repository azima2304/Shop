package services;

import models.Products;
import models.Shop;

import java.util.List;

public interface ProductService {
    String save(String name, double price);
    Products findById(Long id);
    List<Products> findAll();
    Products update(Long id, String name);
    Products update(Long id, double price);
}
