package ni9logic.banking;


import org.bson.Document;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

public class UserMenu {
    private static void WithdrawBtn(JFrame menu, JPanel panel, Font jetBrainsMed, Font jetBrains, Document inSessionUser) {
        JButton withdraw = new JButton("Withdraw Amount");
        withdraw.setFont(jetBrainsMed);
        withdraw.setBounds(350, 100, 200, 30);

        // What would happen if withdraw btn is pressed
        withdraw.addActionListener(e -> {
            // Setting a panel in which instances will be added
            JPanel withdrawPanel = new JPanel();
            withdrawPanel.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
            withdrawPanel.setLayout(null);

            // Closing user menu frame
            menu.setVisible(false);

            // Creating new Frame for withdraw
            JFrame withdrawFrame = new JFrame("Withdraw Amount");

            // Setting Size and close operation
            withdrawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            withdrawFrame.setSize(Main.WIDTH, Main.HEIGHT);
            withdrawFrame.setVisible(true);
            withdrawFrame.setLayout(null);

            // Creating the label with message
            JLabel label = new JLabel("Welcome to banking management system!");
            label.setBounds(200, 5, 600, 50); // Set x, y, width, height

            // Setting border of the label
            label.setBorder(new LineBorder(Color.black, 6, true));

            // Setting Alignment
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setFont(jetBrains);


            // Getting a label for current Balance
            JLabel currentBalanceLabel = new JLabel("Your current balance is: " + inSessionUser.get("Balance"));
            currentBalanceLabel.setBounds(250, 100, 250, 50);
            currentBalanceLabel.setForeground(Color.blue);
            currentBalanceLabel.setFont(jetBrainsMed);

            // Withdraw Label
            JLabel withdrawAmountLabel = new JLabel("Withdraw Amount");
            withdrawAmountLabel.setFont(jetBrainsMed);
            withdrawAmountLabel.setBounds(250, 150, 250, 50);

            // Text Field
            JTextField withdrawText = new JTextField(20);
            withdrawText.setBounds(360, 160, 250, 30);

            // Withdraw Btn
            JButton withdrawButton = new JButton("Withdraw Amount");
            withdrawButton.setFont(jetBrainsMed);
            withdrawButton.setBounds(350, 250, 200, 30);
            withdrawButton.setEnabled(false);

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

                private void checkFields() {
                    String loginText = withdrawText.getText();
                    withdrawButton.setEnabled(!loginText.isEmpty());
                }
            };

            // Adding the document listener as well
            withdrawText.setText("");
            withdrawText.getDocument().addDocumentListener(documentListener);

            // What happens when withdraw Button is pressed
            withdrawButton.addActionListener(withdrawA -> {
                // Getting withdraw Amount
                String withdrawAmount = withdrawText.getText();

                // Handling if withdraw amount is empty
                try {
                    double dWithdrawAmount = Double.parseDouble(withdrawAmount);
                    double availableBalance = Double.parseDouble(inSessionUser.get("Balance").toString());

                    // Checking
                    if (dWithdrawAmount > 0 && dWithdrawAmount <= availableBalance) {
                        double newBalance = availableBalance - dWithdrawAmount;
                        boolean isUpdated = Database.updateUser(inSessionUser.get("Username").toString(), "Balance", String.valueOf(newBalance));

                        if (isUpdated) {
                            JOptionPane.showMessageDialog(withdrawFrame, "Amount Successfully withdrawn");
                            // Update the current balance label with the new balance

                            // Now to get latest updates
                            Document latestUser = Database.findUserByName(inSessionUser.get("Username").toString());
                            assert latestUser != null;
                            currentBalanceLabel.setText("Your current balance is: " + latestUser.get("Balance").toString());
                            currentBalanceLabel.repaint();

                            // Creating a transaction
                            Database.createTransaction(inSessionUser.get("Username").toString(), "", "Withdraw", dWithdrawAmount);

                        } else
                            JOptionPane.showMessageDialog(withdrawFrame, "Random Error Occurred");
                    } else if (dWithdrawAmount > availableBalance)
                        JOptionPane.showMessageDialog(withdrawFrame, "Insufficient Balance");
                    else
                        JOptionPane.showMessageDialog(withdrawFrame, "Enter valid amount");
                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(withdrawFrame, "Only digits allowed");
                }
            });


            // Go Back Btn
            JButton goBackBtn = new JButton("Back");
            goBackBtn.setFont(jetBrainsMed);
            goBackBtn.setBounds(350, 300, 200, 30);

            // Getting back to old frame
            goBackBtn.addActionListener(goBack -> {
                withdrawFrame.setVisible(false);
                menu.setVisible(true);
            });

            // Adding in panel
            withdrawPanel.add(label);
            withdrawPanel.add(currentBalanceLabel);
            withdrawPanel.add(withdrawAmountLabel);
            withdrawPanel.add(withdrawButton);
            withdrawPanel.add(withdrawText);
            withdrawPanel.add(goBackBtn);

            // Adding this in the frame
            withdrawFrame.add(withdrawPanel);
        });

