package se.lexicon.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class DayReportPanel extends JPanel {

    private static final Font REPORT_FONT = new Font(Font.DIALOG, Font.PLAIN, 13);

    public DayReportPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
    }

    public void refresh(OrderList orderList) {
        removeAll();

        addLine(0, String.format("%-20s : %d", "Customers served", orderList.getOrderCount()));
        addLine(1, String.format(Locale.ENGLISH, "%-20s : %.2f SEK", "Total revenue", orderList.getTotalRevenue()));

        revalidate();
        repaint();
    }

    private void addLine(int row, String text) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = row;
        constraints.anchor = GridBagConstraints.WEST;

        JLabel label = new JLabel(text);
        label.setFont(REPORT_FONT);
        add(label, constraints);
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
}
