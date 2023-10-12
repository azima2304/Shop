import models.Shop;
import services.*;
import services.impl.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {
        BuyOperation buyOperation = new BuyOperationImpl();
        buyOperation.buyProduct();


    }
}
