package Data;

import LibraryObjects.BookShelf;
import utils.IDGenKt;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ShelfTableManager extends DbController
{
    private final Set<BookShelf> bookShelfSet = new HashSet<>();
    private int shelfNum = 0;


    public ShelfTableManager() throws SQLException
    {
        super();

    }


    public Set<BookShelf> getBookShelfSet()
    {
        return bookShelfSet;
    }

    public boolean addShelf(BookShelf bookShelf)
    {
        return bookShelfSet.add(bookShelf);
    }

    public int numOfShelves()
    {
        return bookShelfSet.size();
    }

    public BookShelf findShelfByID(int id)
    {
        for (BookShelf shelf : bookShelfSet)

            if (shelf.getID() == id)
                return shelf;


        return null;
    }

}
