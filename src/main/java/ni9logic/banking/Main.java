package ni9logic.banking;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        // Creating my jetBrains
        Font jetBrains = new Font("JetBrains Mono", Font.BOLD, 15);
        Font jetBrainsMed = new Font("JetBrains Mono", Font.BOLD, 12);

        // Creating GUI
        JFrame frame = new JFrame("Banking Management System");

        // We need to set layout to null for manual positioning
        frame.setLayout(null);

        // Setting to close on pressing exit button
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating the label with message
        JLabel label = new JLabel("Welcome to banking management system!");
        label.setBounds(200, 5, 600, 50); // Set x, y, width, height

        // Setting border of the label
        label.setBorder(new LineBorder(Color.black, 6, true));

        // Setting Alignment
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(jetBrains);

        // Creating register menu

        // Username Label
        JLabel usernameLabel = new JLabel("Username ");
        usernameLabel.setFont(jetBrains);
        usernameLabel.setBounds(200,100,100,30);

        // Username TextField
        JTextField usernameText = new JTextField();
        usernameText.setFont(jetBrainsMed);
        usernameText.setBounds(300, 105, 200, 25);

        // Password Label
        JLabel passwordLabel = new JLabel("Password ");
        passwordLabel.setFont(jetBrains);
        passwordLabel.setBounds(200, 150, 100, 30);

        // Password Text Field
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setFont(jetBrainsMed);
        passwordText.setBounds(300, 150, 200, 25);

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(jetBrains);
        loginBtn.setBounds(300, 200, 200, 25);

        // Getting username and password if Login btn is pressed
        loginBtn.addActionListener(e -> {
            String username = usernameText.getText();
            char [] passArray = passwordText.getPassword();
            String password = new String(passArray);

            String message = "<html><font color='blue'>Username:</font> " + username + "<br>" +
                    "<font color='blue'>Password:</font> " + password + "</html>";


            if (username.equals("Ni9Logic") && password.equals("HASsan@4r"))
                JOptionPane.showMessageDialog(frame, message);
            else
                JOptionPane.showMessageDialog(frame, "Invalid Credentials");
        });

        // Creating a panel so we can use multiple instances
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(usernameLabel);
        panel.add(usernameText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(loginBtn);
        panel.setLayout(null);
        panel.setBounds(0, 0, 1024, 800);


        // Setting frame size
        frame.add(panel);
        frame.setSize(1024, 800);
        frame.setVisible(true);
    }
}