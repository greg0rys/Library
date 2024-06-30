package Data;

import LibraryObjects.CheckedBook;
import LibraryObjects.LibraryMember;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class UserTableManager {
    private final String DB_URL ="jdbc:sqlite:newDB.db";
    private final static String GET_ALL_USERS = "SELECT * FROM LibraryUser";
    private final static String ADD_NEW_USER = "INSERT INTO LibraryUser VALUES(?,?,?,?)";
    private final static String ADD_NEW_USER_WITHOUT_LOANS = "INSERT INTO LibraryUser(FirstName,LastName,UserCardNumber) VALUES(?,?,?)";
    private final static String DELETE_USER = "DELETE FROM LibraryUser WHERE ID = ?";
    private final List<LibraryMember> MEMBERS = new ArrayList<>();

    public UserTableManager() throws SQLException {}

    public List<LibraryMember> queryAllMembers() {
        try(Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement(GET_ALL_USERS);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                MEMBERS.add(
                        new LibraryMember(rs.getString("FirstName"),
                                          rs.getString("LastName"),
                                          rs.getInt("UserCardNumber"),
                                          rs.getInt("NumBooksOnLoan")));
            }

            return MEMBERS;
        } catch(SQLException e) {

            out.println(e.getMessage());
            return null;
        }
    }

    public List<LibraryMember> getMemberList() {
        return MEMBERS;
    }

    private boolean addMember(LibraryMember member) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement preparedStatement = conn.prepareStatement(ADD_NEW_USER_WITHOUT_LOANS);
            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getLastName());
            preparedStatement.setInt(3, member.getCardNumber());
            return preparedStatement.executeUpdate() > 0;
        } catch (RuntimeException | SQLException e) {
            out.println("User already exists..");
            return false;
        }
    }

    public boolean addToLocalList(LibraryMember member) { return true;}
}