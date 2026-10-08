package se.lexicon.ui;

import se.lexicon.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class MenuPanel extends JPanel {

    public MenuPanel(List<MenuItem> menuItems, Consumer<MenuItem> onItemSelected) {

        setLayout(new GridLayout(menuItems.size(), 1, 0, 5));
        // Set a border around the panel -> padding
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 3, true),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        setPreferredSize(new Dimension(350, menuItems.size() * 30));

        //menu items
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem menuItem = menuItems.get(i);
            String text = String.format(Locale.ENGLISH, "%d. %-15s %6.2f SEK", i + 1, menuItem.getName(), menuItem.getPrice());

            JButton itemButton = new JButton(text);
            itemButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
            itemButton.setHorizontalAlignment(SwingConstants.LEFT);
            itemButton.addActionListener(e -> onItemSelected.accept(menuItem));

            add(itemButton);
        }
    }
}
