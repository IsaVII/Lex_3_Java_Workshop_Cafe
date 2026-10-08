package se.lexicon;

import java.util.Locale;
import java.util.Set;

public class LineItem {
    
    MenuItem menuItem;
    int amount;
    double priceTotal;

    public LineItem(MenuItem menuItem, int amount) {
        this.menuItem = menuItem;
        setAmount(amount);
    }
    
    public void printLineItem() {
        IO.println(String.format(Locale.ENGLISH, "\t%-12s x%-3d %.2f SEK", menuItem.name, amount, priceTotal));
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
        priceTotal = menuItem.getPrice() * amount;
    }
    

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getAmount() {
        return amount;
    }

    public double getTotalPrice() {
        return menuItem.getPrice() * amount;
    }
}
