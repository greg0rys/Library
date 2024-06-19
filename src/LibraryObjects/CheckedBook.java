package LibraryObjects;

import Constants.LoanStatus;


public class CheckedBook extends Book
{

    private LibraryMember memberNode;

    /**
     * Pass in a book and an assigned member
     * @param t the book we will use to set the table.
     * @param m the member we wish to assign the book to.
     */
    public CheckedBook(Book t, LibraryMember m)
    {
        super(t.getTitle(),t.getAuthor(),t.getGenre(),t.aSeries(), t.getPrice(), t.getLoanStatus());
        memberNode = m;

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

    public boolean returnBook()
    {
        return returnToLibrary();
    }


    private boolean returnToLibrary()
    {
        return false;
    }


}
