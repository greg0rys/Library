package LibraryObjects;

import Data.LibraryDB;
import utils.Helpers;
import Data.LibraryDB;

import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryMember
{
    @SuppressWarnings("FieldMayBeFinal")
    private int cardNumber;
    private String firstName;
    private String lastName;
    private ArrayList<Book> checkedOutBooks;

    public LibraryMember()
    {
        this.firstName = "";
        this.lastName = "";
        this.cardNumber = Helpers.getCardNum();
    }

    public LibraryMember(String firstName, String lastName, int cardNum)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cardNumber = cardNum;
        this.checkedOutBooks = LibraryDB.getUsersBooks(cardNum);
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String fName)
    {
        this.firstName = fName;
    }

    public void setLastName(String lName)
    {
        this.lastName = lName;
    }

    public int getCardNumber()
    {
        return cardNumber;
    }


    public int getTotalBooksOnLoan()
    {
        if(checkedOutBooks.isEmpty())
            return 0;

        return checkedOutBooks.size();
    }

    public boolean checkOutBook(Book B) throws SQLException, ClassNotFoundException
    {
        if(B == null)
            return false;

        return LibraryDB.userCheckout(B, cardNumber);
    }

    public boolean returnBook(Book B)
    {
        if(B == null)
            return false;

        return LibraryDB.returnBookToLibrary(B, cardNumber); // maybe not cardnum..
    }

    public boolean updateCheckedOutList(Book B)
    {
        if(checkedOutBooks.isEmpty())
            return false;
        return checkedOutBooks.add(B);
    }

    public LibraryMember collectInfo()
    {
        return new LibraryMember();
    }

    public void display()
    {
    }
}
