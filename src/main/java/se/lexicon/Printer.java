package se.lexicon;

import java.util.List;
import java.util.Locale;

public class Printer {
    
    public void printMenu(List<MenuItem> menuItems){
        printCafeTitle();
        printOrderList(menuItems);
    }

    public void greeting(String userName) {
        IO.println("Hi " + userName + "! Here is our menu: \n");
    }
    
    public void printCafeTitle(){
        printLine();
        IO.println("\t\t Lexicon Cafe");
        printLine();
    }
    
    public void printOrderSummary(String userName, MenuItem menuItem, int orderAmount, double SubTotal, double totalPrice, double Discount, double VAT){
        printLine();
        IO.println(String.format("%-15s : %s", "Customer", userName));
        IO.println(String.format("%-15s : %s x %d", "Order", menuItem.getName(), orderAmount));;
        System.out.printf(Locale.ENGLISH,   "%-15s : %.2f SEK%n", "SubTotal", SubTotal);
        System.out.printf(Locale.ENGLISH,   "%-15s : %.2f SEK%n", "Discount", Discount);
        System.out.printf(Locale.ENGLISH,   "%-15s : %.2f SEK%n", "VAT", VAT);
        IO.println("------------------------------");
        System.out.printf(Locale.ENGLISH,   "%-15s : %.2f SEK%n", "Total Price", totalPrice);
        printLine();
    }
    
    public void printGoodBye(String userName) {
        printLine();
        IO.println("Thank you, " + userName + "!\n" +
                "Have a great day and see you next time.!");
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

}
