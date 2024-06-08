package ManagerClasses;

import Data.UserTableManager;
import LibraryObjects.LibraryMember;

import java.sql.SQLException;
import java.util.*;

import static java.lang.System.out;

public class UserManager
{
    private List<LibraryMember> memberList; // the list of users in the database
    private final Scanner scanner = new Scanner(System.in); // user input scanner
    private final UserTableManager userTableManager; // Database table manager for users.
    private final LibraryMember MEMBER = new LibraryMember(); // Generic member used to pass data.
    // list containing valid menu options
    private final List<Integer> validMenuChoices = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));


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
    public void start() throws SQLException
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
                case 3:
                    deleteMember(findMemberBy());
                    break;
                case 4:
                    updateMember(findMemberBy());
                    break;
                case 5:
                    getUsersCheckedBooks(findMemberBy());
                    break;
                default:
                    out.println("No valid menu choice, lets try again.");

            }

        } while(choice != 0);
    }

    /**
     * Get all checked books for a given user.
     * @param temp
     */
    private void getUsersCheckedBooks(LibraryMember temp)
    {
        userTableManager.getCheckedBooks(temp);
    }

    private void updateMember(LibraryMember temp)
    {
        int choice;
        out.println("What would you like to update about this member? 1 for first name 2 for last name");
        choice = scanner.nextInt();

        if(choice == 1)
        {
            out.println("Please enter the members new first name: ");
            temp.setFirstName(scanner.next());

        }

        if(choice == 2)
        {
            out.println("Please enter the members new last name: ");
            temp.setLastName(scanner.next());
        }

    }

    private void deleteMember(LibraryMember temp)
    {
        if(!memberList.contains(temp))
            return;

        memberList.remove(temp);
    }


    /**
     * The display menu for the program returns the users selection
     * @return the users menu choice
     */
    private int menu()
    {
        int choice;

        out.println("1. Display All Members");
        out.println("2. Add Member");
        out.println("3. Delete Member");
        out.println("4. Update Member");
        out.println("5. Books Loaned to Member");

        out.println("Enter your choice: ");

        choice = scanner.nextInt();
        if(!validMenuChoices.contains(choice))
        {
            out.println("Invalid choice, lets try again.");
            return menu();
        }

        return choice;

    }

    private LibraryMember findMemberBy()
    {
        int searchChoice; // the users choice from the menu.
        out.println("1 to search by name, 2 to search by card number: ");
        searchChoice = scanner.nextInt();

        if (!validMenuChoices.contains(searchChoice))
        {
            out.println("That isn't a valid choice, lets try again.");
            return findMemberBy();
        }



        return (searchChoice == 1 ? findUserByName() : findMemberByID());

    }


    /**
     * Add a member to the UserList and also to the database
     * Before an attempt to store the data in the database we will check the current list to see if the user
     * is already in the system.
     * @return true if we added a new user - false if the user is already in the system.
     */
    public boolean addMember() throws SQLException
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
     * @return the member matching the search query.
     */
    public LibraryMember findMemberByID()
    {
        int userID = displayAllMembersByID();
        for (LibraryMember member : memberList)

            if (member.getCardNumber() == userID)
                return member;


        return null;
    }

    private LibraryMember findUserByName()
    {
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

    /**
     * Display a list of all members and their ID's used to select the member to be found.
     * @return the ID number of a user in the list.
     */
    private int displayAllMembersByID()
    {
        int choice = 0;

        for(LibraryMember member : memberList)
            out.println("ID: " + member.getCardNumber() + " " + member.getFirstName() + " " + member.getLastName());
        out.println("Please Enter An ID Number: ");

        choice = scanner.nextInt();

        if(!getAllMemberIDs().contains(choice))
        {
            out.println("Invalid ID Number - redisplay");
            return displayAllMembersByID();

        }

        return choice;
    }


    /**
     * Get a list of all the member ID's in the current list.
     * This is to help ensure users supply a valid input
     * @return
     */
    private List<Integer> getAllMemberIDs()
    {
        List<Integer> memberIDs = new ArrayList<>();

        for (LibraryMember member : memberList)
        {
            memberIDs.add(member.getCardNumber());
        }
        return memberIDs;
    }


}
