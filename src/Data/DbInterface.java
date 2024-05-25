package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DbInterface
{
    String DB_URL ="jdbc:sqlite:newDB.db";

    static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(DB_URL);
    }

    static boolean pingDB() throws SQLException
    {
        return DriverManager.getConnection(DB_URL) != null;
    }

    static boolean queryAllTables()
    {
        return false;
    }
}
