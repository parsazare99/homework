package ir.maktab56.hw8.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Repository {

    Connection connection;

    public Repository() throws SQLException, ClassNotFoundException {


        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hw8", "root", "Parsa99099@");


    }
}
