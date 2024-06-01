package LibraryObjects;

import Constants.LoanStatus;


public class CheckedBook extends Book
{

    public CheckedBook(String title, String author, String genre, boolean series,
                       double price, LoanStatus ls)
    {
        super(title, author, genre, series, price, ls);
    }



}
