import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartFrame extends JFrame {
    // constructor for cart frame
    public CartFrame(BakerySystem bakerySystem, JFrame previousFrame) {
        setTitle("Cart");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // set background image
        String imagePath = "/C:/Users/My PC/IdeaProjects/BakeryTPS/src/bg2IMG.jpg/";
        BackgroundPanel backgroundPanel = new BackgroundPanel(imagePath);
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        JTextArea cartDisplay = new JTextArea();  // create text area to display cart items
        cartDisplay.setEditable(false);  // set text area to be non-editable

        StringBuilder cartContent = new StringBuilder("Cart:\n");  // create string builder for cart content
        double total = 0;  // initialize total price

        // loop through cart items and add them to the cart content
        for (int i = 0; i < bakerySystem.getCart().size(); i++) {
            BakerySystem.Product product = bakerySystem.getCart().get(i);
            cartContent.append(i + 1).append(". ").append(product.getName()).append(" - ₱").append(product.getPrice()).append("\n");
            total += product.getPrice();
        }
        cartContent.append("\nTotal: ₱").append(total);  // add total price to cart content

        cartDisplay.setText(cartContent.toString());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 10, 10);
        backgroundPanel.add(new JScrollPane(cartDisplay), constraints);

        JPanel buttonPanel = new JPanel();  // create panel for buttons
        buttonPanel.setLayout(new GridBagLayout());  // use grid bag layout for button panel

        // create clear cart button
        JButton clearCartButton = new JButton("Clear Cart");
        clearCartButton.setPreferredSize(new Dimension(150, 40));
        clearCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakerySystem.getCart().clear();  // clear the cart
                cartDisplay.setText("Cart:\n\nTotal: ₱0.00");  // update cart display
            }
        });

        // create remove last item button
        JButton removeItemButton = new JButton("Remove Last Item");
        removeItemButton.setPreferredSize(new Dimension(150, 40));
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!bakerySystem.getCart().isEmpty()) {
                    bakerySystem.getCart().remove(bakerySystem.getCart().size() - 1);  // remove last item from cart
                    double newTotal = 0;
                    StringBuilder newCartContent = new StringBuilder("Cart:\n");  // create new cart content
                    for (int i = 0; i < bakerySystem.getCart().size(); i++) {
                        BakerySystem.Product product = bakerySystem.getCart().get(i);
                        newCartContent.append(i + 1).append(". ").append(product.getName()).append(" - ₱").append(product.getPrice()).append("\n");
                        newTotal += product.getPrice();
                    }
                    newCartContent.append("\nTotal: ₱").append(newTotal);  // add new total price
                    cartDisplay.setText(newCartContent.toString());  // update cart display
                }
            }
        });

        // create back button
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousFrame.setVisible(true);  // make previous frame visible
                dispose();  // close current frame
            }
        });

        // create checkout button
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setPreferredSize(new Dimension(150, 40));
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakerySystem.createCheckoutFrame();  // open checkout frame
                dispose();  // close current frame
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();  // create grid bag constraints for buttons
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(clearCartButton, gbc);  // add clear cart button to button panel with constraints

        gbc.gridx++;
        buttonPanel.add(removeItemButton, gbc);  // add remove last item button to button panel with constraints

        gbc.gridx++;  // move to next column
        buttonPanel.add(backButton, gbc);  // add back button to button panel with constraints

        gbc.gridx++;  // move to next column
        buttonPanel.add(checkoutButton, gbc);  // add checkout button to button panel with constraints

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        backgroundPanel.add(buttonPanel, constraints);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
