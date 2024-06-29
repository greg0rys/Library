package Utils;

import LibraryObjects.Book;
import LibraryObjects.LibraryMember;

<<<<<<< HEAD:src/utils/Helpers.java
import java.sql.SQLException;
=======
>>>>>>> 9cc9aabedd5cb697edf9c2bade802971a5321f85:src/Utils/Helpers.java
import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;
/*
 * This class holds helper functions used throughout the program.
 */

import java.util.Random;

public class Helpers
{
    private static final int MAX_BOOK_ID = 300500;
    private static final int MAX_SHELF_ID = 400567;
    private static final int MAX_CARD_NUM = (Integer.MAX_VALUE / 4);
    private static final Random RANDOM = new Random();
    private static final Scanner SCANNER = new Scanner(System.in);
<<<<<<< HEAD:src/utils/Helpers.java
=======
    private static String input;
>>>>>>> 9cc9aabedd5cb697edf9c2bade802971a5321f85:src/Utils/Helpers.java


    public Helpers() {};

    static public int getBookID()
    {
        return RANDOM.nextInt(MAX_BOOK_ID);
    }

    static public int getShelfID()
    {
        return RANDOM.nextInt(MAX_SHELF_ID);
    }

    static public int getCardNum() { return RANDOM.nextInt(MAX_CARD_NUM); }


    static public Book collectNewBookData()
    {
        Book temp = new Book();
        Scanner scanner = new Scanner(System.in);
        out.print("Enter book title: ");
        temp.setTitle(scanner.nextLine());
        out.print("Enter book author: ");
        temp.setAuthor(scanner.nextLine());
        out.print("Enter book genre: ");
        temp.setGenre(scanner.nextLine());
        out.print("Is the book part of a series? (true/false): ");
        temp.setSeries(scanner.nextBoolean());
        out.print("Enter book cost: $");
        temp.setPrice(scanner.nextDouble());

        return temp;
    }

    static public LibraryMember collectNewMember()
    {
        LibraryMember member = new LibraryMember();
        out.println("Members First Name: ");
        member.setFirstName(SCANNER.nextLine());
        out.println("Members Last Name: ");
        member.setLastName(SCANNER.nextLine());

        out.println(member.getFirstName() + " has been assigned card number: " + member.getCardNumber());

        return member;
    }

    static public void printArrayList(List<Book> books)
    {
        if(books.isEmpty())
            System.out.println("No books found.");
        for(Book book : books)
        {
            book.display();
            out.println();
        }
    }

<<<<<<< HEAD:src/utils/Helpers.java
    static public void addNewLine()
    {
        out.println();
    }


=======
    public static String collectBookTitle()
    {
        // TODO: input validation
        clearStringInput();
        out.println("Enter Books Title: ");
        input = nextLine();
        return (input.isEmpty()) ? collectBookTitle() : input;
    }

    public static String collectBookAuthor()
    {
        // TODO: input validation
        clearStringInput();
        out.println("Enter Books Author: ");
        input = nextLine();
        return (input.isEmpty()) ? collectBookAuthor() : input;
    }

    public static String collectBookGenre()
    {
        // TODO: input validation
        clearStringInput();
        out.println("Enter Genre of Books: ");
        input = nextLine();
        return (input.isEmpty()) ? collectBookGenre() : input; // if empty recurse.
    }

    public static Double collectBookPrice()
    {
        // TODO: input validation

        out.println("Enter Books Price Range: ");
        return SCANNER.nextDouble();
    }


    /**
     * shorthand method to call Scanner.nextLine();
     * @return the input string;
     */
    private static String nextLine()
    {
        return SCANNER.nextLine().strip(); // remove any whitespace.
    }

    private static void clearStringInput()
    {
        input = "";
    }
>>>>>>> 9cc9aabedd5cb697edf9c2bade802971a5321f85:src/Utils/Helpers.java
}
