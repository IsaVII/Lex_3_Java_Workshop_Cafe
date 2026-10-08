package se.lexicon;

public class Order {

    String customerName;
    MenuItem menuItem;
    int orderAmount;
    boolean isLoyaltyMember;
    
    double loyaltyDiscount = 0.15; // 15% discount for loyalty members
    double highValueDiscount = 0.10; // 10% discount for orders over 150 SEK
    double discountThreshold = 150.0; // Threshold for high value discount
     
    double vatRate = 0.12;  //12% 

    public double getTotalPriceAndPrintOrder(Printer printer) {
        double subTotal = menuItem.getPrice() * orderAmount;
        double discount = isLoyaltyMember ? subTotal * 0.15 : subTotal > 150 ? subTotal * 0.10 : 0;
        double vat = (subTotal - discount) * 0.12;
        double totalPrice = subTotal - discount + vat;

        //***** print receipt  *****
        printer.printOrderSummary(customerName, menuItem, orderAmount, subTotal, totalPrice, discount, vat);

        return totalPrice;
    }
    
    public Order(String userName) {
        this.customerName = userName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public boolean isLoyaltyMember() {
        return isLoyaltyMember;
    }

    public void setLoyaltyMember(boolean loyaltyMember) {
        isLoyaltyMember = loyaltyMember;
    }

    public double getLoyaltyDiscount() {
        return loyaltyDiscount;
    }

    public void setLoyaltyDiscount(double loyaltyDiscount) {
        this.loyaltyDiscount = loyaltyDiscount;
    }

    public double getHighValueDiscount() {
        return highValueDiscount;
    }

    public void setHighValueDiscount(double highValueDiscount) {
        this.highValueDiscount = highValueDiscount;
    }

    public double getDiscountThreshold() {
        return discountThreshold;
    }

    public void setDiscountThreshold(double discountThreshold) {
        this.discountThreshold = discountThreshold;
    }

    public double getVatRate() {
        return vatRate;
    }

    public void setVatRate(double vatRate) {
        this.vatRate = vatRate;
    }


}
