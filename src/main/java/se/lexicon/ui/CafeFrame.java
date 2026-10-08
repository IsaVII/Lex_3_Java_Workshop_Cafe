package se.lexicon.ui;

import se.lexicon.Menu;
import se.lexicon.Order;

import javax.swing.*;
import java.awt.*;

public class CafeFrame extends JFrame {

    private final UiComponentCreator componentCreator = new UiComponentCreator();
    private final Menu menu = new Menu();
    private final OrderList orderList = new OrderList();

    private final OrderPanel orderPanel;
    private final CustomerNamePanel customerNamePanel;
    private final ReceiptPanel receiptPanel = new ReceiptPanel();
    private final DayReportPanel dayReportPanel = new DayReportPanel();

    private Order order = new Order("Customer");

    public CafeFrame() {
        super("Lexicon Cafe");

        //Main layout - stack content top to bottom
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        //**** PARTS ***
        //Order panel
        orderPanel = new OrderPanel(order, menu.getMenuItems().size());
        orderPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Menu panel
        MenuPanel menuPanel = new MenuPanel(menu.getMenuItems(), orderPanel::addOrderLine);
        menuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.setMaximumSize(menuPanel.getPreferredSize());

        //Receipt panel
        receiptPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderPanel.setOnOrderChanged(() -> receiptPanel.refresh(order));
        receiptPanel.refresh(order);

        //Customer name panel
        customerNamePanel = new CustomerNamePanel(order.getCustomerName());
        customerNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //greeting
        JLabel secondGreetingLabel = componentCreator.createSecondGreetingLabel(order.getCustomerName());

        customerNamePanel.setOnNameChanged(name -> {
            order.setCustomerName(name);
            receiptPanel.refresh(order);
            secondGreetingLabel.setText(UiComponentCreator.secondGreetingText(name));
        });
        
        //place order button
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        placeOrderButton.addActionListener(e -> placeOrder());

        dayReportPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dayReportPanel.refresh(orderList);

        //**** ADD PARTS TO FRAME ****/
        add(componentCreator.createTitleLabel());
        add(componentCreator.createGreetingLabel());
        add(customerNamePanel);
        add(secondGreetingLabel);
        add(componentCreator.createMenuLabel());
        add(menuPanel);
        add(orderPanel);
        add(receiptPanel);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(placeOrderButton);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(dayReportPanel);

        //Default data
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 900);
        setLocationRelativeTo(null);
    }

    private void placeOrder() {
        orderList.addOrder(order);
        dayReportPanel.refresh(orderList);

        order = new Order("Customer");
        orderPanel.resetOrder(order);
        customerNamePanel.setName(order.getCustomerName());
    }


    void main() {

        SwingUtilities.invokeLater(() -> new CafeFrame().setVisible(true));
    }
}
