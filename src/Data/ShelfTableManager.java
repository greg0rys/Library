package Data;

import LibraryObjects.BookShelf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static Data.DbController.getConnection;
import static java.lang.System.out;

public class ShelfTableManager
{
    public static ArrayList<BookShelf> getAllShelves()
    {
        ArrayList<BookShelf> shelves = new ArrayList<>();
        try (Connection conn = getConnection())
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM shelves");

            while (rs.next())
            {
                BookShelf shelf = new BookShelf();
                shelves.add(shelf);
            }
        } catch (SQLException e)
        {
            out.println(e.getMessage());
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        return shelves;
    }
}
