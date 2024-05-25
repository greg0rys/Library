package utils;

import LibraryObjects.Book;

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
}
