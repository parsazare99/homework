package ir.maktab56.hw8.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {


//    public Database() throws SQLException, ClassNotFoundException {
//        super();
//
//    }

    public void createUsersTable(Connection connection) throws SQLException {


        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                "  `userId` INT NOT NULL AUTO_INCREMENT," +
                "  `username` VARCHAR(45) NOT NULL," +
                "  `password` VARCHAR(45) NOT NULL," +
                "  `balance` INT NULL DEFAULT NULL," +
                "  PRIMARY KEY (`userId`))");


        statement.close();

    }

    public void createCategoriesTable(Connection connection) throws SQLException {


        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS categories (" +
                "  `categoryId` INT NOT NULL AUTO_INCREMENT," +
                "  `title` VARCHAR(45) NOT NULL," +
                "  PRIMARY KEY (`categoryId`))");

        statement.close();

    }


    public void createProductsTable(Connection connection) throws SQLException {


        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS products(" +
                "  `productId` INT NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(45) NOT NULL," +
                "  `price` INT NOT NULL," +
                "  `inventory` INT NOT NULL," +
                "  `categoryId` INT NOT NULL," +
                "  PRIMARY KEY (`productId`)," +
                "  INDEX `fk_categoryId_idx` (`categoryId` ASC) VISIBLE," +
                "  CONSTRAINT `fk_categoryId`" +
                "    FOREIGN KEY (`categoryId`)" +
                "    REFERENCES categories (`categoryId`)" +
                "    ON DELETE NO ACTION" +
                "    ON UPDATE NO ACTION)");


        statement.close();

    }

    public void createCartTable(Connection connection) throws SQLException {


        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS cart (" +
                "        `userId` INT NOT NULL," +
                "        `productId` INT NOT NULL," +
                "        `number` INT NOT NULL," +
                "        `isPaid` BIT(1) NOT NULL," +
                "        INDEX `fk_productId_idx` (`productId` ASC) VISIBLE," +
                "        INDEX `fk_userId_idx` (`userId` ASC) VISIBLE," +
                "        CONSTRAINT `fk_productId`" +
                "        FOREIGN KEY (`productId`)" +
                "        REFERENCES `hw9`.`products` (`productId`)" +
                "        ON DELETE NO ACTION" +
                "        ON UPDATE NO ACTION," +
                "        CONSTRAINT `fk_userId`" +
                "        FOREIGN KEY (`userId`)" +
                "        REFERENCES `hw9`.`users` (`userId`)" +
                "        ON DELETE NO ACTION" +
                "        ON UPDATE NO ACTION)");


        statement.close();

    }


}
