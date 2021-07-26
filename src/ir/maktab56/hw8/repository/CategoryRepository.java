package ir.maktab56.hw8.repository;

import ir.maktab56.hw8.domain.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryRepository extends Repository {


    public CategoryRepository() throws SQLException, ClassNotFoundException {

        super();

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


}
