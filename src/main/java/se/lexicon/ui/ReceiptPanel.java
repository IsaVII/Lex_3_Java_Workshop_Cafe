package se.lexicon.ui;

import se.lexicon.LineItem;
import se.lexicon.Order;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class ReceiptPanel extends JPanel {

    private static final Font RECEIPT_FONT = new Font(Font.DIALOG, Font.PLAIN, 13);
    private static final Font TOTAL_FONT = new Font(Font.DIALOG, Font.BOLD, 14);
    private static final int NAME_COLUMN_WIDTH = 150;
    private static final int AMOUNT_COLUMN_WIDTH = 50;
    private static final int SMALL_COLUMN_WIDTH = 10;
    private Dimension reservedSize;
    
    public ReceiptPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        reservedSize = new Dimension(280,  200);
    }

    public void refresh(Order order) {
        removeAll();
        int row = 0;

        //Show customer name a
        addLine(row++, String.format("%-10s : %s", "Customer", order.getCustomerName()));
        printLine(row++);

        //Show order items
        for (LineItem lineItem : order.getLineItems()) {
            addItemLine(row++, lineItem.getMenuItem().getName(), lineItem.getAmount(), lineItem.getTotalPrice());
        }
        
        printLine(row++);
        
        //subtotal, discount, vat, total price
        addBasicLine(row++, "SubTotal", order.getSubTotal(), false);

        double discount = order.getDiscount();
        if (discount > 0) {
            addBasicLine(row++, "Discount", -discount, false);
        }

        addBasicLine(row++, "VAT", order.getVat(), false);
        printLine(row++);
        
        addBasicLine(row++, "Total Price", order.getTotalPrice(), true);

        reservedSize = new Dimension(280, row * 20 + 20);
        setPreferredSize(reservedSize);
        revalidate();
        repaint();
    }
    
    private void printLine(int row) {
        addLine(row, "------------------------------");
    }

    private void addLine(int row, String text) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.WEST;

        JLabel label = new JLabel(text);
        label.setFont(RECEIPT_FONT);
        add(label, constraints);
    }

    private void addItemLine(int row, String name, int amount, double price) {
        GridBagConstraints nameConstraints = new GridBagConstraints();
        nameConstraints.gridx = 0;
        nameConstraints.gridy = row;
        nameConstraints.anchor = GridBagConstraints.WEST;
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(RECEIPT_FONT);
        nameLabel.setPreferredSize(new Dimension(NAME_COLUMN_WIDTH, nameLabel.getPreferredSize().height));
        add(nameLabel, nameConstraints);

        GridBagConstraints amountConstraints = new GridBagConstraints();
        amountConstraints.gridx = 1;
        amountConstraints.gridy = row;
        amountConstraints.anchor = GridBagConstraints.WEST;
        JLabel amountLabel = new JLabel("x" + amount);
        amountLabel.setFont(RECEIPT_FONT);
        amountLabel.setPreferredSize(new Dimension(AMOUNT_COLUMN_WIDTH, amountLabel.getPreferredSize().height));
        add(amountLabel, amountConstraints);

        GridBagConstraints priceConstraints = new GridBagConstraints();
        priceConstraints.gridx = 2;
        priceConstraints.gridy = row;
        priceConstraints.anchor = GridBagConstraints.EAST;
        JLabel priceLabel = new JLabel(String.format(Locale.ENGLISH, "%.2f SEK", price));
        priceLabel.setFont(RECEIPT_FONT);
        add(priceLabel, priceConstraints);
    }

    private void addBasicLine(int row, String name, double price, boolean isTotal) {
        GridBagConstraints nameConstraints = new GridBagConstraints();
        nameConstraints.gridx = 0;
        nameConstraints.gridy = row;
        nameConstraints.anchor = GridBagConstraints.WEST;
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(isTotal ? TOTAL_FONT : RECEIPT_FONT);
        nameLabel.setPreferredSize(new Dimension(NAME_COLUMN_WIDTH, nameLabel.getPreferredSize().height));
        add(nameLabel, nameConstraints);

        GridBagConstraints amountConstraints = new GridBagConstraints();
        amountConstraints.gridx = 1;
        amountConstraints.gridy = row;
        amountConstraints.anchor = GridBagConstraints.WEST;
        JLabel amountLabel = new JLabel(":" );
        amountLabel.setFont(isTotal ? TOTAL_FONT : RECEIPT_FONT);
        amountLabel.setPreferredSize(new Dimension(SMALL_COLUMN_WIDTH, amountLabel.getPreferredSize().height));
        add(amountLabel, amountConstraints);

        GridBagConstraints priceConstraints = new GridBagConstraints();
        priceConstraints.gridx = 2;
        priceConstraints.gridy = row;
        priceConstraints.anchor = GridBagConstraints.EAST;
        JLabel priceLabel = new JLabel(String.format(Locale.ENGLISH, "%.2f SEK", price));
        priceLabel.setFont(isTotal ? TOTAL_FONT : RECEIPT_FONT);
        add(priceLabel, priceConstraints);
    }

    @Override
    public Dimension getMaximumSize() {
        return reservedSize;
    }
}
