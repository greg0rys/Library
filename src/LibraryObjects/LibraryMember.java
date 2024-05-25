package LibraryObjects;

import Data.LibraryDB;
import utils.Helpers;

import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryMember
{
    private final int CARD_NUMBER = Helpers.getCardNum();
    private String firstName;
    private String lastName;
    private final ArrayList<Book> checkedOutBooks = new ArrayList<>();

    public LibraryMember()
    {
        firstName = "";
        lastName = "";
    }

    public LibraryMember(String firstName, String lastName, int totalOnLoan)
    {
        this.firstName = firstName;
        this.lastName = lastName;
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
        firstName = fName;
    }

    public void setLastName(String lName)
    {
        lastName = lName;
    }

    public int getCardNumber()
    {
        return CARD_NUMBER;
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

        return LibraryDB.userCheckout(B, CARD_NUMBER);
    }

    public boolean returnBook(Book B)
    {
        if(B == null)
            return false;

        return LibraryDB.returnBookToLibrary(B, CARD_NUMBER); // maybe not cardnum..
    }

    public boolean updateCheckedOutList(Book B)
    {
        if(checkedOutBooks.isEmpty())
            return false;
        return checkedOutBooks.add(B);
    }

}
