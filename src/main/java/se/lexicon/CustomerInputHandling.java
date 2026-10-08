package se.lexicon;

public class CustomerInputHandling {


    public boolean getLoyaltyMemberStatusFromUser() {
        String loyaltyMemberQuestion = "Are you a loyalty member? (yes/no): ";
        String loyaltyMemberInput = IO.readln(loyaltyMemberQuestion);

        while (!loyaltyMemberInput.equalsIgnoreCase("yes") && !loyaltyMemberInput.equalsIgnoreCase("no")) {
            IO.println("Invalid input. Please enter 'yes' or 'no'.");
            loyaltyMemberInput = IO.readln(loyaltyMemberQuestion);
        }

        boolean isLoyaltyMember = loyaltyMemberInput.equalsIgnoreCase("yes");
        return isLoyaltyMember;
    }


    public String getCustomerNameFromUser(){
        String userNameQuestion = "Welcome to Lexicon Cafe! What is your name?";
        String userName = IO.readln(userNameQuestion);
        //if no name -> ask again
        while (CustomerInputHandling.IsEmptyString(userName)) {
            userName = IO.readln(userNameQuestion);
        }
        return userName;
    }

    public String getNewCustomerName(){
        String nextCustomerQuestion = "Next customer name (or 'done' to close): ";
        String userName = IO.readln(nextCustomerQuestion);
        //if no name -> ask again
        while (CustomerInputHandling.IsEmptyString(userName)) {
            userName = IO.readln(nextCustomerQuestion);
        }
        return userName;
    }

    public MenuItem getMenuItemFromUser(Menu menu){
        int menuNumber = 0;
        boolean validInput = false;

        while (!validInput) {
            String userInput = IO.readln("Enter item number (1-" + menu.getMenuItems().size() + "), or 0 to finish: ");

            try {
                menuNumber = Integer.parseInt(userInput);
                if ( menuNumber == 0) {
                    return null; // User chose to finish ordering
                }
                validInput = menu.isCorrectMenuNumber(menuNumber);
            } catch (NumberFormatException e) {
                IO.println("Invalid input. Please enter a valid number.");
            }
        }
        return menu.getMenuItem(menuNumber);
    }

    public int getOrderAmountFromUser() {
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
    
    public static boolean IsEmptyString(String inputString) {
        boolean isEmpty = inputString == null || inputString.trim().isEmpty();
        if (isEmpty){
            IO.println("Empty input!");
        }
        return isEmpty;
    }
    
}
