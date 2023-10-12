package services.impl;

import models.Products;
import services.BuyOperation;
import services.ProductReceipt;
import services.ProductService;
import services.ReceiptService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.BinaryOperator;

public class BuyOperationImpl implements BuyOperation {
    @Override
    public void buyProduct() {
        Scanner sc = new Scanner(System.in);
        ProductService service = new ProductServiceImpl();
        Products products = new Products();
        ProductReceipt productReceipt = new ProductReceiptImpl();
        ReceiptService receiptService = new ReceiptImpl();

        System.out.println("Добро пожаловать, кассир ! Укажите свой id: ");
        Long idEmp = sc.nextLong();

        List<String> receiptItems = new ArrayList<>();

        char answer = 'y';
        System.out.println("Добро пожаловать! ");
        System.out.println("Что вы  хотите купить? ");

        while (answer=='y') {
            System.out.println(service.findAll());
            System.out.println("Введите id продукта: ");
            Long idProduct = sc.nextLong();
            Products productInfo = service.findById(idProduct);


            String productString = productInfo.toString();
            receiptItems.add(productString);
            productReceipt.save(idProduct, idEmp);
            System.out.println("Хотите ещё что-то взять? y/n ");
            answer = sc.next().charAt(0);
        }
        System.out.println("Ваш чек: ");
        for (String receiptItem : receiptItems) {
            System.out.println(receiptItem);
        }


    }
}
