package se.lexicon;

import java.util.List;

public class CafeApp {
    
    void main(){
        Printer printer = new Printer();
        List<MenuItem> menuItems =  createMenuItems();
        
         String userName = IO.readln("Welcome to Lexicon Cafe! What is your name? ");
        // Logic
        printer.greeting(userName);
        
        printer.printMenu(menuItems);
        
    }
    
    
    List<MenuItem> createMenuItems(){
        List<MenuItem> menuItems = List.of(
            new MenuItem("Espresso", 25.00),
            new MenuItem("Cappuccino", 35.00),
            new MenuItem("Latte", 40.00),
            new MenuItem("Croissant", 30.00),
            new MenuItem("Sandwich", 55.00)
        );
        
        return menuItems;
    }
    
   
  
}
