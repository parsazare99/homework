package ir.maktab56.hw8.service;

import ir.maktab56.hw8.domain.Cart;
import ir.maktab56.hw8.domain.Category;
import ir.maktab56.hw8.domain.Product;
import ir.maktab56.hw8.domain.User;
import ir.maktab56.hw8.repository.CartRepository;
import ir.maktab56.hw8.repository.CategoryRepository;
import ir.maktab56.hw8.repository.ProductRepository;
import ir.maktab56.hw8.repository.UserRepository;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShopService {
    UserRepository userRepository = new UserRepository();
    CategoryRepository categoryRepository = new CategoryRepository();
    ProductRepository productRepository = new ProductRepository();
    CartRepository cartRepository = new CartRepository();

    public ShopService() throws SQLException, ClassNotFoundException {
    }


    public void buy(User user) throws SQLException {
        ArrayList<Category> categories = categoryRepository.getAllCategories();
        for (Category c : categories) {

            System.out.println(c.toString());
        }
        int categoryId = Integer.parseInt(JOptionPane.showInputDialog("Enter the category ID you want :"));

        ArrayList<Product> products = productRepository.getProductsByCategoryId(categoryId);
        for (Product p : products) {

            System.out.println(p.toString());
        }
        int productId = Integer.parseInt(JOptionPane.showInputDialog("Enter the product ID you want :"));

        int inventory = productRepository.getInventory(productId);
        if (inventory > 0) {

            int number = Integer.parseInt(JOptionPane.showInputDialog("Enter the number you want to buy :"));

            if ((inventory - number) >= 0) {
                int newInventory = inventory - number;
                productRepository.updateInventory(productId, newInventory);
                //*****
                cartRepository.insert(user.getId(), categoryId, number, false);

            } else {

                JOptionPane.showMessageDialog(null, "The number of requests you have\n" +
                        " is more than our inventory", "inventory", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "This product is not available ",
                    "inventory", JOptionPane.ERROR_MESSAGE);

        }

    }


    public void printAllProducts(int userId) throws SQLException {
        ArrayList<Cart> list = cartRepository.getCart(userId);
        for (Cart c : list) {
            System.out.println(c.toString());

        }

    }


    public void deleteFromCart(int userId) throws SQLException {

        printAllProducts(userId);

        int productId = Integer.parseInt(JOptionPane.showInputDialog("Enter the product ID you want to Delete:"));

        cartRepository.RemoveFromCart(userId, productId);


        JOptionPane.showMessageDialog(null, "Removal from cart was successful",
                "Delete", JOptionPane.ERROR_MESSAGE);
    }

    public int getTotalPrice(int userId) throws SQLException {

        ArrayList<Cart> list = cartRepository.getCart(userId);


        int sum = 0;
        for (Cart c : list) {

            int productId = c.getProductId();
            int price = productRepository.getPrice(productId);
            sum += (c.getNumber() * price);

        }
        return sum;
    }


    public void cartConfirmation(User user) throws SQLException {

        int totalPrice = getTotalPrice(user.getId());
        if (user.getBalance() >= totalPrice) {
            int newBalance = (user.getBalance() - totalPrice);
            user.setBalance(newBalance);
            userRepository.updateUser(user);
            cartRepository.setIsPaid(user.getId());

        } else {


            JOptionPane.showMessageDialog(null, "your account balance is not enough !  \n" +
                            "please increase your account balance  ",
                    "Balance", JOptionPane.ERROR_MESSAGE);

        }


    }

}

