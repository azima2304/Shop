package services;

import models.Employees;
import models.Shop;

import java.util.List;

public interface StoreService {
    String save(String name);
    Shop findById(Long id);
    List<Shop> findAll();
    Shop update(Long id, String name);

    List<Employees> findEmpByShop(Long id);

    //System.out.println("Название магазина: ");
    //String shopName = sc.next();
    //service.save(shopName);


//        System.out.println("Введите id магазина: ");
//        Shop shop = service.findById(sc.nextLong());
//        System.out.println(shop);
//
//        System.out.println("Все магазины: ");
//        System.out.println(service.findAll());
//        System.out.println("Введите id магазина: ");
//        Long id = sc.nextLong();
//        System.out.println("Введите название: ");
//        String name = sc.next();
//        System.out.println(service.update(id, name));






}
