package ni9logic.banking;

import org.bson.Document;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class Register {
    public static void registerMenu(JFrame frame, JPanel panel, Font jetBrains, Font jetBrainsMed) {
        JButton registerBtn = new JButton("Register");
        registerBtn.setFont(jetBrains);
        registerBtn.setBounds(450, 250, 200, 25);

        registerBtn.addActionListener(e -> {
            frame.setVisible(false);

            JFrame registerFrame = new JFrame("Register Menu");
            registerFrame.setVisible(true);
            registerFrame.setLayout(null);
            registerFrame.setSize(Main.WIDTH, Main.HEIGHT);

            JPanel registerPanel = new JPanel();
            registerPanel.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
            registerPanel.setVisible(true);
            registerPanel.setLayout(null);

            JLabel registerLabel = new JLabel("Welcome to Banking Management System");
            registerLabel.setBounds(200, 5, 600, 50); // Set x, y, width, height

            // Setting border of the label
            registerLabel.setBorder(new LineBorder(Color.black, 6, true));

            // Setting Alignment
            registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            registerLabel.setVerticalAlignment(SwingConstants.CENTER);
            registerLabel.setFont(jetBrains);

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

            // Username Label
            JLabel accountTypeLabel = new JLabel("Account Type ");
            accountTypeLabel.setFont(jetBrains);
            accountTypeLabel.setBounds(330, 200, 150, 30);

            // Savings Account Btn
            JButton savingsAccountBtn = new JButton("Savings");
            savingsAccountBtn.setFont(jetBrainsMed);
            savingsAccountBtn.setBounds(450, 205, 150, 25);

            // Savings Account Btn
            JButton currentAccountBtn = new JButton("Current");
            currentAccountBtn.setFont(jetBrainsMed);
            currentAccountBtn.setBounds(600, 205, 150, 25);

            // Checking which accountType User Selected
            AtomicReference<String> accountType = new AtomicReference<>("");
            savingsAccountBtn.addActionListener(savings -> {
                accountType.set("Savings");
                savingsAccountBtn.setBorder(new LineBorder(Color.blue, 4, true));
                currentAccountBtn.setBorder(null);
            });

            currentAccountBtn.addActionListener(current -> {
                accountType.set("Current");
                currentAccountBtn.setBorder(new LineBorder(Color.blue, 4, true));
                savingsAccountBtn.setBorder(null);
            });

            // Date Of Birth Label
            JLabel dobLabel = new JLabel("Date Of Birth ");
            dobLabel.setFont(jetBrains);
            dobLabel.setBounds(320, 255, 200, 30);

            // Date Of Birth TextField
            JTextField dobText = new JTextField();
            dobText.setFont(jetBrainsMed);
            dobText.setBounds(450, 255, 200, 25);

            // Date Of Birth Label
            JLabel initialBalanceLabel = new JLabel("Balance Initial ");
            initialBalanceLabel.setFont(jetBrains);
            initialBalanceLabel.setBounds(310, 305, 200, 30);

            // Date Of Birth TextField
            JTextField initialBalanceText = new JTextField();
            initialBalanceText.setFont(jetBrainsMed);
            initialBalanceText.setBounds(450, 305, 200, 25);

            // Register Button
            JButton registerButton = new JButton("Register");
            registerButton.setFont(jetBrains);
            registerButton.setBounds(450, 355, 200, 25);
            registerButton.setEnabled(false);

            final String[] username = {usernameText.getText().trim()};
            final String[] password = {new String(passwordText.getPassword()).trim()};
            final String[] dateOfBirth = {dobText.getText()};
            final String[] initialBalance = {initialBalanceText.getText()};
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

                private void checkFields() {
                    username[0] = usernameText.getText().trim();
                    password[0] = new String(passwordText.getPassword()).trim();
                    dateOfBirth[0] = dobText.getText();
                    initialBalance[0] = initialBalanceText.getText();
                    registerButton.setEnabled(!username[0].isEmpty() && !password[0].isEmpty()
                            && !dateOfBirth[0].isEmpty() && !initialBalance[0].isEmpty());
                }
            };

            // Adding Document Listener to Fields
            usernameText.getDocument().addDocumentListener(documentListener);
            passwordText.getDocument().addDocumentListener(documentListener);
            dobText.getDocument().addDocumentListener(documentListener);
            initialBalanceText.getDocument().addDocumentListener(documentListener);

            // When register Button is pressed
            registerButton.addActionListener(register -> {
                try {
                    double bankBal = Double.parseDouble(initialBalance[0]);
                    Document user = Database.findUserByName(username[0]);
                    // Checking if the username already exists or not
                    if (user != null)
                        JOptionPane.showMessageDialog(registerFrame, "Username Already Exists");
                    // Checking if user didn't enter a non-positive enter as a balance
                    else if (bankBal <= 0)
                        JOptionPane.showMessageDialog(registerFrame, "Enter a valid amount as initial Balance");
                    // Success scenario
                    else {
                        Database.createUser(username[0], password[0], accountType.get(), false, dateOfBirth[0], initialBalance[0]);
                        JOptionPane.showMessageDialog(registerFrame, "Account Created Successfully");

                        // Switching frames
                        registerFrame.setVisible(false);
                        frame.setVisible(true);
                    }
                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(registerFrame, "Enter a valid amount in initial Balance");
                }
            });

            // Go Back Btn
            JButton goBack = new JButton("Back");
            goBack.setFont(jetBrains);
            goBack.setBounds(450, 405, 200, 25);

            goBack.addActionListener(back -> {
                registerFrame.setVisible(false);
                frame.setVisible(true);
            });

            // Adding
            registerPanel.add(registerLabel);
            registerPanel.add(usernameLabel);
            registerPanel.add(usernameText);
            registerPanel.add(passwordLabel);
            registerPanel.add(passwordText);
            registerPanel.add(accountTypeLabel);
            registerPanel.add(savingsAccountBtn);
            registerPanel.add(currentAccountBtn);
            registerPanel.add(dobLabel);
            registerPanel.add(dobText);
            registerPanel.add(initialBalanceLabel);
            registerPanel.add(initialBalanceText);
            registerPanel.add(registerButton);
            registerPanel.add(goBack);

            // Getting Back Btn
            registerFrame.add(registerPanel);
        });

        panel.add(registerBtn);
    }
}
