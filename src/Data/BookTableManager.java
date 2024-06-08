package Data;

import Constants.LoanStatus;
import LibraryObjects.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static java.lang.System.out;

public class BookTableManager
{
    private final String DB_URL ="jdbc:sqlite:newDB.db";
    private final String SELECT_ALL = "SELECT * FROM Books";
    private static final List<Book> allBooks = new ArrayList<>();
    private final String BOOK_INSERT = "INSERT INTO Books(Title, Author, Genre, isSeries, Price, LoanStatus) VALUES" +
            "(?,?,?,?,?, ?)";


    public BookTableManager()
    {}

    /**
     * Get a connection to the database
     * @return a Connection to the database.
     * @throws SQLException in case of DB error
     */
    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(DB_URL);
    }

    /**
     * Get all books from the database
     * @return an ArrayList of all Books in the database.
     */
    public List<Book> getAllBooks()
    {
        try (Connection conn = DriverManager.getConnection(DB_URL))
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL);

            while (rs.next())
                allBooks.add(new Book(rs.getString("title"), rs.getString("author"), rs.getString("genre"),
                                                     rs.getBoolean("isSeries"), rs.getDouble("price"),
                                                     rs.getString("LoanStatus").equals("checked in") ?
                                                     LoanStatus.CHECKED_IN : LoanStatus.CHECKED_OUT));

        }
        catch (Exception e)
        {
            out.println(e.getMessage());
        }

        return allBooks;
    }

    public boolean addBook(Book book)
    {
        out.println("Adding book: " + book.getTitle() + " to the Library");
        try(Connection conn = getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement(BOOK_INSERT);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getGenre());
            stmt.setBoolean(4, book.aSeries());
            stmt.setDouble(5, book.getPrice());
            stmt.setString(6, book.getLoanStatus().toString());

            return stmt.executeUpdate() > 1;

        } catch (Exception e)
        {
            e.getMessage();
            return false;
        }
    }


}
