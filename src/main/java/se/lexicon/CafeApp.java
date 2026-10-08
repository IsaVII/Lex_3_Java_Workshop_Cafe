package se.lexicon;

public class CafeApp {

    void main(String[] args) {
        Printer printer = new Printer();
        Menu menu = new Menu();

        int customerCount = 0;
        double totalRevenue = 0.0;

        Order order = new Order(getCustomerNameFromUser());

        while (!order.customerName.equals("done")) {
            customerCount++;

            //*****  Print greeting and menu   ***** 
            printer.greeting(order.getCustomerName());
            printer.printMenu(menu.getMenuItems());

            //***** get order from user  ***** 
            order.setMenuItem(getMenuItemFromUser(menu));

            //***** get the amount ordered from user ***** 
            order.setOrderAmount(getOrderAmountFromUser());

            //*****  Ask for loyalty Member  *****
            order.setLoyaltyMember(getLoyaltyMemberStatusFromUser());
            
            //*****  Calculate total price *****
            double orderPrice = order.getTotalPriceAndPrintOrder(printer);
            totalRevenue += orderPrice;
          
            //***** print goodbye message  *****
            printer.printGoodBye(order.getCustomerName());
            
            IO.println("");

            //***** check for next customer  *****
            order = new Order(getNewCustomerName());
        }
        
        //***** print total revenue and customer count  *****
        printer.printEndOfDayReport(customerCount, totalRevenue);
    }

    private boolean getLoyaltyMemberStatusFromUser() {
        String loyaltyMemberQuestion = "Are you a loyalty member? (yes/no): ";
        String loyaltyMemberInput = IO.readln(loyaltyMemberQuestion);

        while (!loyaltyMemberInput.equalsIgnoreCase("yes") && !loyaltyMemberInput.equalsIgnoreCase("no")) {
            IO.println("Invalid input. Please enter 'yes' or 'no'.");
            loyaltyMemberInput = IO.readln(loyaltyMemberQuestion);
        }

        boolean isLoyaltyMember = loyaltyMemberInput.equalsIgnoreCase("yes");
        return isLoyaltyMember;
    }


    private String getCustomerNameFromUser(){
        String userNameQuestion = "Welcome to Lexicon Cafe! What is your name?";
        String userName = IO.readln(userNameQuestion);
        //if no name -> ask again
        while (StringValidation.IsEmptyString(userName)) {
            userName = IO.readln(userNameQuestion);
        }
        return userName;
    }
    
    private String getNewCustomerName(){
        String nextCustomerQuestion = "Next customer name (or 'done' to close): ";
        String userName = IO.readln(nextCustomerQuestion);
        //if no name -> ask again
        while (StringValidation.IsEmptyString(userName)) {
            userName = IO.readln(nextCustomerQuestion);
        }
        return userName;
    }
    
    private MenuItem getMenuItemFromUser(Menu menu){
        int menuNumber = 0;
        boolean validInput = false;

        while (!validInput) {
            String userInput = IO.readln("Enter item number (1-" + menu.getMenuItems().size() + "): ");

            try {
                menuNumber = Integer.parseInt(userInput);
                validInput = menu.isCorrectMenuNumber(menuNumber);
            } catch (NumberFormatException e) {
                IO.println("Invalid input. Please enter a valid number.");
            }
        }
        return menu.getMenuItem(menuNumber);
    }

    private int getOrderAmountFromUser() {
        int orderAmount = 0;
        boolean validInput = false;

        while (!validInput) {
            String userInput = IO.readln("How many? ");
            try {
                orderAmount = Integer.parseInt(userInput);
                if (orderAmount > 0) {
                    validInput = true;
                } else {
                    IO.println("Invalid input. Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                IO.println("Invalid input. Please enter a valid number.");
            }
        }
        return orderAmount;
    }
}
    
    
    
   
  
