package LibraryObjects;

import java.sql.SQLException;
import java.util.*;

import ManagerClasses.BookManager;
import ManagerClasses.ShelfManager;
import ManagerClasses.UserManager;
import utils.Helpers;
import static java.lang.System.out;

public class Library
{

    private HashMap<Integer, BookShelf> shelfMap = null;
    private final UserManager userManager = new UserManager();
    private final BookManager bookManager = new BookManager();
    private final ShelfManager shelfManager = new ShelfManager();
    private final Scanner scanner = new Scanner(System.in);



    public Library() throws SQLException
    { /* default constructor */ }

    public boolean hasBooks()
    {
        return (!bookManager.empty());
    }


    /**
     * Add a shelf to the library
     *
     * @param bookShelf the LibraryObjects.BookShelf that will be added to the library
     */
    public boolean addBookShelf(BookShelf bookShelf)
    {
        return shelfManager.addShelf(bookShelf);
    }

    /**
     * Add a common book to a shelf AFTER it gets added to the library if it's new.
     * TODO: Implement boolean check.
     */
    public void addBook()
    {

        // now do something to the shelf. This feels relational.... EG Shelf > LibraryObjects.Book || LibraryObjects.Book > Shelf
        shelfManager.insertBook(Helpers.collectNewBookData());


    }


//    private BookShelf getOpenShelf()
//    {
//        for(BookShelf bookShelf : shelfMap.values())
//        { if (bookShelf.hasCapacity()) return bookShelf; }
//
//        return null;
//    }


    public boolean findBook()
    {
        return true;
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
    public void displayTotals()
    {
        if (shelfMap == null)
        {
            return;
        }

        printTotalBooks();
        numShelves();
        out.println();
    }

    private void numShelves()
    {
        out.println("Total Number of Books: " + shelfManager.shelfCount());
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