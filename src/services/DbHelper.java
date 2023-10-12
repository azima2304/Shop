package services;

import java.sql.PreparedStatement;

public interface DbHelper {
    PreparedStatement getStatement (String sql);
}
