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
        int choice;
        do
        {
            choice = menu();
            controller(choice);
        }
        while(choice != 0);

    }

    /**
     * Display menu choice to the user and collect their input
     * @return the users menu choice in the form of an int
     */
    private static int menu()
    {
        out.println("1. Display Book Count");
        out.println("2. Add New Book To Library");
        out.println("3. Display All Books");
        out.println("4. Member Management System");
        out.println("5. Display Total Members");
        out.println("6. Add New Member To Library");
        out.println("0. Exit");

        out.print("Please enter your choice: ");
        return SCANNER.nextInt();
    }


    /**
     * Program controller to call the library systems methods.
     * @param selection the users menu choice
     * @throws SQLException if DB has an error
     */
    private  void controller(int selection) throws SQLException
    {
        UserManager um = new UserManager();

        switch (selection)
        {

            case 0:
                break;
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
                um.start();
                break;
            default:
                out.println("Not a valid menu choice, try again");
                break;
        }

    }






}
