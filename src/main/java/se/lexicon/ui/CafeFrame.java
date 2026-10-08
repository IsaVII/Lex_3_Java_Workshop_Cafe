package se.lexicon.ui;

import se.lexicon.Menu;

import javax.swing.*;
import java.awt.*;

public class CafeFrame extends JFrame {

    
    public CafeFrame() {
        super("Lexicon Cafe");
        
        UiComponentCreator componentCreator = new UiComponentCreator();
        Menu menu = new Menu();

        //Main layout - stack content top to bottom
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        OrderPanel orderPanel = new OrderPanel();
        orderPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        MenuPanel menuPanel = new MenuPanel(menu.getMenuItems(), orderPanel::addOrderLine);
        menuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.setMaximumSize(menuPanel.getPreferredSize());

        

        add(componentCreator.createTitleLabel());
        add(componentCreator.createGreetingLabel());
        add(componentCreator.createMenuLabel());
        add(menuPanel);
        add(orderPanel);

        //Default data
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 900);
        setLocationRelativeTo(null);
    }


    void main() {

        SwingUtilities.invokeLater(() -> new CafeFrame().setVisible(true));
    }
}
