package ni9logic.banking;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Register {
    public static void registerMenu(JFrame frame, JPanel panel, Font jetBrains) {
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

            // Getting Back Btn
            registerFrame.add(registerPanel);
        });

        panel.add(registerBtn);
    }
}
