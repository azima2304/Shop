package services.impl;

import models.Products;
import services.DbHelper;
import services.ProductReceipt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductReceiptImpl implements ProductReceipt {
    DbHelper dbHelper = new DbHelperImpl();

    @Override
    public String save(Long id_product, Long id_receip) {
        PreparedStatement pr = dbHelper.getStatement("INSERT INTO tb_product_receip (id_product, id_receip) values (?,?)");
        try {
            pr.setLong(1, id_product);
            pr.setLong(2, id_receip);
            pr.executeUpdate();
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException("Невозможно добавить чек!");
        }
        return "Чек успешно добавлен! ";
//        Scanner sc = new Scanner(System.in);
//        ProductReceipt service = new ProductReceiptImpl();
//        System.out.println("Введите id продукта: ");
//        Long id_product = sc.nextLong();
//        System.out.println("Введите id чека: ");
//        Long id_receip = sc.nextLong();
//        System.out.println(service.save(id_product,id_receip));

    }

    @Override
    public String deleteWithIdProduct(Long id) {
        PreparedStatement pr = dbHelper.getStatement("DELETE from tb_product_receip where id=?");
        try {
            pr.setLong(1, id);
            pr.executeUpdate();
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Чек успешно удален!";
    }

//    Scanner sc = new Scanner(System.in);
//    ProductReceipt service = new ProductReceiptImpl();
//        System.out.println("Введите id чека , чтобы удалить: ");
//    Long id = sc.nextLong();
//        System.out.println(service.deleteWithIdProduct(id));

    @Override
    public models.ProductReceipt findById(Long id) {
        models.ProductReceipt productReceipt = new models.ProductReceipt();
        PreparedStatement pr = dbHelper.getStatement("SELECT * FROM tb_product_receip WHERE id=?");
        try {
            pr.setLong(1, id);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                productReceipt.setId(resultSet.getLong("id"));
                productReceipt.setId_product(resultSet.getLong("id_product"));
                productReceipt.setId_receip(resultSet.getLong("id_receip"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productReceipt;
    }


//    Scanner sc = new Scanner(System.in);
//    ProductReceipt service = new ProductReceiptImpl();
//        System.out.println("Введите id чека: ");
//    Long id = sc.nextLong();
//        System.out.println(service.findById(id));


    @Override
    public List<models.ProductReceipt> findAll() {
        List<models.ProductReceipt> productReceipts = new ArrayList<>();
        PreparedStatement pr = dbHelper.getStatement("SELECT * from tb_product_receip");
        try {
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                models.ProductReceipt productReceipt = new models.ProductReceipt();
                productReceipt.setId((long) resultSet.getInt(1));
                productReceipt.setId_product(resultSet.getLong("id_product"));
                productReceipt.setId_receip(resultSet.getLong("id_receip"));
                productReceipts.add(productReceipt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productReceipts;

//        Scanner sc = new Scanner(System.in);
//        ProductReceipt service = new ProductReceiptImpl();
//        System.out.println("Список всех чеков: ");
//        System.out.println(service.findAll());

    }

    @Override
    public models.ProductReceipt updateIdReceipt(Long id, Long id_receip) {
        PreparedStatement ps = dbHelper.getStatement("UPDATE tb_product_receip set id_receip=? where id=?");
        try {
            ps.setLong(1, id_receip);
            ps.setLong(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findById(id);
    }

//    Scanner sc = new Scanner(System.in);
//    ProductReceipt service = new ProductReceiptImpl();
//        System.out.println("Введите id чека где вы хотите поменять id_receip:");
//    Long id = sc.nextLong();
//        System.out.println("Введите новый id_recipt: ");
//    Long id_receip = sc.nextLong();
//        System.out.println(service.updateIdReceipt(id,id_receip));


    @Override
    public models.ProductReceipt updateIdProduct(Long id, Long id_product) {
        PreparedStatement ps = dbHelper.getStatement("UPDATE tb_product_receip set id_product=? where id=?");
        try {
            ps.setLong(1, id_product);
            ps.setLong(2, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findById(id);
    }


    @Override
        public List<Products> findProductByIdReceip(Long id_receip) {
            List<Products> products = new ArrayList<>();
            PreparedStatement ps = dbHelper.getStatement("SELECT tb_product.*\n" +
                    "FROM tb_product_receip\n" +
                    "JOIN tb_product ON tb_product_receip.id_product = tb_product.id\n" +
                    "WHERE tb_product_receip.id_receip = ?\n");
                try {
                ps.setLong(1, id_receip);
                ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    // Создайте объекты продуктов и добавьте их в список products
                    Products product = new Products();
                    product.setId(resultSet.getLong("id"));
                    product.setName(resultSet.getString("name"));
                    // Добавьте другие поля продукта по мере необходимости

                    products.add(product);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return products;
        }

//    ProductReceipt service = new ProductReceiptImpl();
//    Scanner sc = new Scanner(System.in);
//        System.out.println("Введите id чека: ");
//    Long id_receip = sc.nextLong();
//        System.out.println(service.findProductByIdReceip(id_receip));

}


