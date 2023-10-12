package services.impl;

import services.DbHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbHelperImpl implements DbHelper {
    @Override
    public PreparedStatement getStatement(String sql) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:D:\\\\Documents\\\\Base\\\\DataBaseZadanie.db");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
