/**
 * The manager class that will interact with the private table manager.
 * Calls all methods that perform CRUD operations to the SQLite DB
 * @version 061824
 * @author greg0rys
 */
package ManagerClasses;

import Data.CheckedBooksTableManager;
import LibraryObjects.Book;

public class CheckedBooksManager
{

    private static final CheckedBooksTableManager BOOKS_TABLE_MANAGER = new CheckedBooksTableManager(); // statically create the field so only one instance.


    /**
     * Default no args constructor.
     */
    public CheckedBooksManager()
    {
    }



}