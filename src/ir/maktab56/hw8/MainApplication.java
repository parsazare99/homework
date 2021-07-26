package ir.maktab56.hw8;

import ir.maktab56.hw8.domain.User;
import ir.maktab56.hw8.service.ShopService;
import ir.maktab56.hw8.service.UserService;

import javax.swing.*;
import java.sql.SQLException;

public class MainApplication {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        UserService userService = new UserService();
        ShopService shop = new ShopService();

        while (true) {

            String in = JOptionPane.showInputDialog("1 : To register on the Shop\n" +
                    "2 :To Log In the Shop and buy the products\n" +
                    "3 : Exit ...");

            int n = Integer.parseInt(in);
//--------------------------------------------------------------------------------------------------------------
            if (n == 1) {

                userService.register();

            }

//---------------------------------------------------------------------------------------------------------------

            else if (n == 2) {
                User user = userService.logIn();


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
                            } else if (b == 2) {
                                ShopService shop1 = new ShopService();
                                shop1.printAllProducts(user.getId());
                            } else if (b == 3) {
                                shop.deleteFromCart(user.getId());
                            } else if (b == 4) {
                                System.out.println(shop.getTotalPrice(user.getId()) + "\n");
                            } else break;


                        }


                    } else if (a == 2) {

                        shop.cartConfirmation(user);


                    } else if (a == 3) {

                        userService.IncreaseAccountBalance(user);


                    } else if (a == 4) {

                        // change password


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


//-------------------------------------------------------------------------------------------------------------------------

            else {
                break;
            }

        }


    }


}
