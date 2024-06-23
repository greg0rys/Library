/**
 * The manager class that will interact with the private table manager.
 * Calls all methods that perform CRUD operations to the SQLite DB
 * @version 061824
 * @author greg0rys
 */
package ManagerClasses;

import Data.CheckedBooksTableManager;
import LibraryObjects.CheckedBook;
import Nodes.CheckedBookNode;

import java.util.ArrayList;
import java.util.List;

public class CheckedBooksManager
{

    private static final CheckedBooksTableManager BOOKS_TABLE_MANAGER = new CheckedBooksTableManager(); // statically create the field so only one instance.

    private static List<CheckedBookNode> allCheckedBooks = BOOKS_TABLE_MANAGER.getAllCheckedOutBooks();
    /**
     * Default no args constructor.
     */
    public CheckedBooksManager()
    {
    }

    public List<CheckedBookNode> getAllCheckedOutBooks() {
        return allCheckedBooks;
    }



}