/**
 * TODO have hidden button that allows admins to add new staff with all roles
 * TODO non admins cannot add employees only library members
 * 
 * 
 */
package Presentation;

import javax.swing.*;
import java.awt.*;

public class UserMenu extends JFrame implements FrameMaker
{
    private JPanel rootPAnel;
    private JButton homeButton;
    private JButton bookMenuButton;
    private JButton adminMenuButton;
    private JButton logoutButton;
    private JRadioButton addLibraryMemberRadioButton;
    private JRadioButton findLibraryMemberRadioButton;
    private JRadioButton deleteLibraryMemberRadioButton;
    private JButton submitButton;
    private JPanel optionPanel;
    private JToolBar UIMenu;

    public UserMenu()
    {
        
    }


    /**
     * Sets a given set of radio buttons to inactive based on which choice is currently selected.
     * Add return statements to the matching conditionals so we don't keep executing.
     * @since 07.4.23
     */
    private void setRadioButtonEvents()
    {
        if (addLibraryMemberRadioButton.isSelected())
        {
            findLibraryMemberRadioButton.setSelected(false);
            deleteLibraryMemberRadioButton.setSelected(false);
            return;
        }

        if (findLibraryMemberRadioButton.isSelected())
        {
            addLibraryMemberRadioButton.setSelected(false);
            deleteLibraryMemberRadioButton.setSelected(false);
            return;
        }

        if (deleteLibraryMemberRadioButton.isSelected())
        {
            addLibraryMemberRadioButton.setSelected(false);
            findLibraryMemberRadioButton.setSelected(false);
        }



    }

    private void setButtonEvents()
    {
        homeButton.addActionListener(e -> {
            LibraryHome libraryHome = new LibraryHome();
            dispose(); // get rid of this frame
        });

        bookMenuButton.addActionListener(e -> {
            BookMenu bookMenu = new BookMenu();
            dispose();
        });

        adminMenuButton.addActionListener(e -> {
            AdminMenu adminMenu = new AdminMenu();
            dispose();
        });

        logoutButton.addActionListener(e -> {
           // think of this logic later.
        });

    }
    
}
