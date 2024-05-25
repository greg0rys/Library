package Data;

import LibraryObjects.Book;
import LibraryObjects.BookShelf;
import LibraryObjects.LibraryMember;

import java.sql.*;
import java.util.ArrayList;

import static java.lang.System.out;


public class LibraryDB
{
    private final static String DB_URL ="jdbc:sqlite:newDB.db";
    private final static String BOOK_INSERT = "INSERT INTO Books(Title, Author, Genre, aSeries, Price) VALUES(?,?,?,?,?)";
    private final static String SELECT_ALL = "SELECT * FROM Books";
    private final static String TOTAL_BOOKS = "SELECT COUNT(*) FROM Books";
    private static ResultSet rs;
    private static final ArrayList<Book> allBooks = new ArrayList<>();
    private static ArrayList<BookShelf> allShelves = new ArrayList<>();



    public static Connection getConnection() throws SQLException, ClassNotFoundException
    {
        return DriverManager.getConnection(DB_URL);
    }

    public static boolean addBookToLibrary(Book book)
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


    public static int getNumBooks()
    {
        try(Connection conn = getConnection())
        {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(TOTAL_BOOKS);

            if(rs.next())
                return rs.getInt(1);
        }
        catch(Exception e)
        {
            out.println("unable to get count " + e.getMessage());
        }
        return 0;
    }

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

    public static ArrayList<Book> getAllBooks()
    {
        int bookCount;
        boolean singleBook;
        try (Connection conn = getConnection())
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL);

            while (rs.next())
                allBooks.add(new Book(rs.getString("title"),rs.getString("author"),rs.getString("genre"),
                                      rs.getBoolean("aSeries"),rs.getDouble("price")));


        }
        catch (Exception e)
        {
            out.println(e.getMessage());
        }

        return allBooks;
    }


    public static boolean userCheckout(Book b, int cardNumber) throws SQLException, ClassNotFoundException
    {
        ArrayList<LibraryMember> members;
        if(b == null)
            return false;
        try(Connection conn = getConnection())
        {
            // check out a book
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO checked_out_books (book_id, card_number) VALUES (?, ?)");
            stmt.setInt(1, b.getBook_id());
            stmt.setInt(2, cardNumber);
            
            return (stmt.executeUpdate() == 1);
            
        }
    }

    public static boolean returnBookToLibrary(Book b, int cardNumber)
    {
        try (Connection conn = getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM checked_out_books WHERE book_id = ? AND card_number = ?");
            stmt.setInt(1, b.getBook_id());
            stmt.setInt(2, cardNumber);
    
            return (stmt.executeUpdate() == 1);
        } catch (Exception e)
        {
            out.println(e.getMessage());
            return false;
        }
    }



//    public static void addBookToLibrary(LibraryObjects.Book book)
//    {
//    }
}
