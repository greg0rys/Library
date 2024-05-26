package Nodes;

import LibraryObjects.Book;
import UserObjects.LibraryMember;

public class CheckedBookNode
{
    private LibraryMember checkedBy;
    private Book titleChecked;

    public CheckedBookNode() { }

    public CheckedBookNode(LibraryMember checkedBy, Book titleChecked)
    {
        this.checkedBy = checkedBy;
        this.titleChecked = titleChecked;
    }

    public LibraryMember getCheckedBy()
    {
        return checkedBy;
    }

    public Book getTitleChecked()
    {
        return titleChecked;
    }

    public void setCheckedBy(LibraryMember checkedBy)
    {
        this.checkedBy = checkedBy;
    }

    public void setTitleChecked(Book titleChecked)
    {
        this.titleChecked = titleChecked;
    }
}
