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
        if (cartRepository.getAllNumberUserProducts(user.getId()) < 5) {


            ArrayList<Category> categories = categoryRepository.getAllCategories();
            for (Category c : categories) {

                System.out.println(c.toString());

            }
            System.out.println();
            int categoryId = Integer.parseInt(JOptionPane.showInputDialog("Enter the category ID you want :"));

            ArrayList<Product> products = productRepository.getProductsByCategoryId(categoryId);
            for (Product p : products) {

                System.out.println(p.toString());

            }
            System.out.println();
            int productId = Integer.parseInt(JOptionPane.showInputDialog("Enter the product ID you want :"));

            int inventory = productRepository.getInventory(productId);
            if (inventory > 0) {

                int number = Integer.parseInt(JOptionPane.showInputDialog("Enter the number you want to buy :"));

                if ((inventory - number) >= 0) {

                    if ((cartRepository.getAllNumberUserProducts(user.getId()) + number) <= 5) {
                        int newInventory = inventory - number;
                        productRepository.updateInventory(productId, newInventory);
                        if (cartRepository.productIsExist(user.getId(), productId)) {
                            int newNumber = cartRepository.getNumber(user.getId(), productId) + number;
                            cartRepository.updateNumber(user.getId(), productId, newNumber);
                        } else {
                            cartRepository.insert(user.getId(), productId, number, false);
                        }


                    } else {


                        JOptionPane.showMessageDialog(null, "The number you entered is unacceptable", "inventory", JOptionPane.ERROR_MESSAGE);
                    }


                } else {

                    JOptionPane.showMessageDialog(null, "The number of requests you have\n" +
                            " is more than our inventory", "inventory", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "This product is not available ",
                        "inventory", JOptionPane.ERROR_MESSAGE);

            }
        } else {

            JOptionPane.showMessageDialog(null, "Your cart capacity is full !\n" +
                            "You must delete items to add a new product",
                    "full !", JOptionPane.ERROR_MESSAGE);

        }

    }


    public void printAllProducts(int userId) throws SQLException {
        ArrayList<Cart> list = cartRepository.getCart(userId);
        if (list.isEmpty()) {
            System.out.println("Your Cart Is Empty ");
        } else {
            for (Cart k : list) {
                System.out.println(k.toString());

            }
            System.out.println();
        }


    }


    public void deleteFromCart(int userId) throws SQLException {

        printAllProducts(userId);
        Product product = new Product();

        int productId = Integer.parseInt(JOptionPane.showInputDialog("Enter the product ID you want to Delete:"));
        product = productRepository.getproductById(productId);
        Cart cart = cartRepository.getCart(userId, productId);
        int removeNumber;
        while (true) {

            removeNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter the number you want to Delete:"));
            if (removeNumber <= cart.getNumber()) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Wrong Number !!!\n" +
                                "Please try againe... ",
                        " Error ", JOptionPane.ERROR_MESSAGE);
            }
        }
        int newNumber = cart.getNumber() - removeNumber;
        if (newNumber == 0) {
            cartRepository.RemoveFromCart(userId, productId);
        }

        cart.setNumber(newNumber);
        cartRepository.updateNumber(userId, productId, newNumber);
        int newInventory = product.getInventory() + removeNumber;
        product.setInventory(newInventory);

        productRepository.updateInventory(product.getId(), newInventory);

        JOptionPane.showMessageDialog(null, "Removal from cart was successful",
                "Delete", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "The operation was successful  \n" +
                            " and the money was withdrawn from your account",
                    "withdrawn", JOptionPane.INFORMATION_MESSAGE);

        } else {


            JOptionPane.showMessageDialog(null, "your account balance is not enough !  \n" +
                            "please increase your account balance  ",
                    "Balance", JOptionPane.ERROR_MESSAGE);

        }


    }

}
