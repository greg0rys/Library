package UserObjects;
import Data.LibraryDB;
import utils.Driver;

import java.sql.SQLException;
import java.util.ArrayList;
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

        return 0;
    }

    private static boolean createNewUser(LibraryMember member)
    {

        return LibraryDB.addNewMember(member);
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
