package se.lexicon.ui;

import se.lexicon.LineItem;
import se.lexicon.MenuItem;
import se.lexicon.Order;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class OrderPanel extends JPanel {

    private static final int NAME_COLUMN_WIDTH = 150;

    private final Order order;
    private final Map<MenuItem, JSpinner> orderLines = new HashMap<>();
    private int nextRow = 0;

    public OrderPanel(Order order) {
        this.order = order;
        setLayout(new GridBagLayout());
    }

    public void addOrderLine(MenuItem menuItem) {
        JSpinner existingSpinner = orderLines.get(menuItem);
        if (existingSpinner != null) {
            int currentAmount = (int) existingSpinner.getValue();
            existingSpinner.setValue(currentAmount + 1);
            return;
        }

        //Name
        GridBagConstraints nameConstraints = createButtonConstraints(0, nextRow, GridBagConstraints.WEST);
        nameConstraints.insets = new Insets(5, 0, 5, 40);
        JLabel nameLabel = new JLabel(menuItem.getName());
        nameLabel.setPreferredSize(new Dimension(NAME_COLUMN_WIDTH, nameLabel.getPreferredSize().height));
        add(nameLabel, nameConstraints);

        //Price
        GridBagConstraints priceConstraints = createButtonConstraints(1, nextRow, GridBagConstraints.EAST);
        priceConstraints.insets = new Insets(5, 20, 5, 0);
        String priceText = String.format(Locale.ENGLISH, "%.2f SEK", menuItem.getPrice());
        add(new JLabel(priceText), priceConstraints);

        LineItem lineItem = new LineItem(menuItem, 1);
        order.addItem(lineItem);

        //Amount
        GridBagConstraints spinnerConstraints = createButtonConstraints(2, nextRow, GridBagConstraints.WEST);
        spinnerConstraints.insets = new Insets(5, 20, 5, 0);
        JSpinner amountSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        amountSpinner.addChangeListener(e -> lineItem.setAmount((int) amountSpinner.getValue()));
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
    
    private GridBagConstraints createButtonConstraints(int gridx, int gridy, int anchor) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.anchor = anchor;
        return constraints;
    }
}
