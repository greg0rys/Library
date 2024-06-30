/**
 * LibrarySearchChoice
 *  GUI designed for user selection of search types.
 * @version 06.28.24
 * @author Greg Shenefelt
 * This form will only present the user with the valid options as Radio Buttons helping to avoid user input errors such
 * as invlaid search choice.
 *
 */

package Presentation;

import javax.swing.*;

import static java.lang.System.out;


public class SearchOptionsForm extends JFrame implements FrameMaker
{
    private JPanel rootFrame;
    private JRadioButton titleRadioButton;
    private JRadioButton authorRadioButton;
    private JRadioButton genreRadioButton;
    private JPanel optionContainer;
    private JButton submitButton;
    private JPanel UIBody;
    private JButton homeButton;
    private JButton userMenuButton;
    private JButton bookMenuButton;


    public SearchOptionsForm()
    {
        super("Search Option Form");
        add(rootFrame);
        setEvents();
        defaultSetup(this); /* pass self as we are inheriting the JFrame type */
    }



    private void setEvents()
    {
        submitButton.addActionListener(e -> {
            // this will need type checking.
            switch(getOption(titleRadioButton, authorRadioButton, genreRadioButton))
            {
                case 1:
                    // launch the search by title form
                    new SearchByTitleForm();
                    this.dispose();
                    break;
                case 2:
                    out.println("SS"); // launch search by author form
                    break;
                default:
                    out.println("This was not a valid choice");
                    break;
            }
        });
    }


    /**
     * Return a numerical option based on which Radio Button is selected.
     * @param buttons var args array of all the radio button objects.
     * @return 1 for title, 2 for author, 3 for genre.
     */
    private int getOption(JRadioButton ...buttons)
    {

        for(int i = 0; i < buttons.length; i++)
            if (buttons[i].isSelected())
                return i + 1;


        return 0; // if we reach this return statement return 0 for err
    }



}
