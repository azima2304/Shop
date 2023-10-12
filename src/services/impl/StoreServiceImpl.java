package services.impl;

import models.Employees;
import models.Products;
import models.Shop;
import services.DbHelper;
import services.StoreService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreServiceImpl implements StoreService {
    DbHelper dbHelper= new DbHelperImpl();
    @Override
    public String save(String name) {
        PreparedStatement ps = dbHelper.getStatement("INSERT INTO tb_shop (name) values (?)");
        try {
            ps.setString(1,name);
            ps.executeUpdate();
            ps.close();
            return "Успешно добавили магазин";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Shop findById(Long id) {
        Shop shop = new Shop();
        PreparedStatement prs = dbHelper.getStatement("select from tb_shop where id = ?");
        try {
            prs.setLong(1, id.intValue());
            ResultSet resultSet = prs.executeQuery();
            while (resultSet.next()){
                shop.setId((long) resultSet.getInt(1));
                shop.setName(resultSet.getString(2));
            }
            return shop;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Shop> findAll() {
        List<Shop> shops = new ArrayList<>();
        PreparedStatement ps = dbHelper.getStatement("select * from tb_shop");
        try {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Shop result = new Shop();
                result.setId((long)resultSet.getLong(1));
                result.setName(resultSet.getString(2));
                shops.add(result);
            }
            return shops;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Shop update(Long id, String name) {
        PreparedStatement ps = dbHelper.getStatement("UPDATE tb_shop set  name=? where id=?");
        try {
            // 1 -2  это как чередуется вопрос 1-name=?    2-id=?
            ps.setString(1,name);
            ps.setLong(2,id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findById(id);

    }

    @Override
    public List<Employees> findEmpByShop(Long id) {
            List<Employees> employees = new ArrayList<>();
            PreparedStatement ps = dbHelper.getStatement("SELECT tb_employees.*\n" +
                    "FROM tb_shop\n" +
                    "JOIN tb_employees ON tb_employees.shop_id = tb_shop.id\n" +
                    "WHERE tb_shop.id = ?\n");
            try {
                ps.setLong(1, id);
                ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    // Создайте объекты продуктов и добавьте их в список products
                    Employees employee = new Employees();
                    employee.setId(resultSet.getLong("id"));
                    employee.setName(resultSet.getString("name"));
                    // Добавьте другие поля продукта по мере необходимости

                    employees.add(employee);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return employees;
        }
//
//    Scanner sc = new Scanner(System.in);
//    StoreService service = new StoreServiceImpl();
//        System.out.println("Введите id магазина: ");
//    Long id = sc.nextLong();
//        System.out.println(service.findEmpByShop(id));



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



}
