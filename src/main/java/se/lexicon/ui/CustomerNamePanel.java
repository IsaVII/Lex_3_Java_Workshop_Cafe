package se.lexicon.ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.function.Consumer;

public class CustomerNamePanel extends JPanel {

    private final JTextField nameField = new JTextField(20);

    public CustomerNamePanel(String initialName) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        nameField.setText(initialName);

        add(new JLabel("Customer Name:"));
        add(nameField);
    }

    public void setOnNameChanged(Consumer<String> onNameChanged) {
        nameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onNameChanged.accept(nameField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onNameChanged.accept(nameField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onNameChanged.accept(nameField.getText());
            }
        });
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
}
