import java.util.ArrayList;

public class LibraryMember
{
    private final int CARD_NUMBER = Helpers.getCardNum();
    private String firstName;
    private String lastName;
    private ArrayList<Book> totalBooksOnLoan;

    public LibraryMember()
    {
        firstName = "";
        lastName = "";
        totalOnLoan = 0;
    }

    public LibraryMember(String firstName, String lastName, int totalOnLoan)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalOnLoan = totalOnLoan;
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

    public boolean checkOutBook(Book item)
    {
        return false;
    }

}
