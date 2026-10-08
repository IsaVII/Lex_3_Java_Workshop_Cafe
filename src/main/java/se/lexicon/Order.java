package se.lexicon;

import java.util.ArrayList;

public class Order {

    String customerName;
    ArrayList<LineItem> lineItems = new ArrayList<>();
    boolean isLoyaltyMember;

    double loyaltyDiscount = 0.15; // 15% discount for loyalty members
    double highValueDiscount = 0.10; // 10% discount for orders over 150 SEK
    double discountThreshold = 150.0; // Threshold for high value discount

    double vatRate = 0.12;  //12% 

    public double getTotalPriceAndPrintOrder(Printer printer) {
        double subTotal = 0.0;
        for (LineItem lineItem : lineItems) {
            subTotal += lineItem.getTotalPrice();
        }
        
        double discount = isLoyaltyMember ? subTotal * 0.15 : subTotal > 150 ? subTotal * 0.10 : 0;
        double vat = (subTotal - discount) * 0.12;
        double totalPrice = subTotal - discount + vat;

        //***** print receipt  *****
        printer.printOrderSummary(customerName, lineItems, lineItems.size(), subTotal, totalPrice, discount, vat);

        return totalPrice;
    }


    public Order(String userName) {
        this.customerName = userName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void addItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public void setLoyaltyMember(boolean loyaltyMember) {
        isLoyaltyMember = loyaltyMember;
    }
}
