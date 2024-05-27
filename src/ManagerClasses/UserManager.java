package ManagerClasses;

import Data.UserTableManager;
import LibraryObjects.LibraryMember;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class UserManager
{
    private List<LibraryMember> memberList; // the list of users in the database
    private final Scanner scanner = new Scanner(System.in); // user input scanner
    private final UserTableManager userTableManager; // Database table manager for users.
    private final LibraryMember MEMBER = new LibraryMember(); // Generic member used to pass data.

    /**
     * Default constructor - when the userTableManager is init this will query the DB, and then we will copy the
     * results in the array list.
     * @throws SQLException
     */
    public UserManager() throws SQLException
    {
        userTableManager = new UserTableManager();

        memberList = new ArrayList<>(userTableManager.getMemberList());
    }


    /**
     * Used to manage the user management operations based on the users menu choice.
     */
    public void start()
    {
        int choice;
        do
        {
            choice = menu();
            switch(choice)
            {
                case 0:
                    break;
                case 1:
                    displayMembers();
                    break;
                case 2:
                    addMember();
                    break;
                default:
                    out.println("No valid menu choice, lets try again.");

            }

        } while(choice != 0);
    }


    /**
     * The display menu for the program returns the users selection
     * @return the users menu choice
     */
    private int menu()
    {
        out.println("1. Display All Members");
        out.println("2. Add Member");
        out.println("3. Delete Member");
        out.println("4. Update Member");
        out.println("5. Books Loaned to Member");

        out.println("Enter your choice: ");

        return scanner.nextInt();

    }


    /**
     * Add a member to the UserList and also to the database
     * Before an attempt to store the data in the database we will check the current list to see if the user
     * is already in the system.
     * @return true if we added a new user - false if the user is already in the system.
     */
    public boolean addMember()
    {
        MEMBER.collectInfo();

        if(!memberList.contains(MEMBER))
        {
            memberList.add(MEMBER);
            return userTableManager.addMember(MEMBER);
        }

        out.println("Member already exists with card number " + MEMBER.getCardNumber());
        return false;
    }

    /**
     * Find a given member by their library card number
     * @param cardNo the card number for the user
     * @return the member matching the search query.
     */
    public LibraryMember findMemberByCard(int cardNo)
    {

        for (LibraryMember member : memberList)

            if (member.getCardNumber() == cardNo)
                return member;


        return null;
    }

    /**
     * Display all information about the members stored in the list.
     */
    public void displayMembers()
    {
        for(LibraryMember member : memberList)
            member.display();

    }


}
