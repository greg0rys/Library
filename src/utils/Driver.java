/**
 * @author Greg Shenefelt
 * @version 5/30/24
 *
 * Main program driver
 */
package utils;
import LibraryObjects.*;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;
import ManagerClasses.*;

public class Driver
{
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Library LIBRARY_SYSTEM;

    public Driver()
    {

        try
        {
            LIBRARY_SYSTEM = new Library();
        }
        catch (SQLException e)
        {
            out.println(e.getErrorCode());
        }
    }

    public void start() throws SQLException
    {

        do
        {
            controller(menu());
        }
        while(controller(menu()));
    }





    private  boolean controller(int selection) throws SQLException
    {

        switch (selection)
        {

            case 0:
                return false;
            case 1:
                LIBRARY_SYSTEM.printTotalBooks();
                break;
            case 2:
//                LIBRARY_SYSTEM.addBook();
                out.println("Not yet implemented");
                break;
            case 3:
                LIBRARY_SYSTEM.listAllBooks();
                break;
            case 4:
                new UserManager().start();
                break;
            case 16:
                out.println("Not a valid choice, try again");
                break;
            default:
                out.println();
                break;
        }

        return true;
    }



    private static int menu()
    {
        out.println("1. Display LibraryObjects.Book Count");
        out.println("2. Add New LibraryObjects.Book To LibraryObjects.Library");
        out.println("3. Display All Books");
        out.println("4. Display Shelf Count");
        out.println("5. Add Shelf To LibraryObjects.Library");
        out.println(("6. Display All Shelves"));
        out.println("0. Exit");

        out.print("Please enter your choice: ");
        return SCANNER.nextInt();
    }



}
