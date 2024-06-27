import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CheckoutFrame extends JFrame {
    private static final AtomicInteger orderCounter = new AtomicInteger(1);  // static order counter

    // constructor for checkout frame
    public CheckoutFrame(BakerySystem bakerySystem) {
        setTitle("Checkout");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        JTextArea cartDisplay = new JTextArea();  // create text area to display cart items
        cartDisplay.setEditable(false);  // set text area to be non-editable
        cartDisplay.setFont(new Font("Monospaced", Font.PLAIN, 14));

        int orderNumber = orderCounter.getAndIncrement();
        StringBuilder cartContent = new StringBuilder();
        String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());  // get current date and time

        // receipt format
        cartContent.append("===========================================================\n");
        cartContent.append("                         CAKE GALERIE\n");
        cartContent.append("         1st Av, Tulip Dr, Talomo, Davao del Sur, Philippines\n");
        cartContent.append(String.format("                       ORDER NO.: %d\n", orderNumber));
        cartContent.append(String.format("                    DATE & TIME: %s\n", dateTime));
        cartContent.append("===========================================================\n");
        cartContent.append(String.format("%-35s %10s %10s\n", "Description", "Quantity", "Price"));
        cartContent.append("-----------------------------------------------------------\n");

        double total = 0;  // initialize total price
        Map<String, Integer> productQuantities = new HashMap<>();  // create map to store product quantities

        // count quantities of each product
        for (BakerySystem.Product product : bakerySystem.getCart()) {
            productQuantities.put(product.getName(), productQuantities.getOrDefault(product.getName(), 0) + 1);
        }

        // add product details to cart content
        for (Map.Entry<String, Integer> entry : productQuantities.entrySet()) {
            String productName = entry.getKey();
            int quantity = entry.getValue();
            double price = bakerySystem.getCart().stream()
                    .filter(p -> p.getName().equals(productName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product not found in cart"))
                    .getPrice();
            cartContent.append(String.format("%-35s %10d %10s\n", productName, quantity, "₱" + String.format("%.2f", price * quantity)));
            total += price * quantity;
        }

        double takeOutFee = 0;  // initialize take-out fee
        if ("Take Out".equals(bakerySystem.getOrderType())) {
            takeOutFee = 10.00;  // set take-out fee
            cartContent.append(String.format("%-35s %10d %10s\n", "Take-out Fee:", 1, "₱" + String.format("%.2f", takeOutFee)));
        }

        total += takeOutFee;  // add take-out fee to total

        // add total price and footer to cart content
        cartContent.append("-----------------------------------------------------------\n");
        cartContent.append(String.format("%-35s %10s %10s\n", "Total", "", "₱" + String.format("%.2f", total)));
        cartContent.append("===========================================================\n");
        cartContent.append("                          THANK YOU!\n");
        cartContent.append("               Please come again and enjoy our treats!\n");
        cartContent.append("      for inquiries contact us @ homeavenue.cakegaleria@gmail.com \n");
        cartContent.append("===========================================================\n");

        cartDisplay.setText(cartContent.toString());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 10, 10);
        add(new JScrollPane(cartDisplay), constraints);

        JPanel buttonPanel = new JPanel();  // create panel for buttons
        buttonPanel.setLayout(new GridBagLayout());

        // create back button
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.addActionListener(e -> dispose());  // close frame on button click

        GridBagConstraints gbc = new GridBagConstraints();  // create grid bag constraints for buttons
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(backButton, gbc);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        add(buttonPanel, constraints);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
