package Data;


import LibraryObjects.Book;
import Nodes.CheckedBookNode;
import Nodes.Node;
import UserObjects.LibraryMember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DbController
{
    private final String DB_URL ="jdbc:sqlite:newDB.db";
    private final String SELECT_ALL_BOOKS = "SELECT * FROM Books";
    private final String SELECT_ALL_MEMBERS = "SELECT * FROM LibraryUser";
    private final String SELECT_ALL_CHECKED_BOOKS = "SELECT * FROM CheckedOutBooks";
    private final Node LAST_DB_STATE = new Node();

    private final BookTableManager bookTableManager = new BookTableManager();
    private final CheckedBooksManager checkedBooksManager = new CheckedBooksManager();
    private final UserTableManager userTableManager = new UserTableManager();

    public DbController() { }
    
    public DbController(Node N)
    {
        initNode(N, false);
    }


    protected Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(DB_URL);
    }

    protected boolean pingDB() throws SQLException
    {
        return DriverManager.getConnection(DB_URL) != null;
    }


    protected void initNode(Node T, boolean refresh)
    {
        List<Book> bookTableManagers = bookTableManager.getAllBooks();
        List<LibraryMember> members = userTableManager.getMemberList();
        List<CheckedBookNode> checkedBooksManagers = CheckedBooksManager.getAllCheckedOutBooks();

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

    private void commitChanges(Node lastDbState)
    {
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



    


}
