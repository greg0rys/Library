import Utils.*;

import java.sql.SQLException;

import static java.lang.System.exit;
import static java.lang.System.out;

public class Main
{


    public static void main(String[] args) throws SQLException
    {

        new Driver().start();

        out.println("Thanks for visiting the Library - Come back soon.");
        exit(0);
    }

}
