package ir.maktab56.hw8.service;

import ir.maktab56.hw8.domain.Category;
import ir.maktab56.hw8.domain.Product;
import ir.maktab56.hw8.repository.CategoryRepository;
import ir.maktab56.hw8.repository.ProductRepository;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminService {


    ProductRepository productRepository = new ProductRepository();
    CategoryRepository categoryRepository = new CategoryRepository();


    public AdminService() throws SQLException, ClassNotFoundException {
    }


    public void addProduct() throws SQLException {
        Product product = new Product();
        product.setName(JOptionPane.showInputDialog("Enter Product Name:"));
        product.setPrice(Integer.parseInt(JOptionPane.showInputDialog("Enter Product Price:")));
        product.setInventory(Integer.parseInt(JOptionPane.showInputDialog("Enter Product Inventory:")));
        ArrayList<Category> categories = categoryRepository.getAllCategories();
        for (Category c : categories) {

            System.out.println(c.toString());

        }

        String category = JOptionPane.showInputDialog("Enter Category ID :\n " +
                "For Add New Category Enter 'new' :");

        if (category.toLowerCase().equals("new")) {

            product.setCategoryId(addCategory());
        } else {
            product.setCategoryId(Integer.parseInt(category));
        }

        productRepository.insertProduct(product);
        JOptionPane.showMessageDialog(null, "Adding product was successful",
                "Product", JOptionPane.INFORMATION_MESSAGE);

    }


    public int addCategory() throws SQLException {

        String newCategory = JOptionPane.showInputDialog("Enter the Title of the new category you want to create :");

        categoryRepository.insertCategory(newCategory);

        Category c = new Category();

        c = categoryRepository.getCategories(newCategory);
        int id = c.getId();
        JOptionPane.showMessageDialog(null, "Adding Category was successful",
                "Category", JOptionPane.INFORMATION_MESSAGE);
        return id;
    }


    public void increaseInventory() throws SQLException {

        ArrayList<Product> products = productRepository.getAllproducts();
        for (Product p : products) {

            System.out.println(p.toString());

        }
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");

        int productId = Integer.parseInt(JOptionPane.showInputDialog("Enter the product ID you want :"));

        Product p = new Product();
        p = productRepository.getproductById(productId);
        int capacity = Integer.parseInt(JOptionPane.showInputDialog("How many do you want to increase capacity :"));
        int newInventory = p.getInventory() + capacity;
        productRepository.updateInventory(p.getId(), newInventory);


    }

}
