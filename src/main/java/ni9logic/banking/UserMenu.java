package ni9logic.banking;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UserMenu {
    private static void WithdrawBtn(JPanel panel, Font jetBrainsMed){
        JButton withdraw = new JButton("Withdraw Amount");
        withdraw.setFont(jetBrainsMed);
        withdraw.setBounds(350, 100, 200, 30);

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
        WithdrawBtn(panel, jetBrainsMed);
        DepositBtn(panel, jetBrainsMed);
        TransferBtn(panel, jetBrainsMed);
        ProfileBtn(panel, jetBrainsMed);
        TransactionsBtn(panel, jetBrainsMed);
        LogoutBtn(frame, menu, panel, jetBrainsMed);

        menu.add(panel);
    }
}
