package se.lexicon;

import java.util.ArrayList;
import java.util.List;

public class Order {

    String customerName;
    ArrayList<LineItem> lineItems = new ArrayList<>();
    boolean isLoyaltyMember;

    double loyaltyDiscount = 0.15; // 15% discount for loyalty members
    double highValueDiscount = 0.10; // 10% discount for orders over 150 SEK
    double discountThreshold = 150.0; // Threshold for high value discount

    double vatRate = 0.12;  //12% 

    public double getTotalPriceAndPrintOrder(Printer printer) {
        double subTotal = getSubTotal();
        double discount = getDiscount();
        double vat = getVat();
        double totalPrice = getTotalPrice();

        //***** print receipt  *****
        printer.printOrderSummary(customerName, lineItems, lineItems.size(), subTotal, totalPrice, discount, vat);

        return totalPrice;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public double getSubTotal() {
        double subTotal = 0.0;
        for (LineItem lineItem : lineItems) {
            subTotal += lineItem.getTotalPrice();
        }
        return subTotal;
    }

    public double getDiscount() {
        double subTotal = getSubTotal();
        return isLoyaltyMember ? subTotal * loyaltyDiscount
                : subTotal > discountThreshold ? subTotal * highValueDiscount : 0;
    }

    public double getVat() {
        return (getSubTotal() - getDiscount()) * vatRate;
    }

    public double getTotalPrice() {
        return getSubTotal() - getDiscount() + getVat();
    }




    public Order(String userName) {
        this.customerName = userName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void addItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public void removeItem(LineItem lineItem) {
        lineItems.remove(lineItem);
    }

    public void setLoyaltyMember(boolean loyaltyMember) {
        isLoyaltyMember = loyaltyMember;
    }
}
