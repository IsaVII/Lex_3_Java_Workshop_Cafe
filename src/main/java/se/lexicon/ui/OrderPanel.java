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
    private static final int PANEL_WIDTH = 350;
    private static final int ROW_HEIGHT = 40;

    private Order order;
    private final Map<MenuItem, JSpinner> orderLines = new HashMap<>();
    private final Dimension reservedSize;
    private final int maxDistinctItems;
    private Runnable onOrderChanged = () -> {};

    public OrderPanel(Order order, int maxDistinctItems) {
        this.order = order;
        this.maxDistinctItems = maxDistinctItems;
        this.reservedSize = new Dimension(PANEL_WIDTH, maxDistinctItems * ROW_HEIGHT - 40);
        setLayout(new GridBagLayout());
        addFiller(0);
    }

    public void setOnOrderChanged(Runnable onOrderChanged) {
        this.onOrderChanged = onOrderChanged;
    }

    public void resetOrder(Order newOrder) {
        this.order = newOrder;
        rebuild();
    }

    public void addOrderLine(MenuItem menuItem) {
        JSpinner existingSpinner = orderLines.get(menuItem);
        if (existingSpinner != null) {
            int currentAmount = (int) existingSpinner.getValue();
            existingSpinner.setValue(currentAmount + 1);
            return;
        }

        LineItem lineItem = new LineItem(menuItem, 1);
        order.addItem(lineItem);
        rebuild();
    }

    private void removeOrderLine(LineItem lineItem) {
        order.removeItem(lineItem);
        rebuild();
    }

    private void rebuild() {
        removeAll();
        orderLines.clear();

        int row = 0;
        for (LineItem lineItem : order.getLineItems()) {
            addRow(row, lineItem);
            row++;
        }

        addFiller(row);

        revalidate();
        repaint();
        onOrderChanged.run();
    }

    private void addRow(int row, LineItem lineItem) {
        MenuItem menuItem = lineItem.getMenuItem();

        //Name
        GridBagConstraints nameConstraints = createConstraints(0, row, GridBagConstraints.WEST);
        nameConstraints.insets = new Insets(5, 0, 5, 40);
        JLabel nameLabel = new JLabel(menuItem.getName());
        nameLabel.setPreferredSize(new Dimension(NAME_COLUMN_WIDTH, nameLabel.getPreferredSize().height));
        add(nameLabel, nameConstraints);

        //Price
        GridBagConstraints priceConstraints = createConstraints(1, row, GridBagConstraints.EAST);
        priceConstraints.insets = new Insets(5, 20, 5, 0);
        String priceText = String.format(Locale.ENGLISH, "%.2f SEK", menuItem.getPrice());
        add(new JLabel(priceText), priceConstraints);

        //Amount
        GridBagConstraints spinnerConstraints = createConstraints(2, row, GridBagConstraints.WEST);
        spinnerConstraints.insets = new Insets(5, 20, 5, 0);
        JSpinner amountSpinner = new JSpinner(new SpinnerNumberModel(lineItem.getAmount(), 1, 99, 1));
        amountSpinner.addChangeListener(e -> {
            lineItem.setAmount((int) amountSpinner.getValue());
            onOrderChanged.run();
        });
        add(amountSpinner, spinnerConstraints);
        orderLines.put(menuItem, amountSpinner);

        //Remove
        GridBagConstraints removeConstraints = createConstraints(3, row, GridBagConstraints.WEST);
        removeConstraints.insets = new Insets(5, 10, 5, 0);
        JButton removeButton = new JButton("X");
        removeButton.setMargin(new Insets(0, 4, 0, 4));
        removeButton.setForeground(Color.RED);
        removeButton.setFont(removeButton.getFont().deriveFont(Font.BOLD));
        removeButton.setFocusPainted(false);
        removeButton.addActionListener(e -> removeOrderLine(lineItem));
        add(removeButton, removeConstraints);
    }

    private void addFiller(int row) {
        GridBagConstraints fillerConstraints = new GridBagConstraints();
        fillerConstraints.gridx = 0;
        fillerConstraints.gridy = Math.max(row, maxDistinctItems);
        fillerConstraints.weighty = 1.0;
        fillerConstraints.fill = GridBagConstraints.VERTICAL;
        add(Box.createGlue(), fillerConstraints);
    }

    @Override
    public Dimension getPreferredSize() {
        return reservedSize;
    }

    @Override
    public Dimension getMaximumSize() {
        return reservedSize;
    }

    private GridBagConstraints createConstraints(int gridx, int gridy, int anchor) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.anchor = anchor;
        return constraints;
    }
}
