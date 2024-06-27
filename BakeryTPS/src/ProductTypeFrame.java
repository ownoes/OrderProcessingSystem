import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// main class for the product type frame
public class ProductTypeFrame extends JFrame {

    // constructor for the product type frame
    public ProductTypeFrame(BakerySystem bakerySystem, String orderType) {
        setTitle(orderType + " - Select from Menu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // set the background image
        String imagePath = "/C:/Users/My PC/IdeaProjects/BakeryTPS/src/bgIMG.jpg/";
        BackgroundPanel backgroundPanel = new BackgroundPanel(imagePath);
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new GridBagLayout());

        // set the icon for the buttons
        ImageIcon buttonIcon = new ImageIcon("/C:/Users/My PC/IdeaProjects/BakeryTPS/src/Button.png/");

        // create and configure the sandwich button
        ImageButton sandwichButton = new ImageButton("Sandwich", buttonIcon);
        sandwichButton.setPreferredSize(new Dimension(200, 50));
        sandwichButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakerySystem.createProductSelectionFrame("Sandwich");
                dispose(); // close the current frame
            }
        });
        // adds a button to the background panel
        backgroundPanel.add(sandwichButton, gbc);

        // create and configure the dessert button
        gbc.gridy++;
        ImageButton dessertButton = new ImageButton("Dessert", buttonIcon);
        dessertButton.setPreferredSize(new Dimension(200, 50));
        dessertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakerySystem.createProductSelectionFrame("Dessert");
                dispose(); // close the current frame
            }
        });
        // adds button to the background panel
        backgroundPanel.add(dessertButton, gbc);

        // create and configure the drinks button
        gbc.gridy++;
        ImageButton drinksButton = new ImageButton("Drinks", buttonIcon);
        drinksButton.setPreferredSize(new Dimension(200, 50));
        drinksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakerySystem.createProductSelectionFrame("Drinks");
                dispose();
            }
        });
        backgroundPanel.add(drinksButton, gbc); // add button to the background panel

        // create and configure the view cart button
        gbc.gridy++;
        ImageButton viewCartButton = new ImageButton("View Cart", buttonIcon);
        viewCartButton.setPreferredSize(new Dimension(200, 50));
        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bakerySystem.createCartFrame(ProductTypeFrame.this); // open the cart frame
            }
        });
        backgroundPanel.add(viewCartButton, gbc); // add button to the background panel

        // make the frame visible
        setVisible(true);
    }
}
