/**
 * SearchByTitleForm
 *  The GUI form used to collect user input for searching for books by title.
 * @version 06.28.24
 * @author Greg Shenefelt
 *
*/
package Presentation;

import javax.swing.*;

public class SearchByTitleForm extends JFrame
                               implements FrameMaker
{
    private JTextField userTextInput;
    private JButton submitButton;
    private JPanel searchPane;
    private JPanel rootPanel;
    private final String JFRAME_TITLE = "Search By Title";

    /* default constructor */
    public SearchByTitleForm()
    {
        super("Search By Title");
        add(searchPane);
        setEvents(JFRAME_TITLE, this);
        defaultSetup(this);
    }

    private void createUIComponents()
    {
        // TODO: place custom component creation code here
    }

    /* what if this was inside of the interface, and we just passed a flag to determine config */
//    private void setEvents() { /* Used to wire events for upcoming swing objects. */ }


}
