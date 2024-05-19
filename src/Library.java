import java.util.*;

import static java.lang.System.out;

public class Library
{

    private HashMap<Integer, BookShelf> shelfMap = null;
    private boolean hasBooks = false;

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

    public boolean addBook()
    {
        // if our shelfMap is empty initialize it
        if(shelfMap.isEmpty())
            shelfMap = new HashMap<>();

        Book temp = Helpers.collectNewBookData();
        out.println(temp.getTitle() + " has been added to the library");
        BookShelf shelf = getOpenShelf();

        if(shelf != null)
            return shelf.addBook(temp);



        return false; // if we make it here we didn't add the book
    }
    public boolean addBookInitialized(Book newBook)
    {
        newBook = Helpers.collectNewBookData();

        BookShelf shelf = null;
        if(shelfMap == null || shelfMap.isEmpty())
            return false;

        shelf = getOpenShelf();

        if(shelf != null)
        {
            return shelf.addBook(newBook);
        }

        return false;
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

        out.println("Total Books: " + getTotalBooks());
        out.println("Total Shelves: " + shelfMap.size());
        out.println();
    }

    /**
     * List each book in the library
     * List BookID as well.
     */
    public void listAllBooks()
    {
        if (shelfMap.isEmpty())
        {
            out.println("No books found in the library :(");
        }


    }

    /**
     * Calls the recursive counting method
     *
     * @return the sum of all books in the library
     */
    public int getTotalBooks()
    {
        return countBooksR(new ArrayList<>(shelfMap.values()), 0, 0);
    }

    /*
        Private methods
     */

    /**
     * Recurse the shelf map to get a total count of all books
     *
     * @param shelves the BookShelf objects we wish to count
     * @param index   the current position in the list
     * @param sum     the total number of all books
     * @return the sum of all books on the shelves, respecting the terminating steps
     */
    private int countBooksR(List<BookShelf> shelves, int index, int sum)
    {
        if (shelves == null || index < 0)
            return 0;

        if (index >= shelves.size())
            return sum;

        return countBooksR(shelves, index + 1, (sum + shelves.get(index).getNumberOfBooks()));
    }


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