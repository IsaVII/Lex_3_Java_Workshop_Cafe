package se.lexicon;

import java.util.List;
import java.util.jar.JarEntry;

public class Menu {

    List<MenuItem> menuItems =  createMenuItems();

    public Menu() {
        createMenuItems();
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
    
    public boolean isCorrectMenuNumber(int menuNumber){
        int itemListSize = menuItems.size();
        boolean isCorrect = menuNumber > 0 && menuNumber <= itemListSize;
        
        if (!isCorrect){
            IO.println("Invalid menu number. Please enter a number between 1 and " + itemListSize + ".");
        }
        
        return isCorrect;
    }
    
    
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public MenuItem getMenuItem(int number) {
        return menuItems.get(number -1);
    }
}
