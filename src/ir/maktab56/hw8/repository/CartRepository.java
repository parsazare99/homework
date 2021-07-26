package ir.maktab56.hw8.repository;

import ir.maktab56.hw8.domain.Cart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CartRepository extends Repository {

    public CartRepository() throws SQLException, ClassNotFoundException {

        super();

    }


    public void insert(int userId, int productId, int number, boolean isPaid) throws SQLException {


        PreparedStatement pre = connection.prepareStatement("INSERT INTO cart (`userId`, `productId`,`number`, `isPaid`) VALUES (?,?, ?,?)");

        pre.setInt(1, userId);
        pre.setInt(2, productId);
        pre.setInt(3, number);
        pre.setBoolean(4, isPaid);
        pre.executeUpdate();


    }


    public void RemoveFromCart(int userId, int productId) throws SQLException {


        PreparedStatement pre = connection.prepareStatement("DELETE FROM cart WHERE userId=? and productId=?");

        pre.setInt(1, userId);
        pre.setInt(2, productId);

        pre.executeUpdate();

    }


    public ArrayList<Cart> getCart(int userId) throws SQLException {

        ArrayList<Cart> listt = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from cart where userId=" + userId + " and isPaid=" + false);


        while (result.next()) {
            Cart cart = new Cart();

            cart.setUserId(result.getInt(1));
            cart.setProductId(result.getInt(2));
            cart.setNumber(result.getInt(3));
            cart.setPaid(result.getBoolean(4));
            Statement s = connection.createStatement();
            ResultSet res = s.executeQuery("select name, price from products where productId=" + cart.getProductId());
            while (res.next()) {
                cart.setProductName(res.getString(1));
                cart.setPrice(res.getInt(2));
            }


            listt.add(cart);
        }

        return listt;


    }


    public void setIsPaid(int userId) throws SQLException {


        PreparedStatement pre = connection.prepareStatement
                ("UPDATE cart SET " +
                        "`isPaid` = ? WHERE userId =" + userId);

        pre.setBoolean(1, true);

        pre.executeUpdate();


    }


    public int getAllNumberUserProducts(int userId) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from cart where userId = " + userId);

        int sum = 0;
        while (result.next()) {

            sum += result.getInt(3);
        }

        return sum;


    }

    public int getNumber(int userId, int productId) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select number from cart where userId =" + userId + " and productId=" + productId);
        int number = 0;
        while (result.next()) {

            number = result.getInt(1);
        }

        return number;


    }


    public boolean productIsExist(int userId, int productId) throws SQLException {
        Statement stb = connection.createStatement();
        ResultSet re = stb.executeQuery("select productId from cart where userId=" + userId);


        while (re.next()) {
            if (re.getInt(1) == productId)
                return true;

        }

        return false;

    }

    public void updateNumber(int userId, int productId, int newNumber) throws SQLException {


        PreparedStatement pre = connection.prepareStatement
                ("UPDATE cart SET number=? WHERE productId = ? and userId=?");

        pre.setInt(1, newNumber);
        pre.setInt(2, productId);
        pre.setInt(3, userId);

        pre.executeUpdate();

    }

}
