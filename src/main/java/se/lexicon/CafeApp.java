package se.lexicon;

public class CafeApp {

    void main(String[] args) {
        Printer printer = new Printer();
        Menu menu = new Menu();
        CustomerInputHandling customerInputHandling = new CustomerInputHandling();

        int customerCount = 0;
        double totalRevenue = 0.0;

        Order order = new Order(customerInputHandling.getCustomerNameFromUser());

        while (!order.customerName.equals("done")) {
            customerCount++;

            //*****  Print greeting and menu   ***** 
            printer.greeting(order.getCustomerName());
            printer.printMenu(menu.getMenuItems());

            //***** get order from user  ***** 
            order.addItem(new LineItem(customerInputHandling.getMenuItemFromUser(menu), customerInputHandling.getOrderAmountFromUser()));

            //*****  Ask for loyalty Member  *****
            order.setLoyaltyMember(customerInputHandling.getLoyaltyMemberStatusFromUser());
            
            //*****  Calculate total price *****
            double orderPrice = order.getTotalPriceAndPrintOrder(printer);
            totalRevenue += orderPrice;
          
            //***** print goodbye message  *****
            printer.printGoodBye(order.getCustomerName());
            
            IO.println("");

            //***** check for next customer  *****
            order = new Order(customerInputHandling.getNewCustomerName());
        }
        
        //***** print total revenue and customer count  *****
        printer.printEndOfDayReport(customerCount, totalRevenue);
    }


}
    
    
    
   
  
