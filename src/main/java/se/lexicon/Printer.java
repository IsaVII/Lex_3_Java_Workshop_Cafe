package se.lexicon;

import java.util.List;
import java.util.Locale;

public class Printer {
    
    private String askForUserName = "Welcome to Lexicon Cafe! What is your name?";
    private String enterItemNumbers = "Enter item number (1-5):";
    
    
    public void printMenu(List<MenuItem> menuItems){
        printCafeTitle();
        printOrderList(menuItems);
    }

    public void greeting(String userName) {
        IO.println("Hi " + userName + "! Here is our menu: \n");
    }
    

    
    private void printCafeTitle(){
        printLine();
        IO.println("\t\t Lexicon Cafe");
        printLine();
    }

    private void printLine(){
        IO.println("==============================");
    }

    private void printOrderList(List<MenuItem> menuItems){
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem menuItem = menuItems.get(i);
            //Locale.ENGLISH, because otherwise it would be "," instead of "." for the decimal format
            System.out.printf(Locale.ENGLISH,   "%d.\t%-15s %.2f SEK%n", i + 1, menuItem.getName(), menuItem.getPrice());
        }
        printLine();
    }

    public String getEnterItemNumbers() {
        return enterItemNumbers;
    }

    public String askForUserName() {
        return askForUserName;
    }
}
