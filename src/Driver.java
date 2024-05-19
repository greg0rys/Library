import java.util.Scanner;

import static java.lang.System.out;

public class Driver
{
    private static final Scanner scanner = new Scanner(System.in);
    private static Library masterLibrary = new Library();
    Driver()
    { }

    public static void start()
    {
        controller();
    }


    private static void controller()
    {
        boolean valid = false;
//        int numBooks = (masterLibrary.hasBooks() ? masterLibrary.getTotalBooks() : 0);
        Book newBook = null;
        displayMenu();

        out.print("Please enter your choice: ");
        int choice = scanner.nextInt();
        valid = validateChoice(choice);

        choice = valid ? choice : 16; // if the choice isn't valid return 16 to show switch error else choice

        switch (choice)
        {
            case 0:
                out.println("Goodbye!");
                break;
            case 1:
//                out.println("The total number of books is: " + numBooks);
                controller();
                break;
            case 2:
               newBook = Helpers.collectNewBookData();
               out.println("Added: ");
               newBook.display();
               out.println();
               controller();
                break;
            case 3:
                out.println("LOL");
                controller();
                break;
            case 16:
                out.println("Not a valid choice, try again");
                controller(); // if you just run displayMenu() it causes a crash because there is no input given in displayMenu so it just ends the program. Call the controller again to start over.
                break;
            default:
                controller();
                break;
        }
    }


    private static boolean validateChoice(int choice)
    {
        return choice >= 0 && choice <= 3 || choice == 16;
    }


    private static void displayMenu()
    {
        out.println("1. Display total number of books");
        out.println("2. Add a book to the library");
        out.println("3. Print shelf map");
        out.println("0. Exit");
    }

}
