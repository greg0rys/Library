package Data;

import LibraryObjects.Book;
import Constants.LoanStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class BookShelf
{
    private final static String GET_ALL_BOOKS_FOR_USER = "SELECT * FROM CheckedOutBooks WHERE UserCardNum = ?";
    private final static String REMOVE_BOOK_FOR_USER = "DELETE FROM CheckedOutBooks WHERE UserCardNum = ?";
    private final static String ADD_BOOK_FOR_USER = "INSERT INTO CheckedOutBooks(BookID, UserCardNum) VALUES(?,?)";
    private final static List<Book> CHECKED_OUT_BOOKS = new ArrayList<>();

    public BookShelf() throws SQLException
    {
        if(!DbInterface.pingDB())
            out.println("Unable to ping db - try again.");
    }

    public List<Book> getAllBooksForUser(int userCardNum) throws SQLException, ClassNotFoundException
    {
        try(Connection conn = DbInterface.getConnection())
        {
            List<Book> books = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement(GET_ALL_BOOKS_FOR_USER);
            ps.setInt(1, userCardNum);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                books.add(new Book(rs.getString("title"), rs.getString("author"), rs.getString("genre"),
                                                   rs.getBoolean("in_series"), rs.getDouble("price"),
                                                   LoanStatus.valueOf(rs.getString("loan_status"))));
            }

            return books;
        }
        catch(Exception e)
        {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return null;
    }

    public boolean removeCheckedOutBook(int userCardNum)
    {

        try(Connection conn = DbInterface.getConnection())
        {
            PreparedStatement ps = conn.prepareStatement(REMOVE_BOOK_FOR_USER);
            ps.setInt(1, userCardNum);
            return ps.executeUpdate() > 0;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    public boolean addCheckedOutBook(Book B, int userCardNumber)
    {
        if(B == null || userCardNumber <= 0)
            return false;

        try(Connection conn = DbInterface.getConnection())
        {
            PreparedStatement ps = conn.prepareStatement(ADD_BOOK_FOR_USER);
            ps.setInt(1, B.getBook_id());
            ps.setInt(2, userCardNumber);

            return ps.executeUpdate() > 0;
        }
        catch(Exception e)
        {
            throw new RuntimeException();
        }
    }
}
