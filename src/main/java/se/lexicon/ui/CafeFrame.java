package se.lexicon.ui;

import se.lexicon.Menu;

import javax.swing.*;
import java.awt.*;

public class CafeFrame extends JFrame {

    
    public CafeFrame() {
        super("Lexicon Cafe");

        Menu menu = new Menu();

        //Main layout - stack content top to bottom
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        //Title
        JLabel titleLabel = new JLabel("Lexicon Cafe", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Menu
        JLabel menuLabel = new JLabel("Menu", SwingConstants.CENTER);
        menuLabel.setFont(menuLabel.getFont().deriveFont(Font.BOLD, 16f));
        menuLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        MenuPanel menuPanel = new MenuPanel(menu.getMenuItems());
        menuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.setMaximumSize(menuPanel.getPreferredSize());

        add(titleLabel);
        add(menuLabel);
        add(menuPanel);

        //Default data
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 900);
        setLocationRelativeTo(null);
    }


    void main() {

        SwingUtilities.invokeLater(() -> new CafeFrame().setVisible(true));
    }
}
