package ir.maktab56.hw8.repository;

import ir.maktab56.hw8.domain.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryRepository extends Repository {


    public CategoryRepository() throws SQLException, ClassNotFoundException {

        super();

    }

    public void insertCategory(String title) throws SQLException {

        PreparedStatement pre = connection.prepareStatement("insert into" +
                " categories (title) values (?)");

        pre.setString(1, title);

        pre.executeUpdate();

    }

    public ArrayList<Category> getAllCategories() throws SQLException {

        ArrayList<Category> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from categories");

        while (result.next()) {
            Category category = new Category();

            category.setId(result.getInt(1));
            category.setTitle(result.getString(2));
            list.add(category);
        }

        return list;
    }

    public Category getCategories(String categoryTitle) throws SQLException {

        Category category = new Category();

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery("select * from categories where title='" + categoryTitle + "'");

        while (result.next()) {

            category.setId(result.getInt(1));
            category.setTitle(result.getString(2));

        }

        return category;
    }


}
