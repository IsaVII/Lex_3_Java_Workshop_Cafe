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

    private final Order order;
    private final Map<MenuItem, JSpinner> orderLines = new HashMap<>();
    private final Dimension reservedSize;
    private int nextRow = 0;
    private Runnable onOrderChanged = () -> {};

    public OrderPanel(Order order, int maxDistinctItems) {
        this.order = order;
        this.reservedSize = new Dimension(PANEL_WIDTH, maxDistinctItems * ROW_HEIGHT-40);
        setLayout(new GridBagLayout());

        // Filler row below every possible real row: absorbs leftover vertical
        // space so GridBagLayout anchors the real rows to the top instead of
        // centering the whole grid within the panel's fixed reserved height.
        GridBagConstraints fillerConstraints = new GridBagConstraints();
        fillerConstraints.gridx = 0;
        fillerConstraints.gridy = maxDistinctItems;
        fillerConstraints.weighty = 1.0;
        fillerConstraints.fill = GridBagConstraints.VERTICAL;
        add(Box.createGlue(), fillerConstraints);
    }

    public void setOnOrderChanged(Runnable onOrderChanged) {
        this.onOrderChanged = onOrderChanged;
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
        amountSpinner.addChangeListener(e -> {
            lineItem.setAmount((int) amountSpinner.getValue());
            onOrderChanged.run();
        });
        add(amountSpinner, spinnerConstraints);

        orderLines.put(menuItem, amountSpinner);
        nextRow++;

        revalidate();
        repaint();
        onOrderChanged.run();
    }

    @Override
    public Dimension getPreferredSize() {
        return reservedSize;
    }

    @Override
    public Dimension getMaximumSize() {
        return reservedSize;
    }
    
    private GridBagConstraints createButtonConstraints(int gridx, int gridy, int anchor) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.anchor = anchor;
        return constraints;
    }
}
