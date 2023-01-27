package DB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    public static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/LoginSystem","postgres","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
