import static java.lang.System.out;

import java.util.Hashtable;
import java.util.Random;


@SuppressWarnings("FieldCanBeLocal")
public class BookShelf
{

    private final int SHELF_CAPACITY = 200; // Capacity of this shelf;
    private final int MAX_ID = 8500;  // the largest ID that a shelf can be assigned;
    private Hashtable<String,Book> books = null;  // the table of books on this shelf.
    private final int SHELF_ID = Math.abs(new Random().nextInt(MAX_ID));  // a randomly generated ID;
    /**
     * Standard empty constructor nothing fancy
     */
    public BookShelf() {}

    /**
     * Constructor that copies an already existing table.
     * @param bookList the list of books we wish to add.
     */
    public BookShelf(Hashtable<String,Book> bookList)
    {
        this.books = new Hashtable<>(bookList);
    }

    public boolean hasCapacity()
    {
        return books.size() < SHELF_CAPACITY;
    }


    /**
     * Print all the books on the shelf.
     */
    public void printShelfContents()
    {
        if(books == null || books.isEmpty())
            out.println("The shelf is empty!");


        for(Book book: books.values())
            book.display();

        displayShelfDetails();

    }

    private void displayShelfDetails()
    {

        out.println("Other shelf details:");
        out.println("\tShelf ID: " + SHELF_ID);
        out.println("\tNumber of books: " + books.size());
        out.println("\tUsed Shelf Space: " + calculateUsedSpace() + "/" + SHELF_CAPACITY);
    }

    /**
     * Get the ID of this shelf.
     * @return SHELF_ID the ID of this shelf.
     */
    public int getID()
    {
        return SHELF_ID;
    }

    /**
     * Add a new Book to the shelf
     * @param newBook the book we are adding.
     * @return true if we added the book false if else.
     */
    public boolean addBook(Book newBook)
    {
        if(books == null)
            books = new Hashtable<>();

        if (books.size() >= SHELF_CAPACITY)
            return false;

        if(newBook == null)
            return false;

        books.put(newBook.getTitle(), newBook);

        return true;
    }

    /**
     * Find a Book on the shelf.
     * @param title the name of the Book we are looking for
     * @return the Book we were looking for null if else.
     */
    public Book findBookByTitle(String title)
    {
        if(books == null || books.isEmpty() || title.isEmpty())
            return null;

        books.contains(title);
        return books.get(title);
    }

    /**
     * Remove a Book by it's title.
     * @param title the name of the book we are looking to remove
     * @return true if we were able to remove the book else false.
     */
    public boolean removeBook(String title)
    {
        if (books == null || books.isEmpty() || title.isEmpty())
            return false;

        return books.remove(title) != null;
    }

    /**
     * Move a Book from this Shelf to a new Shelf.
     * @param title the title of the book we wish to move
     * @param shelf the new shelf the book will be on
     * @return true if complete false is else.
     */
    public boolean moveBook(String title, BookShelf shelf)
    {
        if (books == null || books.isEmpty() || title.isEmpty() || shelf == null)
            return false;

        Book book = books.get(title);
        if (book == null)
            return false;

        if (shelf.addBook(book))
        {
            books.remove(title);
            return true;
        }

        return false;
    }

    /**
     * Calculate how many more books can fit on the shelf.
     * @return the number of books that can fit on this shelf.
     */
    public int calculateUsedSpace()
    {
        if(books == null)
            return 0;

        return (books.size() - SHELF_CAPACITY);
    }

    /**
     * Get total number of books
     * @return the total number of books.
     */
    public int getNumberOfBooks()
    {
        if (books == null || books.isEmpty())
            return 0;

        return books.size();
    }

    /**
     * Merge the given shelf into this shelf.
     * @param shelf the shelf we wish to merge into this shelf.
     * @return true if the merge was able to happen false if else.
     */
    public boolean mergeShelf(BookShelf shelf)
    {
        if (shelf == null || shelf.books == null || shelf.books.isEmpty())
            return false;

        for (String title : shelf.books.keySet())
        {
            Book book = shelf.books.get(title);
            if (book != null)
                books.put(title, book);
        }

        shelf = null;
        return true;
    }

}
