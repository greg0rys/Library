package Data;

import UserObjects.LibraryMember;

import java.util.ArrayList;
import java.util.List;

public class Node
{
    private List<Book> books;
    private List<BookShelf> shelfList;
    private List<LibraryMember> libraryMemberList;
    
    public Node () {
        books = new ArrayList<>();
        shelfList = new ArrayList<>();
        libraryMemberList = new ArrayList<>();
    }

    public Node(List<Book> BTM, List<BookShelf> CM,
                List<LibraryMember> SHM, List<UserTableManager> USM)
    {
        books = new ArrayList<>();
        shelfList = new ArrayList<>();
        libraryMemberList = new ArrayList<>();
        
        books.addAll(BTM);
        shelfList.addAll(CM);
        libraryMemberList.addAll(SHM);
    }
    
    public List<Book> getBooks()
    {
        return books;
    }

    public List<BookShelf> getShelfList()
    {
        return shelfList;
    }
    
    public List<LibraryMember> getLibraryMemberList()
    {
        return libraryMemberList;
    }
    
    public void setBooks(List<Book> books)
    {
        this.books = books;
    }
    
    public void setShelfList(List<BookShelf> shelfList)
    {
        this.shelfList = shelfList;
    }
    
    public void setLibraryMemberList(List<LibraryMember> libraryMemberList)
    {
        this.libraryMemberList = libraryMemberList;
    }
    

    
}
