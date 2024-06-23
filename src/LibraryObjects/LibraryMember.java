package LibraryObjects;

import Utils.Helpers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class LibraryMember
{
    @SuppressWarnings("FieldMayBeFinal")
    private int cardNumber;
    private String firstName;
    private String lastName;
    private List<CheckedBook> booksOnLoan;
    private List<BookShelf> shelves; // goodreads style shelves e.g. read, want to read, reading.
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Create a generic member that will need to have first and last name set
     */
    public LibraryMember() throws SQLException
    {
        firstName = "dummy";
        lastName = "user";
        cardNumber = Helpers.getCardNum();
    }


    /**
     * Create a new user with no checked books and a new card number.
     */
    public LibraryMember(String fName, String lName) throws SQLException
    {
        firstName = fName;
        lastName = lName;
        cardNumber = Helpers.getCardNum();
    }

    /**
     * Create a member with all fields but no checked books
     * @param fName     first name
     * @param lName     last name
     * @param memberNum card number
     */
    public LibraryMember(String fName, String lName, int memberNum) throws SQLException
    {
        firstName = fName;
        lastName = lName;
        cardNumber = memberNum;
    }


    /* GETTERS */
    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    public int getCardNumber()
    {
        return cardNumber;
    }

    public int getTotalBooksOnLoan()
    {
        if (booksOnLoan.isEmpty())
            return 0;

        return booksOnLoan.size();
    }

    /* SETTERS */
    public void setFirstName(String fName)
    {
        firstName = fName;
    }

    public void setLastName(String lName)
    {
        lastName = lName;
    }


    /**
     * Display all data about a member.
     */
    public void display()
    {
        out.println(firstName + " " + lastName + "\nMember ID: " + cardNumber +
                            "\nTotal Loaned: " + booksOnLoan.size());
    }

    /**
     * Get the List containing all the books the user has on loan.
     *
     * @return all books user has on loan
     */
    public List<CheckedBook> getBooksOnLoan()
    {
        if (booksOnLoan.isEmpty())
            return null;

        return booksOnLoan;
    }

    /**
     * Add a new book to the users checked out list.
     *
     * @param B
     * @return true if added, false if not.
     * TODO: Make sure you can't have duplicate books.
     */
    public boolean updateCheckedOutList(Book B)
    {
        if (booksOnLoan.isEmpty())
            return false;
        return booksOnLoan.add((CheckedBook) B);
    }

    /**
     * Collect First and Last name about a new user and add them to the data base.
     *
     * @return true if added, false if not
     * @throws SQLException to handle any exceptions from the user table manager.
     */
    @SuppressWarnings("UnusedReturnValue")
    public boolean collectInfo() throws SQLException
    {
        LibraryMember temp = new LibraryMember();
        out.println("Member First Name: ");
        temp.setFirstName(scanner.nextLine());
        out.println("Member Last Name: ");
        temp.setLastName(scanner.nextLine());
        out.println("Assigned " + temp.getCardNumber() + " card number to " + temp.getFirstName());

        return false;

    }

    /**
     * Query all books checked out by the user.
     *
     * @return true if loaded false if else.
     */
    private boolean loadCheckedList()
    {
        if (booksOnLoan == null)
            booksOnLoan = new ArrayList<>();

        return false;
    }

}
