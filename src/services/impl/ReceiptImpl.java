package services.impl;

import models.Receipt;
import services.DbHelper;
import services.EmployeesService;
import services.ReceiptService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReceiptImpl implements ReceiptService {
    DbHelper dbHelper = new DbHelperImpl();
    EmployeesService employeesService = new EmployeesImpl();

//    @Override
//    public String save(double sum, Date date, Long id_employee) {
//        java.util.Date currentDate = new java.util.Date();
//        PreparedStatement pr = dbHelper.getStatement("INSERT INTO tb_receipt (sum,date,id_employee) values (?,?,?)");
//        try {
//            pr.setDouble(1,sum);
//            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
//            pr.setDate(2, sqlDate); // Установите текущую дату
//            pr.setLong(3, id_employee);
//            pr.executeUpdate();
//            pr.close();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return "Чек добавлен! ";
//    }
    //    Scanner scanner = new Scanner(System.in);
//    ReceiptService receiptService = new ReceiptImpl();
//        System.out.println("Введите сумму чека: ");
//    double sum = scanner.nextDouble();
//
//    // Создайте объект java.util.Date для текущей даты и времени
//    java.util.Date currentDate = new java.util.Date();
//
//    // Создайте форматтер для отображения даты и времени в нужном формате
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String formattedDate = dateFormat.format(currentDate);
//        System.out.println("Введите id_employee");
//    Long id_employee = scanner.nextLong();
//        System.out.println(receiptService.save(sum,currentDate,id_employee));

    @Override
    public Receipt save(Receipt receipt) {
        PreparedStatement ps = dbHelper.getStatement("INSERT INTO tb_receipt (sum, id_employee, date) values (?,?,?)");
        try {
            ps.setDouble(1,receipt.getSum());
            ps.setLong(2,receipt.getEmployees().getId());
            LocalDate localDate = LocalDate.now();
            ps.setDate(3, new java.sql.Date(localDate.getYear(),localDate.getMonthValue(),localDate.getDayOfMonth()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findLast() ;
    }

    @Override
    public Receipt findByAdd(Long id) {
        PreparedStatement ps = dbHelper.getStatement("SELECT * FROM tb_receipt where id=?");
        try {
            ps.setLong(1,id);
            ResultSet resultSet = ps.executeQuery();
            Receipt receipt = new Receipt();
            while (resultSet.next()){
                receipt.setId(resultSet.getLong("id"));
                receipt.setSum(resultSet.getDouble("sum"));
                receipt.setEmployees(employeesService.findById(resultSet.getLong("id_employee")));
                receipt.setDate(resultSet.getDate("date"));

            }
            return receipt;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Receipt findLast() {
        try {
            PreparedStatement ps = dbHelper.getStatement("SELECT * FROM tb_receipt where id = (SELECT max(id) from tb_receipt)");
            ResultSet resultSet = ps.executeQuery();
            Receipt receipt = new Receipt();
            while (resultSet.next()){
                receipt.setId(resultSet.getLong("id"));
                receipt.setSum(resultSet.getDouble("sum"));
                receipt.setEmployees(employeesService.findById(resultSet.getLong("id_employee")));
                receipt.setDate(resultSet.getDate("date"));

            }
            return receipt;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    }


