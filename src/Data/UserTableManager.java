package Data;

import LibraryObjects.CheckedBook;
import LibraryObjects.LibraryMember;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class UserTableManager
{
    private final String DB_URL ="jdbc:sqlite:newDB.db";
    private final static String GET_ALL_USERS = "SELECT * FROM LibraryUser";
    private final static String ADD_NEW_USER = "INSERT INTO LibraryUser VALUES(?,?,?,?)";
    private final static String DELETE_USER = "DELETE FROM LibraryUser WHERE ID = ?";
    private final List<LibraryMember> MEMBERS = new ArrayList<>();
    private static PreparedStatement preparedStatement;

    public UserTableManager() throws SQLException
    {
       queryAllMembers();

    }


    /**
     * Query in memory DB collect all members. Stored linear.
     * @return ArrayList of all members.
     */
    private boolean queryAllMembers()
    {
        try(Connection conn = DriverManager.getConnection(DB_URL))
        {
            PreparedStatement ps = conn.prepareStatement(GET_ALL_USERS);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                MEMBERS.add(
                        new LibraryMember(rs.getString("FirstName"),
                                              rs.getString("LastName"),
                                              rs.getInt("UserCardNumber"))
                );
            }

            return true;
        }
        catch(SQLException e)
        {

            out.println(e.getMessage());
            return false;
        }
    }


    /**
     *
     * @return
     */
    public List<LibraryMember> getMemberList()
    {
        return MEMBERS;
    }

    public boolean addMember(LibraryMember member)
    {
        if(!MEMBERS.contains(member))
        {
            try (Connection conn = DriverManager.getConnection(DB_URL))
            {
                preparedStatement = conn.prepareStatement(ADD_NEW_USER);
                preparedStatement.setString(1, member.getFirstName());
                preparedStatement.setString(2, member.getLastName());
                preparedStatement.setInt(3, member.getCardNumber());
                preparedStatement.setInt(4, member.getTotalBooksOnLoan());
                preparedStatement.executeUpdate();
               return MEMBERS.add(member); // store the users in a local data struct to prevent db interactions
            } catch (RuntimeException | SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
        out.println("DB Update not executed " + member.getFirstName() + " " + member.getCardNumber() +
                            " is already stored");
        return false;
    }

    public boolean removeMember(LibraryMember member)
    {
        return false;
    }


    public boolean loadChecked(List<CheckedBook> booksOnLoan)
    {
        return true;
    }

    public void getCheckedBooks(LibraryMember temp) {}
}
