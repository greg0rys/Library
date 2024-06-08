package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DBController
{
    String DB_URL = "jdbc:sqlite:newDB.db";
    default Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(DB_URL);
    }
}
