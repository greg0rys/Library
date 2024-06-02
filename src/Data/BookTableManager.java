package Data;

import Constants.LoanStatus;
import LibraryObjects.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static java.lang.System.out;

public class BookTableManager extends DbController
{
    private final String SELECT_ALL = "SELECT * FROM Books";
    private static final List<Book> allBooks = new ArrayList<>();
    private final String BOOK_INSERT = "INSERT INTO Books(Title, Author, Genre, aSeries, Price) VALUES(?,?,?,?,?)";


    public BookTableManager() throws SQLException
    { super(); }
    public List<Book> getAllBooks()
    {
        int bookCount;
        boolean singleBook;
        try (Connection conn = getConnection())
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL);

            while (rs.next())
                allBooks.add(new LibraryObjects.Book(rs.getString("title"), rs.getString("author"), rs.getString("genre"),
                                                     rs.getBoolean("aSeries"), rs.getDouble("price"), LoanStatus.valueOf(rs.getString("loanStatus"))));


        }
        catch (Exception e)
        {
            out.println(e.getMessage());
        }

        return allBooks;
    }

    public boolean addBook(LibraryObjects.Book book)
    {
        out.println("commiting LibraryObjects.Book");
        try(Connection conn = getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement(BOOK_INSERT);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getGenre());
            stmt.setBoolean(4, book.aSeries());
            stmt.setDouble(5, book.getPrice());

            return stmt.executeUpdate() > 1;
        } catch (Exception e)
        {
            e.getMessage();
            return false;
        }
    }

    public int getTotalBooks()
    {
        return allBooks.size();
    }

}
