package se.lexicon;

import java.util.List;

public class CafeApp {

    void main(String[] args) {
        Printer printer = new Printer();
        Menu menu = new Menu();

        //***** Get username ***** 
        String userNameQuestion = "Welcome to Lexicon Cafe! What is your name?";
/*        String userName = IO.readln(userNameQuestion);
        //if no username -> ask again
        while (StringValidation.IsEmptyString(userName)) {
            userName = IO.readln(userNameQuestion);
        }*/

        String userName = "Isa"; //TODO:: REMOVE; SINCE THIS IS JUST FOR TESTING 

        //*****  Print greeting and menu   ***** 
        printer.greeting(userName);
        printer.printMenu(menu.getMenuItems());

        //***** get order from user  ***** 
        int menuNumber = 0;
        boolean validInput = false;

        while (!validInput) {
            String userInput = IO.readln("Enter item number (1-"+ menu.getMenuItems().size() + "): "); 

            try {
                menuNumber = Integer.parseInt(userInput);
                validInput = menu.isCorrectMenuNumber(menuNumber);
            } catch (NumberFormatException e) {
                IO.println("Invalid input. Please enter a valid number.");
            }
        }

        //***** get the amount ordered from user ***** 
        int orderAmount = 0;
        validInput = false;
        
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
        
        //*****  Ask for loyalty Member  *****
        String loyaltyMemberQuestion = "Are you a loyalty member? (yes/no): ";
        String loyaltyMemberInput = IO.readln(loyaltyMemberQuestion);
        
        if (!loyaltyMemberInput.equals("yes") && !loyaltyMemberInput.equals("no")) {
            IO.println("Invalid input. Please enter 'yes' or 'no'.");
            loyaltyMemberInput = IO.readln(loyaltyMemberQuestion);
        }
        
        boolean isLoyaltyMember = loyaltyMemberInput.equalsIgnoreCase("yes");
        
        //*****  Calculate total price and print receipt  *****
         
        
    }
}
    
    
    
   
  
