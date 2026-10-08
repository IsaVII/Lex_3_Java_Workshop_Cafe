package se.lexicon.ui;

import se.lexicon.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Locale;

public class MenuPanel extends JPanel {

    public MenuPanel(List<MenuItem> menuItems) {

        setLayout(new GridLayout(menuItems.size(), 2, 10, 5));
        // Set a border around the panel -> padding
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 3, true),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        setPreferredSize(new Dimension(300, menuItems.size() * 30));
        
        //menu items 
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem menuItem = menuItems.get(i);
            String label = (i + 1) + ". " + menuItem.getName();
            String price = String.format(Locale.ENGLISH, "%.2f SEK", menuItem.getPrice());

            add(new JLabel(label));
            add(new JLabel(price, SwingConstants.RIGHT));
        }
    }
}
