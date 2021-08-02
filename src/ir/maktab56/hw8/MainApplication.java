package ir.maktab56.hw8;

import ir.maktab56.hw8.domain.User;
import ir.maktab56.hw8.service.AdminService;
import ir.maktab56.hw8.service.ShopService;
import ir.maktab56.hw8.service.UserService;

import javax.swing.*;
import java.sql.SQLException;

public class MainApplication {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        UserService userService = new UserService();
        ShopService shop = new ShopService();
        AdminService adminService = new AdminService();

        while (true) {
            String in = "";
            try {
                in = JOptionPane.showInputDialog("1 : To register on the Shop\n" +
                        "2 :To Log In the Shop and buy the products\n" +
                        "3 : Exit ...");
            } catch (Exception e) {
                System.out.println("Wrong input !");
            }

            int n = Integer.parseInt(in);
//--------------------------------------------------------------------------------------------------------------
            if (n == 1) {

                userService.register();

            }

//---------------------------------------------------------------------------------------------------------------

            else if (n == 2) {
                User user = userService.logIn();
                if (user.getUsername().equals("admin")) {

                    while (true) {
                        String adminAnswer = JOptionPane.showInputDialog(
                                "1 : To add product\n" +
                                        "2 : to add category\n" +
                                        "3 : to increase Product Inventory \n" +
                                        "4 :Exit");
                        int add = Integer.parseInt(adminAnswer);
                        if (add == 1) {
                            adminService.addProduct();


                        } else if (add == 2) {

                            adminService.addCategory();
                        } else if (add == 3) {

                            adminService.increaseInventory();
                        } else {
                            break;
                        }


                    }

                } else {
                    while (true) {

                        String userAnswer = JOptionPane.showInputDialog(
                                "1 : To Shop\n" +
                                        "2 : Final shopping cart confirmation\n" +
                                        "3 : Increase account balance \n" +
                                        "4 : Change Password\n" +
                                        "5 : Exit...");
                        int a = Integer.parseInt(userAnswer);


                        if (a == 1) {
                            while (true) {
                                String answer = JOptionPane.showInputDialog(
                                        "1 : Add Product to Cart       \n" +
                                                "2 : Print the list of all products      \n" +
                                                "3 : Delete Product from Cart      \n" +
                                                "4 : Print the total price of shopping cart items         \n" +
                                                "5 : Exit...");
                                int b = Integer.parseInt(answer);


                                if (b == 1) {
                                    shop.buy(user);
                                    System.out.println("*****************************");
                                } else if (b == 2) {
                                    ShopService shop1 = new ShopService();
                                    shop1.printAllProducts(user.getId());
                                    System.out.println("*****************************");
                                } else if (b == 3) {
                                    shop.deleteFromCart(user.getId());
                                } else if (b == 4) {
                                    System.out.println("Total price = " + shop.getTotalPrice(user.getId()) + "\n");
                                    System.out.println("******************************");
                                } else break;


                            }


                        } else if (a == 2) {

                            shop.cartConfirmation(user);


                        } else if (a == 3) {

                            userService.IncreaseAccountBalance(user);


                        } else if (a == 4) {

                            userService.changhPassword(user.getId());


                        } else {
                            break;
                        }

                        String u = JOptionPane.showInputDialog("1 : Return to the previous menu\n " +
                                "2 : Exit ");
                        if (!(u.equals("1"))) {
                            break;
                        }

                    }
                }


            }


//-------------------------------------------------------------------------------------------------------------------------

            else {
                break;
            }

        }


    }


}
