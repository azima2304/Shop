package services;

import models.Products;

import java.util.List;

public interface ProductReceipt {
    String save(Long id_product, Long id_receip);
    String deleteWithIdProduct (Long id_product);
    models.ProductReceipt findById(Long id);
    List<models.ProductReceipt> findAll();
    models.ProductReceipt updateIdReceipt(Long id, Long id_receip);
    models.ProductReceipt updateIdProduct(Long id, Long id_product);
    List<Products> findProductByIdReceip(Long id);

}
