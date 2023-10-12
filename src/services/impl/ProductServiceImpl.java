package services.impl;

import models.Products;
import services.DbHelper;
import services.ProductService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductServiceImpl implements ProductService {
    DbHelper dbHelper = new DbHelperImpl();
    @Override
    public String save(String name, double price) {
        PreparedStatement preparedStatement = dbHelper.getStatement("INSERT INTO tb_product (name,price) values (?,?)");
        try {
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2, (int) price);
            preparedStatement.executeUpdate();
            return "Магазин добавлен! ";
        } catch (SQLException e) {
            throw new RuntimeException("Магазин не добавлен! ");
        }
    }

    //Что нужно написать в главном классе Main
//    Scanner sc = new Scanner(System.in);
//    ProductService productService = new ProductServiceImpl();
//        System.out.println("Введите имя продукта: ");
//    String name = sc.next();
//        System.out.println("Введите цену продукта: ");
//    double price = sc.nextDouble();
//        productService.save(name, price);

    @Override
    public Products findById(Long id) {
        Products product = new Products();
        PreparedStatement preparedStatement  = dbHelper.getStatement("SELECT * from tb_product where id=?");
        try {
            //inValue() в базе данных id принимается как int
            preparedStatement.setLong(1,id.intValue());
            //объект ResultSet, который содержит результаты запроса.
            ResultSet resultSet = preparedStatement.executeQuery();
            //перебираем таблицу чтобы найти нужное нам значение
            while (resultSet.next()){
                product.setId((long) resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
            }
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //    Scanner sc = new Scanner(System.in);
//        ProductService productService = new ProductServiceImpl();
//        System.out.println("Введите id продукта, которого хотиите посмотреть: ");
//        Long id = sc.nextLong();
//        System.out.println(productService.findById(id));

    @Override
    public List<Products> findAll() {
        List<Products> products = new ArrayList<>();
        PreparedStatement preparedStatement = dbHelper.getStatement("SELECT * from tb_product");
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Products result = new Products();
                result.setId((long)resultSet.getLong(1));
                result.setName(resultSet.getString(2));
                result.setPrice(resultSet.getDouble(3));
                products.add(result);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("База данных продуктов не найдена! ");
        }
    }

//    ProductService productService = new ProductServiceImpl();
//        System.out.println("Таблица продукты: ");
//        System.out.println(productService.findAll());

    @Override
    public Products update(Long id, String name) {
        PreparedStatement preparedStatement = dbHelper.getStatement("UPDATE tb_product set name = ? where id =?");
        try {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findById(id);
//        Scanner sc = new Scanner(System.in);
//        ProductService productService = new ProductServiceImpl();
//        System.out.println("Введите id продукта, у которого хотите поменять название: ");
//        Long id = sc.nextLong();
//        System.out.println("Введите имя на которрое хотите поменять: ");
//        String name = sc.next();
//        System.out.println(productService.update(id,name));
    }

    @Override
    public Products update(Long id, double price) {
        PreparedStatement preparedStatement = dbHelper.getStatement("UPDATE tb_product set price =? where id =?");
        try {
            preparedStatement.setDouble(1,price);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findById(id);
    }

    //Scanner sc = new Scanner(System.in);
//        ProductService productService = new ProductServiceImpl();
//        System.out.println("Введите id продукта , цену которго хотите поменять: ");
//        Long id = sc.nextLong();
//        System.out.println("Введите цену: ");
//        double price = sc.nextDouble();
//        System.out.println(productService.update(id,price));
}
