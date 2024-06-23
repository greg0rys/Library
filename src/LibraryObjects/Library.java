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
    private final UserManager userManager = new UserManager();
    private final BookManager bookManager = new BookManager();
    private final ShelfManager shelfManager = new ShelfManager();
    private final CheckedBooksManager checkedBooksManager = new CheckedBooksManager();
    private final Scanner scanner = new Scanner(System.in);


    /**
     * TODO: make sure all manager classes have loaded data from db
     * @throws SQLException
     */
    public Library() throws SQLException
    { /* default constructor */ }

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
     * Get a LibraryObjects.BookShelf of a given ID number
     *
     * @param shelfId the shelf ID of the shelf.
     * @return the LibraryObjects.BookShelf object that has a matching shelf ID
     */
    public BookShelf getBookShelf(int shelfId)
    {
        return shelfMap.get(shelfId);
    }

    /**
     * Print a list of all Shelves in the LibraryObjects.Library - Includes Shelf IDs
     */
    public void printShelfMap()
    {
        for (Map.Entry<Integer, BookShelf> shelf : shelfMap.entrySet())
            out.println("Shelf ID: " + shelf.getKey() + ", LibraryObjects.Book Shelf: " + shelf.getValue());
    }

    /**
     * Display the total number of books & shelves in the library
     */

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

    /*
        Private methods
     */




    /**
     * Search the library for a book by it's given title
     *
     * @param resultList      an ArrayList of all matching titles.
     * @param identity        the title of the book being searched for.
     * @param shelfCollection the map of all shelves to search
     * @return true if the title is found else false.
     */
    private boolean searchLibraryForTitle(ArrayList<Book> resultList, String identity,
                                          Hashtable<Integer, BookShelf> shelfCollection)
    {
        boolean found = false;
        Book result = null;
        int shelfID = 0;

        for (BookShelf s : shelfCollection.values())
        {
            result = s.findBookByTitle(identity);
            shelfID = s.getID(); // I want the search results to display what shelf a book was found on
            if (result != null)
            {
                resultList.add(result);
                found = true;
            }
        }

        return found;
    }

}