package services;

import models.Employees;
import models.Products;
import models.Shop;

import java.util.List;

public interface EmployeesService {
    String save(String name, int age, Long shop_id);
    Employees findById(Long id);
    List<Employees> findAll();
    Employees update(Long id, String name);
    Employees update(Long id, int age);
    //Employees update(Long id, shop id);
}
