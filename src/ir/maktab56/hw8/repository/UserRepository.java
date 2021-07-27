package ir.maktab56.hw8.repository;

import ir.maktab56.hw8.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository extends Repository {

    public UserRepository() throws SQLException, ClassNotFoundException {

        super();


    }

    public void insertUser(User user) throws SQLException {

        PreparedStatement pre = connection.prepareStatement("insert into" +
                " users (username,password) values (?,?)");

        pre.setString(1, user.getUsername());
        pre.setString(2, user.getPassword());

        pre.executeUpdate();


    }

    public User getUser(String username) throws SQLException {
        User user = new User();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from users where username = '" + username + "'");

        while (result.next()) {

            user.setId(result.getInt(1));

            user.setUsername(result.getString(2));
            user.setPassword(result.getString(3));
            user.setBalance(result.getInt(4));

        }

        return user;
    }


    public void updateUser(User user) throws SQLException {


        PreparedStatement pre = connection.prepareStatement
                ("UPDATE users SET " +
                        "`username` =?, `password` = ? ,`balance`=? WHERE userId =" + user.getId());

        pre.setString(1, user.getUsername());
        pre.setString(2, user.getPassword());
        pre.setInt(3, user.getBalance());


        pre.executeUpdate();

    }


    public boolean userIsExist(String username) throws SQLException {
        Statement stb = connection.createStatement();
        ResultSet re = stb.executeQuery("select username from users where username= '" + username + "'");

        String ans = "";
        while (re.next()) {
            ans = re.getString(1);

        }
        if (ans.equals(username))
            return true;
        else
            return false;
    }

    public boolean passwordIsExist(String username, String password) throws SQLException {
        Statement stb = connection.createStatement();
        ResultSet re = stb.executeQuery("select password from users where username= '" + username + "'");

        String ans = "";
        while (re.next()) {
            ans = re.getString(1);

        }
        if (ans.equals(password))
            return true;
        else
            return false;

    }

    public void updatePassword(int userId, String newPassword) throws SQLException {

        PreparedStatement pre = connection.prepareStatement(
                "update users set password =? where userId = " + userId + "");
        pre.setString(1, newPassword);
        pre.executeUpdate();
    }


}
