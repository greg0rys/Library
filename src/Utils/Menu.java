package Utils;

import java.util.Scanner;

import static java.lang.System.out;

public class Menu {
    private final static Scanner SCANNER = new Scanner(System.in);
    private static int menuChoice;
    public Menu(){}

    static public int findBookMenu()
    {
        menuChoice = 0; // clear stored value.

        out.println("*** Book Search ***");
        out.println("!. Search by Title");
        out.println("2. Search by Author");
        out.println("3. Search by Genre");
        out.println("4. Search by Price");

        out.println("Choice: ");
        menuChoice = SCANNER.nextInt();

        // Check for choice validity.
        if(menuChoice >0 && menuChoice <=4)
            return menuChoice;

        out.println("Not a valid menu choice, try again..");
        return findBookMenu(); // if we don't have a valid choice call ourselves.
    }

    static public int displayBooksMenu()
    {
        out.println("1. Display all books");
        out.println("2. Display books by title");
        out.println("3. Display books by author");
        out.println("4. Display books by genre");
        out.println("5. Display books by price");

        out.println("Choice:" );
        return SCANNER.nextInt();
    }
}
