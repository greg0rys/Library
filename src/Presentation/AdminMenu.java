/**
 * @version 07.4.24
 * @author Greg Shenefelt
 * The UI display class for the AdminMenu
 *
 * CHANGE LOG:
 *  07.4.24 -  implemented class and methods attached to commit @ 10:01AM PST
 */

package Presentation;

import Logic.Employee;

import javax.swing.*;

public class AdminMenu extends JFrame
{

    private JPanel rootPanel;
    private JButton homeButton;
    private JButton logoutButton;
    private JRadioButton bookManagementRadioButton;
    private JRadioButton userManagementRadioButton;
    private JRadioButton employeeManagementRadioButton;
    private JTextField loggedInTextField;
    private JTextField lastActionTextField;

    public AdminMenu()
    {
        super("Admin Menu");
        add(rootPanel);
        setVisible(true);
        setRadioButtonEvents();
        buttonEvents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make sure we add this to all frames before prod push
        pack();

    }

    public AdminMenu(Employee employee)
    {
        super("Admin Menu");
        add(rootPanel);
        setRadioButtonEvents();
        buttonEvents();
        loggedInTextField.setText(employee.getName());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make sure we add this to all frames before prod push
        pack();

    }


    private void setRadioButtonEvents()
    {
        assert bookManagementRadioButton != null;

        bookManagementRadioButton.addActionListener(e ->{
            if(bookManagementRadioButton.isSelected())
            {
                new BookMenu();
                dispose();
            }
        });

        assert userManagementRadioButton != null;

        userManagementRadioButton.addActionListener(e ->{
            if(userManagementRadioButton.isSelected())
            {
                new UserMenu();
                dispose();
            }
        });

        assert employeeManagementRadioButton != null;

        employeeManagementRadioButton.addActionListener(e ->{
            if(employeeManagementRadioButton.isSelected()) {
                new EmployeeMenu();
                dispose();
            }

        });
    }

    private void buttonEvents()
    {
        homeButton.addActionListener(e -> {
            new LibraryHome();
            dispose();
        });

        logoutButton.addActionListener(e -> {
            new LibraryHome(); // make constructor that take a boolean "logged in" so the home and modify the button
            // text
            dispose();
        });
    }
}
