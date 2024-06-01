package LibraryObjects;

import utils.Helpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class LibraryMember
{
    @SuppressWarnings("FieldMayBeFinal")
    private int cardNumber;
    private String firstName;
    private String lastName;
    private int bloan;
    private ArrayList<Book> checkedOutBooks;
    private List<CheckedBook> booksOnLoan;

    /**
     * Create a generic member that will need to have first and last name set
     *
     */
    public LibraryMember()
    {
        firstName = "dummy";
        lastName = "user";
        cardNumber = Helpers.getCardNum();
    }


    /**
     * Create a new user with no checked books and a new card number.
     */
    public LibraryMember(String fName, String lName)
    {
        firstName = fName;
        lastName = lName;
        cardNumber = Helpers.getCardNum();
    }

    /**
     * Create a member with all fields but no checked books
     * @param fName first name
     * @param lName last name
     * @param memberNum card number
     */
    public LibraryMember(String fName, String lName, int memberNum)
    {
        firstName = fName;
        lastName = lName;
        cardNumber = memberNum;
    }

    /**
     * Construct used to load member from DB to include a List of all checked out books
     * @param fName Member First Name
     * @param lName Member Last Name
     * @param memberID Member ID
     * @param checkedBooks List Structure storing all checked books
     */
    public LibraryMember(String fName, String lName, int memberID, List<CheckedBook> checkedBooks)
    {
        firstName = fName;

        lastName = lName;

        cardNumber = memberID;

        checkedOutBooks = new ArrayList<>(checkedBooks);
    }


    public LibraryMember(String firstName, String lastName, int userID, int numBooksOnLoan, int cardNumber)
    {

        firstName = firstName;

        lastName = lastName;

        cardNumber = userID;

        bloan = numBooksOnLoan;

        cardNumber = cardNumber;

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

    public String getFullUserInfo()
    {
        return firstName + " " + lastName + "\nMember ID: " + cardNumber + "\nTotal Loaned: " + booksOnLoan.size();
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
        return cardNumber;
    }


    public int getTotalBooksOnLoan()
    {
        if(booksOnLoan.isEmpty())
            return 0;

        return booksOnLoan.size();
    }


    public boolean updateCheckedOutList(Book B)
    {
        if(booksOnLoan.isEmpty())
            return false;
        return booksOnLoan.add((CheckedBook) B);
    }

    public LibraryMember collectInfo()
    {
        return new LibraryMember();
    }

    public void display()
    {
        out.println(getFullUserInfo());
    }
}
