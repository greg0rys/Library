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

    // TODO the managers should become static so that here is always only one instance VS multi if we have several libraries.
    //      The respective managers can be integrated with logon features for other databases
    private  UserManager userManager = new UserManager();
    private BookManager bookManager = new BookManager();
    private ShelfManager shelfManager = new ShelfManager();
    private  CheckedBooksManager checkedBooksManager = new CheckedBooksManager();


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


    /**
     * Find a book in the Library system.
     * @return null if the manager is empty as there will be no books. (Caller must always check for null)
     *  Else a list of search results.
     */
    private List<Book> findBook(int searchType)
    {
        if(bookManager.empty())
            return null;

        /* instead of passing Menu.findBookMenu() as a function arg, lets switch off input */
        switch(searchType)
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

        return null;
    }


    public List<Book> search(boolean displaySearch, int searchType)
    {
        /* copy the menus return value as a representation of the search type. (Used to process display) */
        /* find a book based of the users display search criteria */
        if(displaySearch)
        {
            searchType = Menu.displayBooksMenu();
            return findBook(searchType);
        }

        searchType = Menu.findBookMenu();
        return findBook(searchType);

    }


    /**
     * List each book in the library
     * List BookID as well.
     */
    public void listAllBooks()
    {
        bookManager.displayAllBooks();
    }

    public void displaySearchResults(int searchType, List<Book> result)
    {
        if(result == null || result.isEmpty())
        {
            out.println("No books found.");
            return;
        }

        switch(searchType)
        {
            case 1: listBooksByTitle()
        }
    }

    /**
     * Display all the books with a given title.
     * @param title the title of the book we wish to find - String
     * @return true if printed false if else.
     */
    public boolean listBooksByTitle(String title)
    {
        if(title.isEmpty())
        {
            out.println("No title provided");
            return false;
        }

        bookManager.displayBooksByTitle(bookManager.findByTitle(title.strip()));

        return true;
    }

    /**
     * Lists all the books in the library by a given author.
     *
     * @param author the author to search for
     * @return true if author is empty, indicating no books were found, false otherwise
     */
    public boolean listBooksByAuthor(String author)
    {
        if(author.isEmpty())
        {
            out.println("No author has been provided");
            return false;
        }
        bookManager.displayBooksByAuthor(bookManager.findByAuthor(author));
        return true;
    }

    /**
     * Retrieves a list of books by genre and displays them.
     *
     * @param genre the genre of the books to be listed
     * @return true if the genre is empty (indicating no books were found), false otherwise
     */
    public boolean listBooksByGenre(String genre)
    {
        if(genre.isEmpty())
        {
            out.println("No genre provided.");
            return false;
        }
        bookManager.displayBooksByGenre(bookManager.findByGenre(genre));
        return genre.isEmpty();
    }

    /**
     * Calls the recursive counting method
     *
     */
    public void printTotalBooks()
    {
        // standard error when there is no books in the library
        if(bookManager.empty())
        {
            out.println("There are no books currently in the library");
            return;
        }
        int totalBooks = bookManager.getTotalBooks();
        String isA = "is " + totalBooks + " book in the library";
        String areA = " are a total of " + totalBooks + " books in the library.";
        out.println("There" + (totalBooks > 1 ? areA : isA));


    }

    public void userManager() throws SQLException
    {
        userManager.start();
    }

    /**
     * Retrieves the BookManager object of the Library.
     *
     * @return The BookManager object.
     */
    private BookManager getBookManager()
    {
        return bookManager;
    }

    /**
     * Returns the UserManager object of the Library.
     *
     * @return The UserManager object.
     */
    private UserManager getUserManager()
    {
        return userManager;
    }

    /**
     * Retrieves the ShelfManager object of the Library.
     *
     * @return The ShelfManager object.
     */
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

    /* private display functions for processing search results */

    /**
     * all of these can just call display on their search results since it will print all data about the books anyways.
     * @param res
     */
    private void displayTitleSearch(List<Book> res)
    {
        int i = 0;
        for(Book b : res)
        {
            out.println((i++) + ". " + b.getTitle());
        }
//            b.display();
    }

    private void displayAuthorSearch(List<Book> res)
    {
        int i = 0;
        for(Book b : res)
            out.println((i++) + ". " +)
    }


}