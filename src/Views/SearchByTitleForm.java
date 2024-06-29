/**
 * SearchByTitleForm
 *  The GUI form used to collect user input for searching for books by title.
 * @version 06.28.24
 * @author Greg Shenefelt
 *
*/
package Views;

import javax.swing.*;

public class SearchByTitleForm extends JFrame
                               implements FrameMaker
{
    private JTextField userTextInput;
    private JButton submitButton;
    private JPanel rootPanel;

    public SearchByTitleForm()
    {
        super("Search for books by title");
        add(rootPanel);
        defaultSetup(this);
    }

    private void setEvents() { /* Used to wire events for upcoming swing objects. */ }


}
