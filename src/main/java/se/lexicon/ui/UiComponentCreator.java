package se.lexicon.ui;

import javax.swing.*;
import java.awt.*;

public class UiComponentCreator {

    public JLabel createTitleLabel(){
        return UiComponentCreator.createPanelWithLabel(
                "Lexicon Cafe", SwingConstants.CENTER, Font.BOLD, 20f
        );
    }
    
    public JLabel createGreetingLabel(){
        return UiComponentCreator.createPanelWithLabel(
                "Welcome to Lexicon Cafe! Please select an item from the menu below.",
                SwingConstants.CENTER,  Font.PLAIN, 14f
        );
    }
    
    public JLabel createMenuLabel(){
        return UiComponentCreator.createPanelWithLabel(
                "Menu", SwingConstants.CENTER,  Font.BOLD, 16f
        );
    }
    
    
    
    public static JLabel createPanelWithLabel(String labelText, int alignment, int font, float fontSize) {

       return createPanelWithLabel(labelText, alignment, font, fontSize, 10, 0, 0, 0, Component.CENTER_ALIGNMENT);
    }
    
    public static JLabel createPanelWithLabel(String labelText, int alignment, int font, float fontSize
    , int topPadding, int leftPadding, int bottomPadding, int rightPadding,  float alignmentX) {
     
        JLabel label = new JLabel(labelText, alignment);
        label.setFont(label.getFont().deriveFont(font, fontSize));
        label.setBorder(BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
        label.setAlignmentX(alignmentX);
        return label;
    }
}
