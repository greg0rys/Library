package Data;


import UserObjects.LibraryMember;

import java.util.ArrayList;
import java.util.List;

public class DbController
{
    private Node N;


    public DbController() { }

    /**
     * pass in all the needed ArrayLists to hold persistent data
     * @return
     */
    public boolean load(ArrayList<Book> B, List<BookShelf> CM,
                        List<LibraryMember> LM, List<UserTableManager> USR)
    {
        N = new Node(B, CM, LM, USR);
        return true;
    }


}
