package se.lexicon.ui;

import se.lexicon.Order;

import java.util.ArrayList;

public class OrderList {
    
    ArrayList<Order> orders = new ArrayList<>();
    
    public void addOrder(Order order) {
        orders.add(order);
    }
    
    public double getTotalRevenue() {
        double totalRevenue = 0.0;
        for (Order order : orders) {
            totalRevenue += order.getTotalPrice();
        }
        return totalRevenue;
    }
    
    public int getOrderCount() {
        return orders.size();
    }
}
