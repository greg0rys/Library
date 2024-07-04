import Presentation.LibraryHome;
import Utils.*;

import java.sql.SQLException;

import static java.lang.System.exit;
import static java.lang.System.out;

public class Main
{


    public static void main(String[] args) throws SQLException
    {
        javax.swing.SwingUtilities.invokeLater(()->{
            new LibraryHome();
        });
    }

}
