package ni9logic.banking;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Login {
    public static void loginMenu (JFrame frame, JPanel panel, Font jetBrains, Font jetBrainsMed){

        // Creating register menu

        // Username Label
        JLabel usernameLabel = new JLabel("Username ");
        usernameLabel.setFont(jetBrains);
        usernameLabel.setBounds(350, 100, 100, 30);

        // Username TextField
        JTextField usernameText = new JTextField();
        usernameText.setFont(jetBrainsMed);
        usernameText.setBounds(450, 105, 200, 25);

        // Password Label
        JLabel passwordLabel = new JLabel("Password ");
        passwordLabel.setFont(jetBrains);
        passwordLabel.setBounds(350, 150, 100, 30);

        // Password Text Field
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setFont(jetBrainsMed);
        passwordText.setBounds(450, 150, 200, 25);

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(jetBrains);
        loginBtn.setBounds(450, 200, 200, 25);
        loginBtn.setEnabled(false);


        // Document Listener
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkFields();
            }

            private void checkFields(){
                String loginText = usernameText.getText().trim();
                String passText = new String(passwordText.getPassword()).trim();
                loginBtn.setEnabled(!loginText.isEmpty() && !passText.isEmpty());
            }
        };

        // Updating listeners
        usernameText.getDocument().addDocumentListener(documentListener);
        passwordText.getDocument().addDocumentListener(documentListener);

        loginBtn.addActionListener(e -> {
            // Getting username and password if Login btn is pressed
            String username = usernameText.getText();
            char[] passArray = passwordText.getPassword();
            String password = new String(passArray);

            if (username.equals("a") && password.equals("a")) {
                usernameText.setText("");
                passwordText.setText("");
                JOptionPane.showMessageDialog(frame, "Login Successful");
                UserMenu.userMenu(frame, jetBrains, jetBrainsMed);
            }
            else
                JOptionPane.showMessageDialog(frame, "Invalid Credentials");
        });

        panel.add(usernameLabel);
        panel.add(usernameText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(loginBtn);
    }
}
