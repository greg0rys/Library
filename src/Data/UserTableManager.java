package Data;

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
    private final static String DELETE_USER = "DELETE FROM LibraryUser WHERE UserCardNumber = ?";
    private final static List<LibraryMember> MEMBERS = new ArrayList<>();

    public UserTableManager() throws SQLException
    {
       super();
       getAllMembers(MEMBERS);

    }


    private void getAllMembers(List<LibraryMember> members)
    {

        try(Connection conn = getConnection())
        {
            PreparedStatement ps = conn.prepareStatement(GET_ALL_USERS);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                members.add(new LibraryMember(
                        rs.getString("FirstName"), rs.getString("LastName"), rs.getInt("UserCardNumber"),
                        rs.getInt("NumBooksOnLoan")
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
                PreparedStatement ps = conn.prepareStatement(ADD_NEW_USER);
                ps.setString(1, member.getFirstName());
                ps.setString(2, member.getLastName());
                ps.setInt(3, member.getCardNumber());
                ps.setInt(4, member.getTotalBooksOnLoan());
                ps.executeUpdate();
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




}
