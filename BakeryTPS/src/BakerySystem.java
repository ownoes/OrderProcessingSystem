import javax.swing.*;
import java.util.ArrayList;

public class BakerySystem {
    private JFrame mainFrame;
    private ArrayList<Product> cart;
    private String orderType;


    public static void main(String[] args) {
        new BakerySystem().createSplashScreen();
    }

    // initializes the cart
    public BakerySystem() {
        cart = new ArrayList<>();
    }

    // creates and display the splash screen
    public void createSplashScreen() {
        new SplashScreen(this);
    }

    // creates and display the main frame
    public void createMainFrame() {
        mainFrame = new MainFrame(this);
    }

    // creates and display the product type selection frame
    public void createProductTypeFrame() {
        new ProductTypeFrame(this, orderType);
        mainFrame.dispose();  // Close the main frame
    }

    // creates and display the product selection frame for a specific product type
    public void createProductSelectionFrame(String productType) {
        new ProductSelectionFrame(this, productType);
    }

    // creates and display the cart frame
    public void createCartFrame(JFrame previousFrame) {
        new CartFrame(this, previousFrame);
    }

    // creates and display the checkout frame
    public void createCheckoutFrame() {
        new CheckoutFrame(this);
    }

    // getter method to retrieve the cart
    public ArrayList<Product> getCart() {
        return cart;
    }

    // setter method to set the order type
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    // getter method to retrieve the order type
    public String getOrderType() {
        return orderType;
    }

    // inner class to represent each product
    public class Product {
        private String name;  // Name of the product
        private double price;  // Price of the product

        // initializes the product with name and price
        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        // getter method to retrieve the product name
        public String getName() {
            return name;
        }

        // getter method to retrieve the product price
        public double getPrice() {
            return price;
        }
    }
}
