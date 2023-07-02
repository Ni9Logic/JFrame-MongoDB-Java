package ni9logic.banking;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UserMenu {
    private static void WithdrawBtn(JFrame menu, JPanel panel, Font jetBrainsMed, Font jetBrains){
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

            // This will remain unused for now
            String currentBalance = "25000";

            // Getting a label for current Balance
            JLabel currentBalanceLabel = new JLabel("Your current balance is: " + currentBalance);
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

            // What happens when withdraw Button is pressed
            withdrawButton.addActionListener(withdrawA -> {
                // Getting withdraw Amount
                String withdrawAmount = withdrawText.getText();

                // Handling if withdraw amount is empty
                if (!withdrawAmount.isEmpty()) {
                    try {
                        double dWithdrawAmount = Double.parseDouble(withdrawAmount);
                        double availableBalance = Double.parseDouble(currentBalance);

                        // Checking
                        if (dWithdrawAmount > 0 && dWithdrawAmount <= availableBalance)
                            JOptionPane.showMessageDialog(withdrawFrame, "Good input");
                        else if (dWithdrawAmount > availableBalance)
                            JOptionPane.showMessageDialog(withdrawFrame, "Insufficient Balance");
                        else
                            JOptionPane.showMessageDialog(withdrawFrame, "Enter valid amount");
                    } catch (NumberFormatException error){
                        JOptionPane.showMessageDialog(withdrawFrame, "Only digits allowed");
                    }
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

    private static void DepositBtn(JPanel panel, Font jetBrainsMed){
        JButton deposit = new JButton("Deposit Amount");
        deposit.setFont(jetBrainsMed);
        deposit.setBounds(350, 150, 200, 30);

        panel.add(deposit);
    }

    private static void TransferBtn(JPanel panel, Font jetBrainsMed){
        JButton transferBtn = new JButton("Transfer Amount");
        transferBtn.setFont(jetBrainsMed);
        transferBtn.setBounds(350, 200, 200, 30);

        panel.add(transferBtn);
    }

    private static void ProfileBtn(JPanel panel, Font jetBrainsMed){
        JButton profileBtn = new JButton("Show Profile");
        profileBtn.setFont(jetBrainsMed);
        profileBtn.setBounds(350, 250, 200, 30);

        panel.add(profileBtn);
    }

    private static void TransactionsBtn(JPanel panel, Font jetBrainsMed){
        JButton transactionBtn = new JButton("Show Transactions");
        transactionBtn.setFont(jetBrainsMed);
        transactionBtn.setBounds(350, 300, 200, 30);

        panel.add(transactionBtn);
    }
    private static void LogoutBtn(JFrame mainFrame, JFrame menuFrame, JPanel panel, Font jetBrainsMed){
        JButton logoutBtn = new JButton("Log Out");
        logoutBtn.setFont(jetBrainsMed);
        logoutBtn.setBounds(350, 350, 200, 30);

        logoutBtn.addActionListener(e -> {
            menuFrame.setVisible(false);
            mainFrame.setVisible(true);
        });

        panel.add(logoutBtn);
    }
    public static void userMenu(JFrame frame, Font jetBrains, Font jetBrainsMed){
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
        WithdrawBtn(menu, panel, jetBrainsMed, jetBrains);
        DepositBtn(panel, jetBrainsMed);
        TransferBtn(panel, jetBrainsMed);
        ProfileBtn(panel, jetBrainsMed);
        TransactionsBtn(panel, jetBrainsMed);
        LogoutBtn(frame, menu, panel, jetBrainsMed);

        menu.add(panel);
    }
}
