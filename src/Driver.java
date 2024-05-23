import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;
import org.sqlite.*;

public class Driver
{
    private static final Scanner scanner = new Scanner(System.in);
    private static Library masterLibrary = new Library();
    private final static String DB_URL = "jdbc:sqlite:src/Library.db";
    Driver()
    { }

    public static void start() throws SQLException
    {
        checkDB();
        // always do this at least once regardless of condition
        do
        {
            controller();

        } while(menu() != 0);
    }

    private static void checkDB() throws SQLException
    {
        try(Connection con = DriverManager.getConnection(DB_URL))
        {
            if(con != null)
                out.println("Connected to the database successfully.");
            else
                out.println("Created the database successfully.");
        }
        catch(SQLException e)
        {
            out.println(e.getErrorCode());
        }
    }


    private static void controller()
    {
        out.println();
        boolean valid = false;
        Book newBook = null;
        int choice = menu();
        int totalBooks;
        String isA;
        String areA;


        choice = validateChoice(choice) ? choice : 16; // if the choice isn't valid return 16 to show switch error else choice or don't because an invalid choice invokes default



        switch (choice)
        {
            case 0:
                out.println("Goodbye!");
                break;
            case 1:
                totalBooks = LibraryDB.getNumBooks();
                isA = " is " + totalBooks + " in the library";
                areA = " are a total of " + totalBooks + " books";
                out.println("There" + (totalBooks > 1 ? areA : isA));
//                controller();
                break;
            case 2:
               newBook = Helpers.collectNewBookData();
               LibraryDB.addBookToLibrary(newBook);
               out.println();
//               controller();
                break;
            case 3:
                Helpers.printArrayList(LibraryDB.getAllBooks());
//                controller();
                break;
            case 4:
//                controller();
                break;
            case 16:
                out.println("Not a valid choice, try again");
                // controller(); // if you just run displayMenu() it causes a crash because there is no input given in displayMenu so it just ends the program. Call the controller again to start over.
                break;
            default:
                out.println();
                // controller();
                break;
        }
    }


    private static boolean validateChoice(int choice)
    {
        return choice >= 0 && choice <= 10 || choice == 16;
    }


    private static int menu()
    {
        out.println("1. Display Book Count");
        out.println("2. Add New Book To Library");
        out.println("3. Display All Books");
        out.println("4. Display Shelf Count");
        out.println("5. Add Shelf To Library");
        out.println(("6. Display All Shelves"));
        out.println("0. Exit");

        out.print("Please enter your choice: ");
        return scanner.nextInt();
    }

}
