/**
 * TODO: Create database refresh methods in the manager and table manager to make sure our books stay updated.
 */
package ManagerClasses;

import Data.BookTableManager;
import LibraryObjects.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class BookManager
{
    private final List<Book> BOOK_LIST = new ArrayList<>();
    @SuppressWarnings("FieldCanBeLocal")
    private final BookTableManager BOOK_TABLE_MANAGER = new BookTableManager();

    public BookManager() throws SQLException
    {
        BOOK_LIST.addAll(BOOK_TABLE_MANAGER.getAllBooks());
    }

    public boolean empty()
    {
        return BOOK_LIST.isEmpty();
    }

    public int getTotalBooks()
    {
        if(BOOK_LIST.isEmpty())
            return 0;

        return BOOK_LIST.size();
    }

    /**
     * Display all books in the library.
     */
    public void displayAllBooks()
    {
        for(Book book : BOOK_LIST)
            book.display();

        out.println("There are a total of " + BOOK_LIST.size() + " books in the library ");
    }

    /**
     * Pass in a single book to be displayed.
     * @param B the book we wish to display from our search result.
     */
    public void displaySingleBook(Book B)
    {
        if(B == null) {
            out.println("No data to display");
            return;
        }

        B.display();
    }

    public void displayBooksByTitle(List<Book> titles)
    {
        if(titles.isEmpty())
        {
            out.println("No matching titles found");
            return;
        }

        String title = titles.getFirst().getTitle();

        for(Book book:titles)
            book.display();

        out.println("There are a total of " + titles.size() + " books with the title " + title);
    }

    /**
     * Display a collection of books by a given author - used to display search results.
     * @param authorList the list of books by the given author
     */
    public void displayBooksByAuthor(List<Book> authorList)
    {
        if(authorList.isEmpty())
        {
            out.println("No books to display - does author exist?");
            return;
        }
        String authorName = authorList.getFirst().getAuthor();

        for (Book book : authorList)
            book.display();

        out.println("There are " + authorList.size() + " books by the author " + authorName);
    }

    /**
     * Displays the books of a specific genre.
     *
     * @param genreList the list of books to be displayed
     */
    public void displayBooksByGenre(List<Book> genreList)
    {
        if (genreList.isEmpty()) {
            out.println("No books to display - does genre exist?");
            return;
        }

        for (Book book : genreList)
            book.display();

        out.println("There are " + genreList.size() + " books in the genre " + genreList.getFirst().getGenre());
    }

    /**
     * Finds books in the library system that match the given title.
     *
     * @param title the title to search for
     * @return a list of books that match the given title
     */
    public List<Book> findByTitle(String title)
    {
        List<Book> foundBooks = new ArrayList<>();

        for (Book book : BOOK_LIST)
            if (book.getTitle().equalsIgnoreCase(title))
                foundBooks.add(book);

        return foundBooks;
    }

    /**
     * Finds books in the Library system that match the given author.
     *
     * @param author the author to search for
     * @return a list of books that match the given author
     */
    public List<Book> findByAuthor(String author)
    {
        List<Book> foundBooks = new ArrayList<>();

        for (Book book : BOOK_LIST)
            if (book.getAuthor().equalsIgnoreCase(author))
                foundBooks.add(book);

        return foundBooks;
    }

    /**
     * Finds books in the library system that match the given genre.
     *
     * @param genre the genre to search for
     * @return a list of books that match the given genre
     */
    public List<Book> findByGenre(String genre)
    {
        List<Book> foundBooks = new ArrayList<>();

        for (Book book : BOOK_LIST)
            if (book.getGenre().equalsIgnoreCase(genre))
                foundBooks.add(book);

        return foundBooks;
    }

    /**
     * Finds books in the library system that match the given price.
     *
     * @param price the price to search for
     * @return a list of books that match the given price
     */
    public List<Book> findByPrice(Double price)
    {
        // return an arraylist of all books that match the price
        List<Book> foundBooks = new ArrayList<>();

        for (Book book : BOOK_LIST)
            if (book.getPrice().equals(price))
                foundBooks.add(book);

        return foundBooks;
    }

    public boolean addBook(Book book)
    {
        return BOOK_LIST.add(book);
    }
}