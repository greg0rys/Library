package LibraryObjects;

import Constants.LoanStatus;
import Data.CheckedBooksTableManager;

import static java.lang.System.out;


public class CheckedBook extends Book
{

    private LibraryMember memberOfRecord;
    private final static CheckedBooksTableManager CBTM = new CheckedBooksTableManager();
    private int tableID;

    /**
     * Pass in a book and an assigned member
     * @param t the book we will use to set the table.
     * @param m the member we wish to assign the book to.
     */
    public CheckedBook(Book t, LibraryMember m, int tableNum)
    {
        super(t.getTitle(),t.getAuthor(),t.getGenre(),t.aSeries(), t.getPrice(), t.getLoanStatus());
        tableID = tableNum;
        memberOfRecord = m;

    }

    /**
     * default constructor
     * @param title book title
     * @param author book author
     * @param genre book genre
     * @param series book series
     * @param price book price
     * @param ls book loan status e.g. checked out
     */
    public CheckedBook(String title, String author, String genre, boolean series,
                       double price, LoanStatus ls)
    {
        super(title, author, genre, series, price, ls);
    }

    /**
     * @return true when removed false if else.
     */
    public boolean returnBook()
    {
        return returnToLibrary();
    }


    /**
     * Use the Table Manager to delete a record of the checked book
     * @return true when book returned false if else.
     */
    private boolean returnToLibrary()
    {
        CBTM.removeCheckedOutBook(tableID);
        return false;
    }

    /**
     * Update information about the checked book as needed
     * @param B the book we will use to update fields.
     * @param updateType the type of update we wish to perform
     *      <ol>
     *      <ul>1. Title</ul>
     *       <ul>2. Author</ul>
     *       <ul>3. Genre</ul>
     *       <ul>4. Price</ul>
     *       <ul>5. Loan Status</ul>
     *       <ul>6. Is a series</ul>
     *       </ol>
     * @return true when updated false if else.
     */
    public boolean updateBookInfo(Book B, int updateType)
    {
        switch(updateType)
        {
            case 1:
                setTitle(B.getTitle());
                return true;
            case 2:
                setAuthor(B.getAuthor());
                return true;
            case 3:
                setGenre(B.getGenre());
                return true;
            case 4:
                setPrice(B.getPrice());
                return true;
            case 5:
                setLoanStatus(B.getLoanStatus().toString());
                return true;
            case 6:
                setSeries(B.aSeries());
                return true;
            default:
                out.println("Not a valid update choice");
                break;
        }

        return false;
    }



}
