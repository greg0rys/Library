package utils;
import LibraryObjects.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

import UserObjects.LibraryMember;
import UserObjects.UserManager;
import org.sqlite.*;

public class Driver
{
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Library LIBRARY_SYSTEM = new Library();
    private final static String DB_URL ="jdbc:sqlite:newDB.db";
    private final static ArrayList<LibraryMember> LIBRARY_MEMBERS = new ArrayList<LibraryMember>();

    Driver()
    { }

    public static void start() throws SQLException
    {
        if(pingDB())
            out.println("Unable to query DB");
        // always do this at least once regardless of condition


        while(controller(menu())) {
            controller(menu());
        }
    }

    public static void resume() throws SQLException
    {
        if(pingDB())
            out.println("Unable to query DB");


        while(controller(menu()))
            controller(menu());

    }

    private static boolean pingDB()
    {

    }


    private static boolean controller(int selection) throws SQLException
    {

        switch (selection)
        {

            case 0:
                return false;
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
                UserManager.start(LIBRARY_MEMBERS);
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

    public static void userManagementMenu()
    {
        out.println("1. Display Library Member Count");
        out.println("2. Add New Library Member");
        out.println("3. Display All Library Members");
        out.println("4. Display Total Books on Loan");
        out.println("5. Check Out Book");
        out.println("6. Return Book");
        out.println("0. Exit");

        out.print("Please enter your choice: ");
//        userController(SCANNER.nextInt());
    }

//    public static void userController(int selection)
//    {
//        switch (selection)
//        {
//            case 1:
//                out.println("There are: " + LIBRARY_MEMBERS.size() + " Members");
//                break;
//            case 2:
//                new UserManager();
//                LIBRARY_SYSTEM.addBook();
//                break;
//            case 3:
//                LIBRARY_SYSTEM.listAllBooks();
//                break;
//            case 4:
//                LIBRARY_SYSTEM.printShelfMap();
//                break;
//            case 5:
//                LIBRARY_SYSTEM.addBookShelf();
//                break;
//            case 6:
//                LIBRARY_SYSTEM.printShelfMap();
//                break;
//            case 0:
//                return false;
//            default:
//                out.println("Not a valid choice, try again");
//                break;
//        }
//
//        return true;
//    }



    public ArrayList<LibraryMember> getLibraryMembers()
    {
        return LIBRARY_MEMBERS;
    }

}
