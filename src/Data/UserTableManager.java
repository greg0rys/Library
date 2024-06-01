package Data;

import LibraryObjects.CheckedBook;
import LibraryObjects.LibraryMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class UserTableManager extends DbController
{
    private final static String GET_ALL_USERS = "SELECT * FROM LibraryUser";
    private final static String ADD_NEW_USER = "INSERT INTO LibraryUser VALUES(?,?,?,?)";
    private final static String DELETE_USER = "DELETE FROM LibraryUser WHERE ID = ?";
    private final static List<LibraryMember> MEMBERS = new ArrayList<>();
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public UserTableManager() throws SQLException
    {
       super();
       getAllMembers();

    }


    private void getAllMembers()
    {

        try(Connection conn = getConnection())
        {
            preparedStatement = conn.prepareStatement(GET_ALL_USERS);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                MEMBERS.add(new LibraryMember(
                        resultSet.getString("FirstName"), resultSet.getString("LastName"),
                        resultSet.getInt("CardNumber")
                ));
            }
        }
        catch(RuntimeException | SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


    public List<LibraryMember> getMemberList()
    {
        return MEMBERS;
    }

    public boolean addMember(LibraryMember member)
    {
        if(!MEMBERS.contains(member))
        {
            try (Connection conn = getConnection())
            {
                preparedStatement = conn.prepareStatement(ADD_NEW_USER);
                preparedStatement.setString(1, member.getFirstName());
                preparedStatement.setString(2, member.getLastName());
                preparedStatement.setInt(3, member.getCardNumber());
                preparedStatement.setInt(4, member.getTotalBooksOnLoan());
                preparedStatement.executeUpdate();
               return MEMBERS.add(member);
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
}
