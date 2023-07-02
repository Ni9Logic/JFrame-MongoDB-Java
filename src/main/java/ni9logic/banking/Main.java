package ni9logic.banking;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Main {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 800;

    public static void main(String[] args) {
        // Creating a panel so we can use multiple instances
        JPanel panel = new JPanel();

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

        Login.loginMenu(frame, panel, jetBrains, jetBrainsMed);

        panel.add(label);
        panel.setLayout(null);
        panel.setBounds(0, 0, WIDTH, HEIGHT);


        // Setting frame size
        frame.add(panel);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }
}