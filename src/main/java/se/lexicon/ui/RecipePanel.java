package se.lexicon.ui;

import javax.swing.*;

public class RecipePanel  extends JPanel {
    private static final int NAME_COLUMN_WIDTH = 150;

    public RecipePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void addRecipeLine(String recipeName, String recipeDescription) {
        JLabel nameLabel = new JLabel(recipeName);
        nameLabel.setPreferredSize(new java.awt.Dimension(NAME_COLUMN_WIDTH, nameLabel.getPreferredSize().height));
        add(nameLabel);

        JLabel descriptionLabel = new JLabel(recipeDescription);
        add(descriptionLabel);

        revalidate();
        repaint();
    }   
    
}
