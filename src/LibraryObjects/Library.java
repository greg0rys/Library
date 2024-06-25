package LibraryObjects;

import java.sql.SQLException;
import java.util.*;

import ManagerClasses.BookManager;
import ManagerClasses.CheckedBooksManager;
import ManagerClasses.ShelfManager;
import ManagerClasses.UserManager;
import Utils.Helpers;
import Utils.Menu;
import static java.lang.System.out;

public class Library
{

    private HashMap<Integer, BookShelf> shelfMap = null;
    private  UserManager userManager = new UserManager();
    private BookManager bookManager = new BookManager();
    private ShelfManager shelfManager = new ShelfManager();
    private  CheckedBooksManager checkedBooksManager = new CheckedBooksManager();
    private final Scanner scanner = new Scanner(System.in);


    /**
     * TODO: make sure all manager classes have loaded data from db
     * @throws SQLException
     */
    public Library() throws SQLException
    { /* default constructor */ }

    /**
     * Used to copy a library
     * @param lib the library object we wish to copy.
     */
    public Library(Library lib) throws SQLException
    {
        if(lib == null)
            throw new IllegalArgumentException("Library object cannot be null");

        bookManager = lib.getBookManager();
        userManager = lib.getUserManager();
        shelfManager = lib.getShelfManager();
        checkedBooksManager = lib.getCheckedBooksManager();
    }

    public boolean hasBooks()
    {
        return (!bookManager.empty());
    }


    /**
     * Find a book in the Library system.
     * @return null if the manager is empty as there will be no books. (Caller must always check for null)
     *  Else a list of search results.
     */
    public List<Book> findBook()
    {
        if(bookManager.empty())
            return null;

        switch(Menu.findBookMenu())
        {
            case 1:
                return bookManager.findByTitle(Helpers.collectBookTitle());
            case 2:
                return bookManager.findByAuthor(Helpers.collectBookAuthor());
            case 3:
                return bookManager.findByGenre(Helpers.collectBookGenre());
            case 4:
                return bookManager.findByPrice(Helpers.collectBookPrice());
            default:
                out.println("Not a valid search type lets try again");
                break;
        }

        return findBook(); // If we made it here - recurse.
    }




    /**
     * List each book in the library
     * List BookID as well.
     */
    public void listAllBooks()
    {
        bookManager.display();
    }

    /**
     * Calls the recursive counting method
     *
     */
    public void printTotalBooks()
    {
        int totalBooks = bookManager.getTotalBooks();
        String isA = "is " + totalBooks + " book in the library";
        String areA = " are a total of " + totalBooks + " books in the library.";
        out.println("There" + (totalBooks > 1 ? areA : isA));


    }


    private BookManager getBookManager()
    {
        return bookManager;
    }

    private UserManager getUserManager()
    {
        return userManager;
    }

    private ShelfManager getShelfManager()
    {
        return shelfManager;
    }

    /**
     * Get the libraries CheckedBookManager - this method is used for the copy constructor
     * @return The class instance of the CheckedBookManager
     */
    private CheckedBooksManager getCheckedBooksManager()
    {
        return checkedBooksManager;
    }

}