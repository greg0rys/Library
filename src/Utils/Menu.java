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

        menuChoice = SCANNER.nextInt();

        // Check for choice validity.
        if(menuChoice >0 && menuChoice <=4)
            return menuChoice;

        out.println("Not a valid menu choice, try again..");
        return findBookMenu(); // if we don't have a valid choice call ourselves.
    }

}
