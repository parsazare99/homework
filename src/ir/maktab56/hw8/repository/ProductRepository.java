package ir.maktab56.hw8.repository;

import ir.maktab56.hw8.domain.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductRepository extends Repository {


    public ProductRepository() throws SQLException, ClassNotFoundException {
        super();

    }

    public void insertProduct(Product product) throws SQLException {

        PreparedStatement pre = connection.prepareStatement("INSERT INTO products " +
                "(`name`, `price`, `inventory`, `categoryId`) VALUES (?, ?, ?, ?)");

        pre.setString(1, product.getName());
        pre.setInt(2, product.getPrice());
        pre.setInt(3, product.getInventory());
        pre.setInt(4, product.getCategoryId());
        pre.executeUpdate();


    }

    public Product getproduct(String name) throws SQLException {
        Product product = new Product();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from products where name =" + name);

        while (result.next()) {

            product.setId(result.getInt(1));
            product.setName(result.getString(2));
            product.setPrice(result.getInt(3));
            product.setInventory(result.getInt(4));
            product.setCategoryId(result.getInt(5));
        }

        return product;
    }


    public ArrayList<Product> getAllproducts() throws SQLException {

        ArrayList<Product> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from products");

        while (result.next()) {
            Product product = new Product();

            product.setId(result.getInt(1));
            product.setName(result.getString(2));
            product.setPrice(result.getInt(3));
            product.setInventory(result.getInt(4));
            product.setCategoryId(result.getInt(5));
            list.add(product);
        }

        return list;
    }


    public void updateProduct(Product product) throws SQLException {


        PreparedStatement pre = connection.prepareStatement
                ("UPDATE products SET " +
                        "`name` =?, `price` = ? ,`inventory`=? , `categoryId` = ? WHERE productId =" + product.getId());

        pre.setString(1, product.getName());
        pre.setInt(2, product.getPrice());
        pre.setInt(3, product.getInventory());
        pre.setInt(4, product.getCategoryId());


        pre.executeUpdate();

    }

    public void updateInventory(int id, int inventory) throws SQLException {


        PreparedStatement pre = connection.prepareStatement
                ("UPDATE products SET " +
                        "`inventory`=? WHERE productId =" + id);


        pre.setInt(1, inventory);


        pre.executeUpdate();

    }

    public int getInventory(int productId) throws SQLException {


        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select inventory from products where productId =" + productId);
        int inventory = 0;
        while (result.next()) {
            inventory = result.getInt(1);

        }

        return inventory;


    }


    public ArrayList<Product> getProductsByCategoryId(int categoryId) throws SQLException {


        ArrayList<Product> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from products where categoryId =" + categoryId);

        while (result.next()) {
            Product product = new Product();
            product.setId(result.getInt(1));
            product.setName(result.getString(2));
            product.setPrice(result.getInt(3));
            product.setInventory(result.getInt(4));
            product.setCategoryId(result.getInt(5));
            list.add(product);
        }

        return list;


    }

    public int getPrice(int productId) throws SQLException {


        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select price from products where productId =" + productId);
        int price = 0;
        while (result.next()) {
            price = result.getInt(1);

        }

        return price;


    }


}
