package se.lexicon;

public class MenuItem
{
    String name = "";
    Double price = 0.0;
    
    public MenuItem(String name, Double price)
    {
        this.name = name;
        this.price = price;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Double getPrice()
    {
        return price;
    }
}
