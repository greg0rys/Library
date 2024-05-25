package UserObjects;
import Data.DbController;
import utils.Driver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class UserManager
{
    private static ArrayList<LibraryMember> members;

    // takes in all the current members and allows us to manage them
    public static void start(ArrayList<LibraryMember> libraryMembers) throws SQLException
    {
        members = libraryMembers;
        controller();
    }

    public static void controller() throws SQLException
    {
        int flag = managerMenu();

        while (flag != 0)
        {
            flag = managerMenu();

            switch(flag)
            {
                case 0:
                    Driver.resume();
                    break;
                case 1:
                    outcome(createNewUser(new LibraryMember().collectInfo()), 1);
                    break;
                case 2:
                    displayAllMembers();
                    break;
                case 3:
                    checkBookOut(getMember());
                    break;
                default:
                    out.println("No a valid menu choice try again");
                    break;
            }
        }


    }

    private static void outcome(boolean newUser, int i)
    {
    }

    private static LibraryMember getMember()
    {
        if(members.isEmpty())
            return null;

     return new LibraryMember();
    }

    private static int managerMenu()
    {

        out.println("1. Add New Member.");
        out.println("2. Display All Members.");
        out.println("3. Check Book Out.");
        out.println("4. Return Book.");
        out.println("5. Update Member Information.");
        out.println("6. Display Books On Loan To User");
        out.println("0. Exit");

        out.println("Enter your choice: ");
        return new Scanner(System.in).nextInt();
    }

    private static boolean createNewUser(LibraryMember member)
    {

        return DbController.addNewMember(member);
    }

    private static void displayAllMembers()
    {
        if(members == null || members.isEmpty())
        {
            out.println("There are no members to display at this time.");
            return;
        }

        for(LibraryMember member : members)
            member.display();
    }

    private static void checkBookOut(LibraryMember member)
    {

    }
}
