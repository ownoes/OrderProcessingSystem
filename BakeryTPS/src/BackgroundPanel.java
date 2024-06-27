import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    // constructor that takes a file path to load the background image
    public BackgroundPanel(String filePath) {
        loadImage(filePath);
    }

    // loads the image from the file path
    private void loadImage(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) { // checks if the file exists
            System.err.println("File does not exist: " + filePath);
            return;
        }
        if (!file.canRead()) { // checks if the file can be read
            System.err.println("File cannot be read: " + filePath);
            return;
        }
        try {
            backgroundImage = ImageIO.read(file); // tries to read the image file
            if (backgroundImage == null) { // checks if the image was successfully loaded
                System.err.println("Failed to load image: " + filePath);
            }
        } catch (IOException e) { // handle any IO exceptions
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // draws the background image to fill the panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            System.err.println("Background image is null."); // prints an error if image is not loaded
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (backgroundImage != null) {
            // returns the size of the image if loaded
            return new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this));
        } else {
            // returns a default size if the image is not loaded
            return new Dimension(800, 600);
        }
    }
}
