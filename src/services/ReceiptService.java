package services;

import models.Employees;
import models.Receipt;

import java.util.Date;
import java.util.List;

public interface ReceiptService {

    Receipt save(Receipt receipt);
    Receipt findByAdd(Long id);
    Receipt findLast();
    //String save(double sum, Date date, Long id_employee);
//    Receipt findById(Long id);
//    List<Receipt> findAll();
//    Receipt update(Long id, double sum);
//    Receipt update(Long id, Date date);
//    Receipt update(Long id, Long id_employee);
}
