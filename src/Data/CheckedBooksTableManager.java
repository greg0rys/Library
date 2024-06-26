package Data;

import LibraryObjects.Book;
import Constants.LoanStatus;
import Logic.CheckedBookNode;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckedBooksTableManager
{
    private final String DB_URL ="jdbc:sqlite:newDB.db";
    private final static String GET_ALL_BOOKS_FOR_USER = "SELECT * FROM CheckedOutBooks WHERE UserCardNum = ?";
    private final static String REMOVE_BOOK_FOR_USER = "DELETE FROM CheckedOutBooks WHERE UserCardNum = ?";
    private final static String ADD_BOOK_FOR_USER = "INSERT INTO CheckedOutBooks(BookID, UserCardNum) VALUES(?,?)";
    private final static String GET_ALL_CHECKED_BOOKS = "SELECT * FROM CheckedOutBooks";
    private final static List<CheckedBookNode> CHECKED_OUT_BOOKS = new ArrayList<>();

    public CheckedBooksTableManager()
    {

    }

    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(DB_URL);
    }

    /**
     * TODO: CHANGE THIS TO RETURN CheckedBook and NOT CheckedBookNode.
     * @return
     */
    public List<CheckedBookNode> getAllCheckedOutBooks()
    {
        try(Connection conn = DriverManager.getConnection(DB_URL))
        {
            Statement state = conn.prepareStatement(GET_ALL_CHECKED_BOOKS);
            ResultSet rs = state.executeQuery(GET_ALL_CHECKED_BOOKS);

            while(rs.next())
            {
                CHECKED_OUT_BOOKS.add(new CheckedBookNode(rs.getInt("UserCardNumber"),
                                                          rs.getInt("BookNumber")));
            }

            return CHECKED_OUT_BOOKS;

        } catch(RuntimeException | SQLException e)
        {
            throw new RuntimeException(e);
        }

    }

    public List<Book> getAllBooksForUser(int userCardNum) throws SQLException, ClassNotFoundException
    {
        try(Connection conn = getConnection())
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

        try(Connection conn = getConnection())
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

        try(Connection conn = getConnection())
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
