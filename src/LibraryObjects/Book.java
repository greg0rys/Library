package LibraryObjects;

import java.util.ArrayList;
import java.util.Scanner;
import utils.Helpers;
import utils.LoanStatus;

import static java.lang.System.out;

public class Book
{

    private String title = "";
    private String author = "";
    private String genre = "";
    private final int BOOK_ID = Helpers.getBookID();
    private boolean isSeries = false;
    private ArrayList<Book> booksInSeries = null;
    private double price = 0;
    private final Scanner inputScanner = new Scanner(System.in);
    private LoanStatus loanStatus;

    /**
     * Create a LibraryObjects.Book object with all user supplied fields.
     * @param bTitle the Books title
     * @param bAuthor the Books author
     * @param bGenre the Books Genre
     * @param inSeries is the book a Series? T/F
     */
    public Book(String bTitle, String bAuthor, String bGenre, boolean inSeries, double bPrice)
    {
        title = bTitle;
        author = bAuthor;
        genre = bGenre;
        isSeries = inSeries;
        price = bPrice;

        loanStatus = LoanStatus.CHECKED_IN;
        if(isSeries) booksInSeries = new ArrayList<>();
    }

    /**
     * No args constructor | all fields init to placeholders.
     */
    public Book()
    {
        setAllEmpty();
    }

    // Get and Set methods for the LibraryObjects.Book Object
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    // End Get and Set methods for LibraryObjects.Book Objects titles

    // Get and Set methods for the Books author
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    // End Get and Set methods for Books Author

    // Get and Set methods for the Books Genre
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    // End Get and Set methods for the Books genre

    /**
     * Get the Books ID
     * @return the ID of the LibraryObjects.Book
     */
    public int getBook_id() { return BOOK_ID; }


    // Get and Set methods for the Books Series value
    public boolean aSeries() { return isSeries; }
    public void setSeries (boolean series) { isSeries = series; }
    // End Get and Set methods for the Books Series value


    /**
     * Nicely display all values of the LibraryObjects.Book.
     */
    public void display()
    {

        out.printf("Title: %s\nAuthor: %s\nGenre: %s\nID: %d\nPrice: $%s%n",
                   title, author, genre, BOOK_ID, price);
    }

    /**
     * Display the ArrayList of all Books in the series.
     */
    public void displaySeries()
    {
        if(booksInSeries == null || booksInSeries.isEmpty()) return;
        out.println("Total Books in this series: " + booksInSeries.size());
        if(isSeries)
            for(Book b : booksInSeries)
            {
                b.display();
                out.println("***");
                out.println();
            }

        else
            out.println("This book is not part of a series.");
    }

    public boolean collectBookInfo(Book newBook)
    {
        if(newBook == null) newBook = new Book();
        return collect(newBook);
    }

    private boolean collect(Book newBook)
    {
        String title = "";
        String author = "";
        String genre = "";
        boolean isSeries = false;
        ArrayList<Book> booksInSeries = null;
        double price = 0;
        boolean valid = false;

        out.print("Please enter the book title: ");
        title = inputScanner.nextLine();
        out.print("Please enter the author: ");
        author = inputScanner.nextLine();
        out.print("Please enter the genre: ");
        genre = inputScanner.nextLine();
        out.print("Is this book part of a series? (true/false): ");
        isSeries = inputScanner.nextBoolean();
        out.print("Please enter the price: ");
        price = inputScanner.nextDouble();


       newBook.setAll(title, author, genre, isSeries, price);
       return true;

    }

    private void setAll(String title, String author, String genre, boolean isSeries, double price)
    {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isSeries = isSeries;
        this.price = price;
    }


    private void setAllEmpty()
    {
        title = " ";
        author = " ";
        genre = " ";
        isSeries = false;
        price = 0.00;

    }

    public void setPrice(Double newPrice)
    {
        price = newPrice;
    }
    public Double getPrice() { return price; }

    public LoanStatus getLoanStatus() { return loanStatus; }
    public void setLoanStatus(String status)
    {
        if(!status.equals(LoanStatus.CHECKED_IN.toString()) || !status.equals(LoanStatus.CHECKED_OUT.toString()))
            return;
        loanStatus = LoanStatus.valueOf(status);
    }

}
