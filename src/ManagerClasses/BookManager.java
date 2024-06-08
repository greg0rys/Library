package ManagerClasses;

import Data.BookTableManager;
import LibraryObjects.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void display()
    {
        for(Book book : BOOK_LIST)
            book.display();

    }
}