package se.lexicon.ui;

import se.lexicon.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class OrderPanel extends JPanel {

    private static final int NAME_COLUMN_WIDTH = 150;

    private final Map<MenuItem, JSpinner> orderLines = new HashMap<>();
    private int nextRow = 0;

    public OrderPanel() {
        setLayout(new GridBagLayout());
    }

    public void addOrderLine(MenuItem menuItem) {
        JSpinner existingSpinner = orderLines.get(menuItem);
        if (existingSpinner != null) {
            int currentAmount = (int) existingSpinner.getValue();
            existingSpinner.setValue(currentAmount + 1);
            return;
        }

        GridBagConstraints nameConstraints = new GridBagConstraints();
        nameConstraints.gridx = 0;
        nameConstraints.gridy = nextRow;
        nameConstraints.anchor = GridBagConstraints.WEST;
        nameConstraints.insets = new Insets(5, 0, 5, 40);
        JLabel nameLabel = new JLabel(menuItem.getName());
        nameLabel.setPreferredSize(new Dimension(NAME_COLUMN_WIDTH, nameLabel.getPreferredSize().height));
        add(nameLabel, nameConstraints);

        GridBagConstraints priceConstraints = new GridBagConstraints();
        priceConstraints.gridx = 1;
        priceConstraints.gridy = nextRow;
        priceConstraints.anchor = GridBagConstraints.EAST;
        priceConstraints.insets = new Insets(5, 20, 5, 0);
        String priceText = String.format(Locale.ENGLISH, "%.2f SEK", menuItem.getPrice());
        add(new JLabel(priceText), priceConstraints);

        GridBagConstraints spinnerConstraints = new GridBagConstraints();
        spinnerConstraints.gridx = 2;
        spinnerConstraints.gridy = nextRow;
        spinnerConstraints.anchor = GridBagConstraints.WEST;
        spinnerConstraints.insets = new Insets(5, 20, 5, 0);
        JSpinner amountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        add(amountSpinner, spinnerConstraints);

        orderLines.put(menuItem, amountSpinner);
        nextRow++;

        revalidate();
        repaint();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
}
