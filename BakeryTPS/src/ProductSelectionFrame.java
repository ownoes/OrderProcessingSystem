import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductSelectionFrame extends JFrame {
    // constructor for product selection frame
    public ProductSelectionFrame(BakerySystem bakerySystem, String productType) {
        setTitle(productType);  // set the title of the frame
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // maximize the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // set default close operation

        // set the background image path
        String imagePath = "/C:/Users/My PC/IdeaProjects/BakeryTPS/src/bgIMG.jpg/";
        BackgroundPanel backgroundPanel = new BackgroundPanel(imagePath);  // create a background panel
        setContentPane(backgroundPanel);  // set the background panel as the content pane
        backgroundPanel.setLayout(new GridBagLayout());  // use grid bag layout for the background panel

        GridBagConstraints gbc = new GridBagConstraints();  // create grid bag constraints
        gbc.insets = new Insets(10, 10, 10, 10);  // set insets for padding
        gbc.gridx = 0;  // start at column 0
        gbc.gridy = 0;  // start at row 0

        // initialize product names and prices arrays
        String[] productNames;
        double[] productPrices;

        // switch case to determine the product type and set corresponding product names and prices
        switch (productType) {
            case "Sandwich":
                productNames = new String[]{"Tuna Sandwich", "Chicken Sandwich", "Cheese Sandwich", "Egg Sandwich", "Ham & Cheese", "Double Decker", "Club House", "Club Sandwich", "BLT Sandwich"};
                productPrices = new double[]{80, 80, 70, 70, 80, 80, 100, 120, 80};
                break;
            case "Dessert":
                productNames = new String[]{"Totoro CreamPuff", "Kiki's Cupcake", "Matcha Revelbar", "Chocolate Revelbar", "Strawberry Shortcake", "Apple Pie", "Cinnamon roll", "Krinkles", "Choco Chip Cookies"};
                productPrices = new double[]{100, 80, 75, 75, 90, 90, 80, 30, 30};
                break;
            case "Drinks":
                productNames = new String[]{"Latte", "Cappuccino", "Caramel latte", "Honey Matcha", "Matcha", "Strawberry Matcha", "Matcha Yakult", "Mixed Berry Yakult", "Ube Latte", "Honey Citron", "Hazelnut Mocha", "Matcha Espresso"};
                productPrices = new double[]{80, 80, 90, 95, 120, 150, 130, 120, 120, 100, 130, 130};
                break;
            default:
                productNames = new String[]{};
                productPrices = new double[]{};
        }

        // loop through product names and prices to create buttons
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            double productPrice = productPrices[i];

            // create a button for each product
            JButton productButton = new JButton(productName + " - ₱" + productPrice);
            productButton.setPreferredSize(new Dimension(200, 50));  // set button size
            productButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // add product to cart and show confirmation message
                    bakerySystem.getCart().add(bakerySystem.new Product(productName, productPrice));
                    JOptionPane.showMessageDialog(null, productName + " added to cart.");
                }
            });
            backgroundPanel.add(productButton, gbc);  // add button to background panel with constraints

            // update grid bag constraints for next button
            if (gbc.gridx == 2) {  // to control the number of columns
                gbc.gridx = 0;
                gbc.gridy++;
            } else {
                gbc.gridx++;
            }
        }

        // create a panel to hold the "Back", "View Cart", and "Checkout" buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10);  // set insets for padding

        // create back button
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakerySystem.createProductTypeFrame();  // go back to product type frame
                dispose();  // close current frame
            }
        });
        buttonGbc.gridx = 0;
        buttonPanel.add(backButton, buttonGbc);

        // create view cart button
        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.setPreferredSize(new Dimension(200, 50));
        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakerySystem.createCartFrame(ProductSelectionFrame.this);  // open cart frame
            }
        });
        buttonGbc.gridx = 1;
        buttonPanel.add(viewCartButton, buttonGbc);

        // create checkout button
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setPreferredSize(new Dimension(200, 50));
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakerySystem.createCheckoutFrame();  // open checkout frame
                dispose();  // close current frame
            }
        });
        buttonGbc.gridx = 2;
        buttonPanel.add(checkoutButton, buttonGbc);

        // add button panel to background panel with updated constraints
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 3;  // span across all columns
        backgroundPanel.add(buttonPanel, gbc);

        setVisible(true);  // make the frame visible
    }
}
