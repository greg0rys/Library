package Nodes;

import Data.CheckedBooksManager;
import Data.UserTableManager;
import LibraryObjects.Book;
import UserObjects.LibraryMember;

import java.util.ArrayList;
import java.util.List;

public class Node
{
    private List<Book> bookList;
    private List<CheckedBookNode> checkedBooksList;
    private List<LibraryMember> libraryMemberList;

    
    public Node () {
        bookList = new ArrayList<>();
        checkedBooksList = new ArrayList<>();
        libraryMemberList = new ArrayList<>();
    }

    public Node(List<Book> BTM, List<CheckedBookNode> CM,
                List<LibraryMember> SHM)
    {
        bookList = new ArrayList<>();
        checkedBooksList = new ArrayList<>();
        libraryMemberList = new ArrayList<>();
        
        bookList.addAll(BTM);
        checkedBooksList.addAll(CM);
        libraryMemberList.addAll(SHM);
    }
    
    public List<Book> getBooks()
    {
        return bookList;
    }



    public void setBooks(List<Book> bookTableManagers)
    {
        bookList = bookTableManagers;
    }
    

    
    public void setLibraryMemberList(List<LibraryMember> libraryMemberList)
    {
        this.libraryMemberList = libraryMemberList;
    }


    public void setCheckedBooks(List<CheckedBookNode> checkedBooksManagers)
    {
        checkedBooksList = checkedBooksManagers;
    }

    public List<CheckedBookNode> getCheckedBooks()
    {
        return checkedBooksList;
    }

    public List<Book> getBookList()
    {
        return bookList;
    }

    public List<LibraryMember> getLibraryMemberList()
    {
        return libraryMemberList;
    }



}
