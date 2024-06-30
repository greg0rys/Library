package Presentation;

import javax.swing.*;

public interface FrameMaker
{
    /**
     * To be called as the last line in all JFrames init.
     * This interface will provide default methods for the JFrame classes; The typical Java multi inheritance hack.
     * @param frame the frame that we want to manage.
     */
    default void defaultSetup(JFrame frame)
    {
        if(frame == null)
            return;

        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();

    }

    default void setEvents(String frameTitle, JFrame frame)
    {
        if(frame == null)
            return;

        if(frameTitle.equalsIgnoreCase("Search By Title"))
            setTitleSearchEvents(frame);
    }

    private void setTitleSearchEvents(JFrame frame)
    {
        /* Set events specific to Title Search Views*/

    }

    private void setSearchOptionsEvents(JFrame frame)
    {

    }



}
