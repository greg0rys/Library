package ManagerClasses;

import LibraryObjects.LibraryMember;

import java.util.List;
import java.util.Scanner;

public class UserManager
{
    private List<LibraryMember> memberList;
    private final Scanner scanner = new Scanner(System.in);

    public boolean start(List<LibraryMember> members, boolean flag)
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


            }

        } while(choice != 0);
    }


    private int menu()
    {
        return 0;
    }
}
