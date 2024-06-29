package Views;

import javax.swing.*;

public interface FrameMaker
{
    /**
     * To be called as the last line in all JFrames init
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



}
