import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;
import org.sqlite.*;

public class Driver
{
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Library LIBRARY_SYSTEM = new Library();
    private final static String DB_URL ="jdbc:sqlite:newDB.db";
    Driver()
    { }

    public static void start() throws SQLException
    {
        if(!checkDB())
            out.println("Unable to query DB");
        // always do this at least once regardless of condition
        do
        {
            controller();
            out.println();

        } while(menu() != 0);
    }

    private static boolean checkDB() throws SQLException
    {
        try(Connection con = DriverManager.getConnection(DB_URL))
        {
            if(con != null)
                return true;
        }
        catch(SQLException e)
        {
            out.println(e.getErrorCode());
        }

        return false;
    }


    private static void controller()
    {
        Book newBook = null;
        int choice = menu();



        choice = validateChoice(choice) ? choice : 16; // if the choice isn't valid return 16 to show switch error else choice or don't because an invalid choice invokes default



        switch (choice)
        {

            case 0:
                break;
            case 1:
                LIBRARY_SYSTEM.printTotalBooks();
                break;
            case 2:
                LIBRARY_SYSTEM.addBook();
                break;
            case 3:
                LIBRARY_SYSTEM.listAllBooks();
                break;
            case 4:
                out.println("COMING SOON XXX");
                break;
            case 16:
                out.println("Not a valid choice, try again");
                break;
            default:
                out.println();
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
        return SCANNER.nextInt();
    }

}
