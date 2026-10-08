package se.lexicon;

import java.util.List;

public class CafeApp {

    void main() {
        Printer printer = new Printer();
        Menu menu = new Menu();

        //***** Get username ***** 
/*        String userName = IO.readln(printer.askForUserName());
        //if no username -> ask again
        while (StringValidation.IsEmptyString(userName)) {
            userName = IO.readln(printer.askForUserName());
        }*/

        String userName = "Isa"; //TODO:: REMOVE; SINCE THIS IS JUST FOR TESTING 

        //*****  Print greeting and menu   ***** 
        printer.greeting(userName);
        printer.printMenu(menu.getMenuItems());

        //***** get order from user  ***** 
        int menuNumber = 0;
        boolean validInput = false;

        while (!validInput) {
            String userInput = IO.readln(printer.getEnterItemNumbers());

            try {
                menuNumber = Integer.parseInt(userInput);
                validInput = menu.isCorrectMenuNumber(menuNumber);
            } catch (NumberFormatException e) {
                IO.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
    
    
    
   
  
