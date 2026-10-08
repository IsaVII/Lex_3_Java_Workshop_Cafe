package se.lexicon;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Printer {

    public void printMenu(List<MenuItem> menuItems) {
        printCafeTitle();
        printOrderList(menuItems);
        IO.println("");
    }

    public void greeting(String userName) {
        IO.println("Hi " + userName + "! Here is our menu: \n");
    }

    public void printCafeTitle() {
        printLine();
        IO.println("\t\t Lexicon Cafe");
        printLine();
    }

    public void printOrderSummary(String userName, ArrayList<LineItem> items, int orderAmount, double SubTotal, double totalPrice, double Discount, double VAT) {
        IO.println("");
        printCafeTitle();
        IO.println(String.format("%-12s : %s", "Customer", userName));
        
        //Items
        printBasicLine();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            lineItem.printLineItem();
        }

        printBasicLine();
        IO.println(String.format(Locale.ENGLISH, "%-12s : %.2f SEK", "SubTotal", SubTotal));

        if (Discount > 0) {
            IO.println(String.format(Locale.ENGLISH, "%-12s : -%.2f SEK", "Discount",  Discount));
        }
        
        IO.println(String.format(Locale.ENGLISH, "%-12s : %.2f SEK", "VAT", VAT));
        printBasicLine();
        IO.println(String.format(Locale.ENGLISH, "%-12s : %.2f SEK", "Total Price", totalPrice));
    }

    public void printGoodBye(String userName) {
        printLine();
        IO.println("\tThank you, " + userName + "!\n" +
                "\tSee you next time!");
        printLine();
    }

    public void printEndOfDayReport(int customerCount, double totalRevenue) {
        printLine();
        IO.println("\tEnd of Day Report");
        printLine();
        
        IO.println(String.format("%-20s : %d", "Customers served", customerCount));
        IO.println(String.format(Locale.ENGLISH, "%-20s : %.2f SEK", "Total revenue", totalRevenue));
       
        printLine();
    }


    private void printOrderList(List<MenuItem> menuItems) {
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem menuItem = menuItems.get(i);
            //Locale.ENGLISH, because otherwise it would be "," instead of "." for the decimal format
            IO.println(String.format(Locale.ENGLISH, "%d.\t%-15s %.2f SEK", i + 1, menuItem.getName(), menuItem.getPrice()));
        }
        printLine();
    }
    
    private void printLine() {
        IO.println("==============================");
    }
    
    private void printBasicLine() {
        IO.println("------------------------------");
    }

  
}
