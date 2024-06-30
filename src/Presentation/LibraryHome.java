/**
 * TODO CREATE LOGIN FORM SOMEONE MUST BE LOGGED IN TO ACCESS THE SYSTEM AT ALL
 */

package Presentation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import Logic.Employee;

public class LibraryHome extends JFrame implements FrameMaker
{
    private JPanel rootPanel;
    private JButton homeButton;
    private JButton adminMenuButton;
    private JButton userMenuButton;
    private JButton bookMenuButton;
    private JToolBar menuBar;
    private JButton loginButton;
    private JList libraryStatus;
    private ArrayList<JButton> buttonContainer;
    private final String FRAME_TITLE = "Library Management System - Home";

    public LibraryHome()
    {
        super();
        setTitle(FRAME_TITLE);
        defaultSetup(this);
        createButtonContainer(homeButton, adminMenuButton, userMenuButton, bookMenuButton, loginButton);
        homeButton.setVisible(false);
    }

    public LibraryHome(Employee E)
    {
        super();
        setTitle(FRAME_TITLE);
        if(E.isLoggedOn)
            loginButton.setText("Logout");

    }

    private void setEvents()
    {
    }
    /**
     * Add all JButtons to an ArrayList to manage all buttons.
     * @param buttons
     */
    private void createButtonContainer(JButton ...buttons)
    {
        if(buttonContainer.isEmpty())
            buttonContainer = new ArrayList<>();

        if(buttons.length <= 0)
            return;

        buttonContainer.addAll(List.of(buttons));

    }
}
