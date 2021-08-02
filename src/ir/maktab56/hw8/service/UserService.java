package ir.maktab56.hw8.service;

import ir.maktab56.hw8.domain.User;
import ir.maktab56.hw8.repository.UserRepository;

import javax.swing.*;
import java.sql.SQLException;

public class UserService {
    UserRepository userRepository = new UserRepository();

    public UserService() throws SQLException, ClassNotFoundException {

    }


    public void register() throws SQLException {
        User user = new User();

        while (true) {

            String username = JOptionPane.showInputDialog("Enter your UserName :");

            if (userRepository.userIsExist(username)) {
                JOptionPane.showMessageDialog(null, "this user is Exists!\n" +
                        "Please choose another username ");


            } else {
                user.setUsername(username);
                break;

            }
        }

        user.setPassword(JOptionPane.showInputDialog("Enter your password :"));

        if (!(user.getUsername().equals("admin"))) {
            user.setBalance(500);
        }
        userRepository.insertUser(user);

        JOptionPane.showMessageDialog(null, "Wellcome to System !");

    }


    public User logIn() throws SQLException {
        String username;
        while (true) {

            username = JOptionPane.showInputDialog("please Enter your username : ");

            if (userRepository.userIsExist(username)) {

                while (true) {
                    String password = JOptionPane.showInputDialog("please Enter your password : ");
                    if (userRepository.passwordIsExist(username, password)) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "password is Wrong!!!!\n" +
                                "Please try agine...", "password", JOptionPane.ERROR_MESSAGE);

                    }
                }

                JOptionPane.showMessageDialog(null, "The log in was successful !");
                break;

            } else
                JOptionPane.showMessageDialog(null, "username is Wrong!!!!\n" +
                        "Please try agine...", "UserName", JOptionPane.ERROR_MESSAGE);

        }

        return userRepository.getUser(username);


    }


    public void IncreaseAccountBalance(User user) throws SQLException {

        int up = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount you want to top up your account : "));
        int newBalance = up + user.getBalance();
        user.setBalance(newBalance);

        userRepository.updateUser(user);
        JOptionPane.showMessageDialog(null, "The operation was successful ");

    }

    public void changhPassword(int userId) throws SQLException {
        String newPassword = JOptionPane.showInputDialog("Enter your new password : ");
        userRepository.updatePassword(userId, newPassword);


        JOptionPane.showMessageDialog(null, "Password change was successful  ");


    }


}
