package Data;


import LibraryObjects.Book;
import Nodes.CheckedBookNode;
import Nodes.Node;
import LibraryObjects.LibraryMember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DbController
{
    private final String DB_URL ="jdbc:sqlite:newDB.db";
    private final Node LAST_DB_STATE = new Node();

    private final BookTableManager bookTableManager = new BookTableManager();
    private final CheckedBooksManager checkedBooksManager = new CheckedBooksManager();
    private final UserTableManager userTableManager = new UserTableManager();

    public DbController() throws SQLException
    { }
    
    public DbController(Node N) throws SQLException
    {
        initNode(N, false);
    }

    public List<Book> getAllBooks()
    {
        return new ArrayList<>();
    }


    protected Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(DB_URL);
    }

    protected boolean pingDB() throws SQLException
    {
        return DriverManager.getConnection(DB_URL) != null;
    }


    /**
     * initialize the node to hold all the data on first run.
     * @param T the node that will hold the data.
     * @param refresh true if you want to simply refresh the data, false if else.
     */
    protected void initNode(Node T, boolean refresh)
    {
        Set<Book> bookTableManagers = bookTableManager.getAllBooks();
        List<LibraryMember> members = userTableManager.getMemberList();
        List<CheckedBookNode> checkedBooksManagers = checkedBooksManager.getAllCheckedOutBooks();

        if(!refresh)
        {
            T.setBooks(bookTableManagers);
            LAST_DB_STATE.setBooks(bookTableManagers);

            T.setLibraryMemberList(members);
            LAST_DB_STATE.setLibraryMemberList(members);

            T.setCheckedBooks(checkedBooksManagers);
            LAST_DB_STATE.setCheckedBooks(checkedBooksManagers);
        }
        else
        {

            LAST_DB_STATE.getBookList().clear();
            LAST_DB_STATE.getLibraryMemberList().clear();
            LAST_DB_STATE.getCheckedBooks().clear();
            
            LAST_DB_STATE.setBooks(bookTableManagers);
            LAST_DB_STATE.setLibraryMemberList(members);
            LAST_DB_STATE.setCheckedBooks(checkedBooksManagers);
            
            commitChanges(LAST_DB_STATE);
        }

    }

    /**
     * Commit changes to the last known state of the database.
     * @param lastDbState the last known database state.
     */
    private void commitChanges(Node lastDbState)
    {
        //
    }

    public boolean refresh(Node N)
    {
        if(N == null)
            return false;
        
        if(!LAST_DB_STATE.equals(N)) {
            initNode(null, true); // pass null + true to simply refresh 
            return true;
        }
        
        return false;
    }

    public boolean addBookToLibrary(Book newBook)
    {
       return bookTableManager.addBook(newBook);
    }


    public int getNumBooks()
    {
        return bookTableManager.getAllBooks().size();
    }

    


}
