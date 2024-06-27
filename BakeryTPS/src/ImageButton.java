import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

// Custom JButton class with an image background
public class ImageButton extends JButton {
    private BufferedImage image;  // BufferedImage to hold the icon image

    // Constructor to initialize the button with text and icon
    public ImageButton(String text, ImageIcon icon) {
        super(text, icon);
        // Create a BufferedImage from the icon
        this.image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = this.image.getGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();

        // Set button properties to make it transparent
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        // Center the text within the button
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
    }

    // Override the paintComponent method to draw the image
    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this); // Draw the image scaled to the button size
        }
        super.paintComponent(g); // Call the superclass's paintComponent method
    }

    // Override the getPreferredSize method to return the size of the image
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }

    // Override the contains method to check if a point is within the non-transparent part of the image
    @Override
    public boolean contains(int x, int y) {
        if (x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()) {
            return false; // Check if coordinates are outside image bounds
        }
        int alpha = (image.getRGB(x, y) >> 24) & 0xff;
        return alpha != 0; // Check if the pixel is not transparent
    }
}
