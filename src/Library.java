import java.util.*;

import static java.lang.System.out;

public class Library
{

    private HashMap<Integer, BookShelf> shelfMap = null;
    private boolean hasBooks = false;
    private static LibraryDB db;
    private int totalBooks = 0;
    public Library() { /* default constructor */ }

    public boolean hasBooks()
    {
        return (!shelfMap.isEmpty());
    }


    /**
     * Add a shelf to the library
     *
     * @param bookShelf the BookShelf that will be added to the library
     */
    public void addBookShelf(BookShelf bookShelf)
    {
        if (shelfMap == null)
            shelfMap = new HashMap<>();

        shelfMap.put(bookShelf.getID(), bookShelf);
    }

    public void addBook()
    {
        Book newBook = Helpers.collectNewBookData();
        // now do something to the shelf. This feels relational.... EG Shelf > Book || Book > Shelf
        LibraryDB.addBookToLibrary(Helpers.collectNewBookData());


    }



    private BookShelf getOpenShelf()
    {
        for(BookShelf bookShelf : shelfMap.values())
        { if (bookShelf.hasCapacity()) return bookShelf; }

        return null;
    }

    public boolean findBook()
    {
        return true;
    }
    /**
     * Get a BookShelf of a given ID number
     *
     * @param shelfId the shelf ID of the shelf.
     * @return the BookShelf object that has a matching shelf ID
     */
    public BookShelf getBookShelf(int shelfId)
    {
        return shelfMap.get(shelfId);
    }

    /**
     * Print a list of all Shelves in the Library - Includes Shelf IDs
     */
    public void printShelfMap()
    {
        for (Map.Entry<Integer, BookShelf> shelf : shelfMap.entrySet())
            out.println("Shelf ID: " + shelf.getKey() + ", Book Shelf: " + shelf.getValue());
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
        printTotalShelves();
        out.println();
    }

    private void printTotalShelves()
    {
    }

    /**
     * List each book in the library
     * List BookID as well.
     */
    public void listAllBooks()
    {

        for(Book books : LibraryDB.getAllBooks())
        {
            out.println();
            books.display();
            out.println("**************");
            out.println();
        }
        printTotalBooks();



    }

    /**
     * Calls the recursive counting method
     *
     */
    public void printTotalBooks()
    {
        totalBooks = LibraryDB.getNumBooks();
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