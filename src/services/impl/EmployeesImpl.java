package services.impl;

import models.Employees;
import services.DbHelper;
import services.EmployeesService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeesImpl implements EmployeesService {
    DbHelper dbHelper = new DbHelperImpl();

    @Override
    public String save(String name, int age,Long shop_id) {
        PreparedStatement pr = dbHelper.getStatement("INSERT INTO tb_employees (name,age,shop_id) values (?,?,?)");
        try {
            pr.setString(1,name);
            pr.setInt(2,age);
            pr.setLong(3,shop_id);
            pr.executeUpdate();
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Сотрудник добавлен! ";
    }

//    Scanner scanner = new Scanner(System.in);
//    EmployeesService employeesService = new EmployeesImpl();
//        System.out.println("Введите имя сотрудника: ");
//    String name = scanner.next();
//        System.out.println("Введите возраст сотрудника: ");
//    int age = scanner.nextInt();
//        System.out.println("Введите id магазина в котором он работает: ");
//    Long shop_id = scanner.nextLong();
//        System.out.println(employeesService.save(name,age,shop_id));

    @Override
    public Employees findById(Long id) {
        Employees employee = new Employees();
        PreparedStatement pr = dbHelper.getStatement("SELECT * from tb_employees where id=?");
        try {
            pr.setLong(1,id.intValue());
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()){
                employee.setId((long)resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setAge(resultSet.getInt(3));
                employee.setShop((long)resultSet.getInt(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

//        Scanner sc = new Scanner(System.in);
//        EmployeesService employeesService = new EmployeesImpl();
//        System.out.println("Введите id сотрудника: ");
//        Long id = sc.nextLong();
//        System.out.println(employeesService.findById(id));

    @Override
    public List<Employees> findAll() {
        List<Employees> employees = new ArrayList<>();
        Employees employee  = new Employees();
        PreparedStatement pr = dbHelper.getStatement("SELECT * from tb_employees");
        try {
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()){
                employee.setId((long)resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setAge(resultSet.getInt(3));
                employee.setShop(resultSet.getLong(4));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees  ;

    }

//    EmployeesService employeesService = new EmployeesImpl();
//        System.out.println("Список сотрудников: ");
//        System.out.println(employeesService.findAll());

    @Override
    public Employees update(Long id, String name) {
        PreparedStatement ps = dbHelper.getStatement("UPDATE tb_employees set name=? where id=?");
        try {
            ps.setString(1,name);
            ps.setLong(2,id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return findById(id);
    }

//    Scanner sc = new Scanner(System.in);
//    EmployeesService employeesService = new EmployeesImpl();
//        System.out.println("Введите id сотрудника: ");
//    Long id = sc.nextLong();
//        System.out.println("Введите новое имя сотрудника: ");
//    String name = sc.next();
//        System.out.println(employeesService.update(id,name));

    @Override
    public Employees update(Long id, int age) {
        PreparedStatement ps = dbHelper.getStatement("UPDATE tb_employees set age=? where id=?");
        try {
            ps.setInt(1,age);
            ps.setLong(2,id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findById(id);
    }

    }
 //               Scanner sc = new Scanner(System.in);
//                EmployeesService employeesService = new EmployeesImpl();
//                System.out.println("Введите id сотрудника: ");
//                Long id = sc.nextLong();
//                System.out.println("Введите новый возраст сотрудника: ");
//                int age = sc.nextInt();
//                System.out.println(employeesService.update(id,age));

