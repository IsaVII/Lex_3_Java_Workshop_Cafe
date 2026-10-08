package se.lexicon;

import java.util.List;
import java.util.Locale;

public class Printer {
    
    public void printMenu(List<MenuItem> menuItems){
        printCafeTitle();
        printOrderList(menuItems);
    }
    
    void printCafeTitle(){
        printLine();
        IO.println("\t\t Lexicon Cafe");
        printLine();
    }
    
    void printLine(){
        IO.println("==============================");
    }

    void printOrderList(List<MenuItem> menuItems){
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem menuItem = menuItems.get(i);
            //Locale.ENGLISH, because otherwise it would be "," instead of "." for the decimal format
            System.out.printf(Locale.ENGLISH,   "%d.\t%-15s %.2f SEK%n", i + 1, menuItem.getName(), menuItem.getPrice());
        }
        printLine();
    }
}
