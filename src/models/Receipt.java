package models;

import java.util.Date;

public class Receipt {
    private Long id;

    private Employees employees;
    private double sum;

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    private Date date;

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", employees=" + employees +
                ", sum=" + sum +
                ", date=" + date +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
