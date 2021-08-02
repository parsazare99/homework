package ir.maktab56.hw8.repository;

import ir.maktab56.hw8.util.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Repository {

    protected Connection connection;

    Database database = new Database();
    public Repository() throws SQLException, ClassNotFoundException {


        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hw9", "root", "Parsa99099@");

        database.createUsersTable(connection);
        database.createCategoriesTable(connection);
        database.createProductsTable(connection);
        database.createCartTable(connection);

    }
}