        panel.add(withdraw);
    }

    private static void DepositBtn(JFrame menu, JPanel panel, Font jetBrainsMed, Font jetBrains, Document inSessionUser) {
        JButton deposit = new JButton("Deposit Amount");
        deposit.setFont(jetBrainsMed);
        deposit.setBounds(350, 150, 200, 30);

        // What would happen if deposit btn is pressed
        deposit.addActionListener(e -> {
            // Setting a panel in which instances will be added
            JPanel depositPanel = new JPanel();
            depositPanel.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
            depositPanel.setLayout(null);

            // Closing user menu frame
            menu.setVisible(false);

            // Creating new Frame for withdraw
            JFrame depositFrame = new JFrame("Deposit Amount");

            // Setting Size and close operation
            depositFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            depositFrame.setSize(Main.WIDTH, Main.HEIGHT);
            depositFrame.setVisible(true);
            depositFrame.setLayout(null);

            // Creating the label with message
            JLabel label = new JLabel("Welcome to banking management system!");
            label.setBounds(200, 5, 600, 50); // Set x, y, width, height

            // Setting border of the label
            label.setBorder(new LineBorder(Color.black, 6, true));

            // Setting Alignment
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setFont(jetBrains);


            // Getting a label for current Balance
            JLabel currentBalanceLabel = new JLabel("Your current balance is: " + inSessionUser.get("Balance"));
            currentBalanceLabel.setBounds(250, 100, 250, 50);
            currentBalanceLabel.setForeground(Color.blue);
            currentBalanceLabel.setFont(jetBrainsMed);

            // Deposit Label
            JLabel depositAmountLabel = new JLabel("Deposit Amount");
            depositAmountLabel.setFont(jetBrainsMed);
            depositAmountLabel.setBounds(250, 150, 250, 50);

            // Text Field
            JTextField DepositText = new JTextField(20);
            DepositText.setBounds(360, 160, 250, 30);

            // Deposit Btn
            JButton depositButton = new JButton("Deposit Amount");
            depositButton.setFont(jetBrainsMed);
            depositButton.setBounds(350, 250, 200, 30);
            depositButton.setEnabled(false);

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

                private void checkFields() {
                    String loginText = DepositText.getText();
                    depositButton.setEnabled(!loginText.isEmpty());
                }
            };

            // Adding the document listener as well
            DepositText.setText("");
            DepositText.getDocument().addDocumentListener(documentListener);

            // What happens when withdraw Button is pressed
            depositButton.addActionListener(withdrawA -> {
                // Getting deposit Amount
                String depositAmount = DepositText.getText();

                // Handling if withdraw amount is empty
                try {
                    double dWithdrawAmount = Double.parseDouble(depositAmount);
                    double availableBalance = Double.parseDouble(inSessionUser.get("Balance").toString());

                    // Checking
                    if (dWithdrawAmount > 0) {
                        double newBalance = availableBalance + dWithdrawAmount;
                        boolean isUpdated = Database.updateUser(inSessionUser.get("Username").toString(), "Balance", String.valueOf(newBalance));

                        if (isUpdated) {
                            JOptionPane.showMessageDialog(depositFrame, "Amount Successfully Deposited");
                            // Update the current balance label with the new balance

                            // Now to get latest updates
                            Document latestUser = Database.findUserByName(inSessionUser.get("Username").toString());
                            assert latestUser != null;
                            currentBalanceLabel.setText("Your current balance is: " + latestUser.get("Balance").toString());
                            currentBalanceLabel.repaint();

                            // Creating a transaction
                            Database.createTransaction(inSessionUser.get("Username").toString(), "", "Deposit", dWithdrawAmount);

                        } else
                            JOptionPane.showMessageDialog(depositFrame, "Random Error Occurred");
                    } else
                        JOptionPane.showMessageDialog(depositFrame, "Enter valid amount");
                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(depositFrame, "Only digits allowed");
                }
            });


            // Go Back Btn
            JButton goBackBtn = new JButton("Back");
            goBackBtn.setFont(jetBrainsMed);
            goBackBtn.setBounds(350, 300, 200, 30);

            // Getting back to old frame
            goBackBtn.addActionListener(goBack -> {
                depositFrame.setVisible(false);
                menu.setVisible(true);
            });

            // Adding in panel
            depositPanel.add(label);
            depositPanel.add(currentBalanceLabel);
            depositPanel.add(depositAmountLabel);
            depositPanel.add(depositButton);
            depositPanel.add(DepositText);
            depositPanel.add(goBackBtn);

            // Adding this in the frame
            depositFrame.add(depositPanel);
        });

        panel.add(deposit);
    }

    private static void TransferBtn(JFrame menu, JPanel panel, Font jetBrainsMed, Font jetBrains, Document inSessionUser) {
        JButton transferBtn = new JButton("Transfer Amount");
        transferBtn.setFont(jetBrainsMed);
        transferBtn.setBounds(350, 200, 200, 30);

        transferBtn.addActionListener(transfer -> {
            // Setting username to null so that user won't give a null input
            String username;

            while (true) {
                username = JOptionPane.showInputDialog(menu, "Enter username of the receiver");
                // Checking username is null or not
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(menu, "Kindly enter a username");
                    continue;
                }

                // Ending loop if username is not null
                break;
            }

            // Getting the user
            Document receiverUser = Database.findUserByName(username);

            // Checking if username exists or not
            if (receiverUser == null)
                JOptionPane.showMessageDialog(menu, "No such user exists");
            else {
                // Setting a panel in which instances will be added
                JPanel transferPanel = new JPanel();
                transferPanel.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
                transferPanel.setLayout(null);

                // Closing user menu frame
                menu.setVisible(false);

                // Creating new Frame for withdraw
                JFrame transferFrame = new JFrame("Transfer Amount");

                // Setting Size and close operation
                transferFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                transferFrame.setSize(Main.WIDTH, Main.HEIGHT);
                transferFrame.setVisible(true);
                transferFrame.setLayout(null);

                // Creating the label with message
                JLabel label = new JLabel("Welcome to banking management system!");
                label.setBounds(200, 5, 600, 50); // Set x, y, width, height

                // Setting border of the label
                label.setBorder(new LineBorder(Color.black, 6, true));

                // Setting Alignment
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setFont(jetBrains);


                // Getting a label for current Balance
                JLabel currentBalanceLabel = new JLabel("Your current balance is: " + inSessionUser.get("Balance"));
                currentBalanceLabel.setBounds(250, 100, 250, 50);
                currentBalanceLabel.setForeground(Color.blue);
                currentBalanceLabel.setFont(jetBrainsMed);

                // Deposit Label
                JLabel transferAmountLabel = new JLabel("Transfer Amount");
                transferAmountLabel.setFont(jetBrainsMed);
                transferAmountLabel.setBounds(250, 150, 250, 50);

                // Text Field
                JTextField transferText = new JTextField(20);
                transferText.setBounds(360, 160, 250, 30);

                // Deposit Btn
                JButton transferButton = new JButton("Transfer Amount");
                transferButton.setFont(jetBrainsMed);
                transferButton.setBounds(350, 250, 200, 30);
                transferButton.setEnabled(false);

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

                    private void checkFields() {
                        String loginText = transferText.getText();
                        transferButton.setEnabled(!loginText.isEmpty());
                    }
                };

                // Adding the document listener as well
                transferText.setText("");
                transferText.getDocument().addDocumentListener(documentListener);

                // What happens when withdraw Button is pressed
                transferButton.addActionListener(withdrawA -> {
                    // Getting deposit Amount
                    String transferAmount = transferText.getText();

                    // Handling if withdraw amount is empty
                    try {
                        double dTransferAmount = Double.parseDouble(transferAmount);
                        double availableBalance = Double.parseDouble(inSessionUser.get("Balance").toString());
                        double receiverAvailableBalance = Double.parseDouble(receiverUser.get("Balance").toString());

                        // Checking
                        if (dTransferAmount > 0) {
                            double newBalance = availableBalance - dTransferAmount;
                            double receiverNewBalance = receiverAvailableBalance + dTransferAmount;
                            boolean isUpdated = Database.updateUser(inSessionUser.get("Username").toString(), "Balance", String.valueOf(newBalance));
                            boolean receiverIsUpdated = Database.updateUser(receiverUser.get("Username").toString(), "Balance", String.valueOf(receiverNewBalance));


                            if (isUpdated && receiverIsUpdated) {
                                JOptionPane.showMessageDialog(transferFrame, "Amount Successfully Transferred");
                                // Update the current balance label with the new balance

                                // Now to get latest updates
                                Document latestUser = Database.findUserByName(inSessionUser.get("Username").toString());
                                assert latestUser != null;
                                currentBalanceLabel.setText("Your current balance is: " + latestUser.get("Balance").toString());
                                currentBalanceLabel.repaint();

                                // Sender Transaction
                                Database.createTransaction(inSessionUser.get("Username").toString(), receiverUser.get("Username").toString(), "Transfer-Send", dTransferAmount);
                                // Receiver Transaction
                                Database.createTransaction(receiverUser.get("Username").toString(), inSessionUser.get("Username").toString(), "Transfer-Receive", dTransferAmount);

                            } else
                                JOptionPane.showMessageDialog(transferFrame, "Random Error Occurred");
                        } else
                            JOptionPane.showMessageDialog(transferFrame, "Enter valid amount");
                    } catch (NumberFormatException error) {
                        JOptionPane.showMessageDialog(transferFrame, "Only digits allowed");
                    }
                });


                // Go Back Btn
                JButton goBackBtn = new JButton("Back");
                goBackBtn.setFont(jetBrainsMed);
                goBackBtn.setBounds(350, 300, 200, 30);

                // Getting back to old frame
                goBackBtn.addActionListener(goBack -> {
                    transferFrame.setVisible(false);
                    menu.setVisible(true);
                });

                // Adding in panel
                transferPanel.add(label);
                transferPanel.add(currentBalanceLabel);
                transferPanel.add(transferAmountLabel);
                transferPanel.add(transferButton);
                transferPanel.add(transferText);
                transferPanel.add(goBackBtn);

                // Adding this in the frame
                transferFrame.add(transferPanel);
            }
        });

        // Adding to panel
        panel.add(transferBtn);
    }

    private static void ProfileBtn(JFrame menu, JPanel panel, Font jetBrainsMed, Font jetBrains, Document inSessionUser) {
        JButton profileBtn = new JButton("Show Profile");
        profileBtn.setFont(jetBrainsMed);
        profileBtn.setBounds(350, 250, 200, 30);

        profileBtn.addActionListener(profile -> {
            // Setting a panel in which instances will be added
            JPanel profilePanel = new JPanel();
            profilePanel.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
            profilePanel.setLayout(null);

            // Closing user menu frame
            menu.setVisible(false);

            // Creating new Frame for withdraw
            JFrame profileFrame = new JFrame("Show Profile");

            // Setting Size and close operation
            profileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            profileFrame.setSize(Main.WIDTH, Main.HEIGHT);
            profileFrame.setVisible(true);
            profileFrame.setLayout(null);

            // Creating the label with message
            JLabel label = new JLabel("Welcome to banking management system!");
            label.setBounds(200, 5, 600, 50); // Set x, y, width, height

            // Setting border of the label
            label.setBorder(new LineBorder(Color.black, 6, true));

            // Setting Alignment
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setFont(jetBrains);


            // Username Label
            JLabel usernameLabel = new JLabel("Username ");
            usernameLabel.setFont(jetBrains);
            usernameLabel.setBounds(310, 100, 100, 30);
            // Get Username
            String username = inSessionUser.get("Username").toString();
            // Actual Username
            JLabel usernameShowLabel = new JLabel(username);
            usernameShowLabel.setFont(jetBrainsMed);
            usernameShowLabel.setBounds(450, 105, 200, 25);

            // Password Label
            JLabel passwordLabel = new JLabel("Password ");
            passwordLabel.setFont(jetBrains);
            passwordLabel.setBounds(310, 150, 100, 30);
            // Get Password
            String password = inSessionUser.get("Password").toString();
            // Password Text Field
            JLabel passwordText = new JLabel(password);
            passwordText.setFont(jetBrainsMed);
            passwordText.setBounds(450, 150, 500, 30);

            // Account Type Label
            JLabel accountTypeLabel = new JLabel("Account Type ");
            accountTypeLabel.setFont(jetBrains);
            accountTypeLabel.setBounds(310, 200, 150, 30);
            // Get Account Type
            String accountType = inSessionUser.get("Account_type").toString();
            // Account Type Text
            JLabel accountTypeText = new JLabel(accountType);
            accountTypeText.setFont(jetBrainsMed);
            accountTypeText.setBounds(450, 200, 150, 30);

            // Date Of Birth Label
            JLabel dobLabel = new JLabel("Date Of Birth ");
            dobLabel.setFont(jetBrains);
            dobLabel.setBounds(310, 255, 200, 30);
            // Date of birth
            String dob = inSessionUser.get("Date-Of-Birth").toString();
            // Date Of Birth TextField
            JLabel dobText = new JLabel(dob);
            dobText.setFont(jetBrainsMed);
            dobText.setBounds(450, 255, 200, 30);

            // Balance label
            JLabel balanceLabel = new JLabel("Balance Initial ");
            balanceLabel.setFont(jetBrains);
            balanceLabel.setBounds(310, 305, 200, 30);
            // Balance
            String balance = inSessionUser.get("Balance").toString();

            // Date Of Birth TextField
            JLabel balanceText = new JLabel(balance);
            balanceText.setFont(jetBrainsMed);
            balanceText.setBounds(450, 305, 200, 30);

            // Balance label
            JLabel isAdminLabel = new JLabel("Admin Status ");
            isAdminLabel.setFont(jetBrains);
            isAdminLabel.setBounds(310, 355, 200, 30);
            // Balance
            String isAdmin = String.valueOf(inSessionUser.get("is_Admin"));
            // Date Of Birth TextField
            JLabel isAdminText = new JLabel(isAdmin);
            isAdminText.setFont(jetBrainsMed);
            isAdminText.setBounds(450, 355, 200, 30);

            // Date of Creation Label
            JLabel creationLabel = new JLabel("Created At ");
            creationLabel.setFont(jetBrains);
            creationLabel.setBounds(310, 405, 200, 30);
            // Balance
            String createdAt = String.valueOf(inSessionUser.get("Created-At"));
            // Date Of Birth TextField
            JLabel creationText = new JLabel(createdAt);
            creationText.setFont(jetBrainsMed);
            creationText.setBounds(450, 405, 200, 30);

            // Go Back Btn
            JButton goBackBtn = new JButton("Back");
            goBackBtn.setFont(jetBrainsMed);
            goBackBtn.setBounds(450, 455, 200, 30);

            // Getting back to old frame
            goBackBtn.addActionListener(goBack -> {
                profileFrame.setVisible(false);
                menu.setVisible(true);
            });

            // Adding in panel
            profileFrame.add(label);
            profileFrame.add(usernameLabel);
            profileFrame.add(usernameShowLabel);
            profileFrame.add(passwordLabel);
            profileFrame.add(passwordText);
            profileFrame.add(accountTypeLabel);
            profileFrame.add(accountTypeText);
            profileFrame.add(dobText);
            profileFrame.add(dobLabel);
            profileFrame.add(balanceLabel);
            profileFrame.add(balanceText);
            profileFrame.add(isAdminLabel);
            profileFrame.add(isAdminText);
            profileFrame.add(creationLabel);
            profileFrame.add(creationText);
            profileFrame.add(goBackBtn);
        });

        panel.add(profileBtn);
    }

    private static void TransactionsBtn(JFrame menu, JPanel panel, Font jetBrainsMed, Font jetBrains, Document inSessionUser) {
        JButton transactionBtn = new JButton("Show Transactions");
        transactionBtn.setFont(jetBrainsMed);
        transactionBtn.setBounds(350, 300, 200, 30);

        transactionBtn.addActionListener(transaction -> {
            // Setting a panel in which instances will be added
            JPanel transactionPanel = new JPanel();
            transactionPanel.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);
            transactionPanel.setLayout(null);

            // Closing user menu frame
            menu.setVisible(false);

            // Creating new Frame for withdraw
            JFrame transactionFrame = new JFrame("Deposit Amount");

            // Setting Size and close operation
            transactionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            transactionFrame.setSize(Main.WIDTH, Main.HEIGHT);
            transactionFrame.setVisible(true);
            transactionFrame.setLayout(null);

            // Creating the label with message
            JLabel label = new JLabel("Welcome to banking management system!");
            label.setBounds(200, 5, 600, 50); // Set x, y, width, height

            // Setting border of the label
            label.setBorder(new LineBorder(Color.black, 6, true));

            // Setting Alignment
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setFont(jetBrains);

            // Getting the list of transactions of inSessionUser
            List<Document> Transactions = Database.getRecentTransactionsByUsername(inSessionUser.get("Username").toString(), 5);

            // Axis
            int x = 30;
            int i = 0;
            for (Document trans : Transactions) {
                JLabel transactionLabel = new JLabel("Transaction %d".formatted(i + 1));

                transactionLabel.setBounds(x, 100, 500, 50);
                transactionLabel.setFont(jetBrains);

                // Username
                JLabel usernameLabel = new JLabel("Username: ");
                usernameLabel.setBounds(x, 130, 500, 50);
                usernameLabel.setFont(jetBrainsMed);

                JLabel username = new JLabel(trans.get("Username").toString());
                username.setBounds(x + 70, 130, 500, 50);
                username.setFont(jetBrainsMed);

                // Transaction Type
                JLabel transactionType = new JLabel("Type: ");
                transactionType.setBounds(x, 160, 500, 50);
                transactionType.setFont(jetBrainsMed);

                JLabel transType = new JLabel(trans.get("Transaction Type").toString());
                transType.setBounds(x + 45, 160, 500, 50);
                transType.setFont(jetBrainsMed);

                // Time Stamp
                JLabel timestampLabel = new JLabel("TimeStamp: ");
                timestampLabel.setBounds(x, 190, 500, 50);
                timestampLabel.setFont(jetBrainsMed);

                // Getting time
                String time = trans.get("Transaction At").toString();
                String[] parts = time.split(" ");
                String Year = parts[0];

                JLabel timestamp = new JLabel(Year);
                timestamp.setBounds(x + 80, 190, 500, 50);
                timestamp.setFont(jetBrainsMed);

                if (trans.get("Transaction Type").toString().equals("Transfer-Receive")) {
                    JLabel fromUserLabel = new JLabel("From User: ");
                    fromUserLabel.setBounds(x, 250, 500, 50);
                    fromUserLabel.setFont(jetBrainsMed);

                    JLabel fromUser = new JLabel(trans.get("From User").toString());
                    fromUser.setBounds(x + 70, 250, 500, 50);
                    fromUser.setFont(jetBrainsMed);

                    transactionPanel.add(fromUserLabel);
                    transactionPanel.add(fromUser);
                } else if (trans.get("Transaction Type").toString().equals("Transfer-Send")) {
                    JLabel fromUserLabel = new JLabel("To User: ");
                    fromUserLabel.setBounds(x, 250, 500, 50);
                    fromUserLabel.setFont(jetBrainsMed);

                    JLabel fromUser = new JLabel(trans.get("To User").toString());
                    fromUser.setBounds(x + 70, 250, 500, 50);
                    fromUser.setFont(jetBrainsMed);

                    transactionPanel.add(fromUserLabel);
                    transactionPanel.add(fromUser);
                }
                // Amount
                JLabel amountLabel = new JLabel("Amount: ");
                amountLabel.setBounds(x, 220, 500, 50);
                amountLabel.setFont(jetBrainsMed);

                JLabel amount = new JLabel(trans.get("Transaction Amount").toString());
                amount.setBounds(x + 50, 220, 500, 50);
                amount.setFont(jetBrainsMed);


                // Adding in panel
                transactionPanel.add(transactionLabel);
                transactionPanel.add(transactionType);
                transactionPanel.add(transType);
                transactionPanel.add(amountLabel);
                transactionPanel.add(amount);
                transactionPanel.add(timestampLabel);
                transactionPanel.add(timestamp);
                transactionPanel.add(usernameLabel);
                transactionPanel.add(username);

                // Axis
                x += 200;
                i += 1;
            }

            // Go back btn
            JButton goBackBtn = new JButton("Back");
            goBackBtn.setFont(jetBrains);

            goBackBtn.setBounds(355, 400, 200, 30);

            goBackBtn.addActionListener(back -> {
                transactionFrame.setVisible(false);
                menu.setVisible(true);
            });
            // Adding
            transactionPanel.add(label);
            transactionPanel.add(goBackBtn);


            transactionFrame.add(transactionPanel);
        });

        panel.add(transactionBtn);
    }

    private static void LogoutBtn(JFrame mainFrame, JFrame menuFrame, JPanel panel, Font jetBrainsMed) {
        JButton logoutBtn = new JButton("Log Out");
        logoutBtn.setFont(jetBrainsMed);
        logoutBtn.setBounds(350, 350, 200, 30);

        logoutBtn.addActionListener(e -> {
            menuFrame.setVisible(false);
            mainFrame.setVisible(true);
        });

        panel.add(logoutBtn);
    }

    public static void userMenu(JFrame frame, Font jetBrains, Font jetBrainsMed, Document inSessionUser) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, Main.WIDTH, Main.HEIGHT);

        // Closing old frame
        frame.setVisible(false);

        JFrame menu = new JFrame("User Menu");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu.setSize(Main.WIDTH, Main.HEIGHT);
        menu.setVisible(true);

        menu.setLayout(null);

        // Creating the label with message
        JLabel label = new JLabel("Welcome to banking management system!");
        label.setBounds(200, 5, 600, 50); // Set x, y, width, height

        // Setting border of the label
        label.setBorder(new LineBorder(Color.black, 6, true));

        // Setting Alignment
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(jetBrains);

        // Adding in panel
        panel.add(label);
        WithdrawBtn(menu, panel, jetBrainsMed, jetBrains, inSessionUser);
        DepositBtn(menu, panel, jetBrainsMed, jetBrains, inSessionUser);
        TransferBtn(menu, panel, jetBrainsMed, jetBrains, inSessionUser);
        ProfileBtn(menu, panel, jetBrainsMed, jetBrains, inSessionUser);
        TransactionsBtn(menu, panel, jetBrainsMed, jetBrains, inSessionUser);
        LogoutBtn(frame, menu, panel, jetBrainsMed);

        menu.add(panel);
    }
}
