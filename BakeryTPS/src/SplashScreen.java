import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//creates the starting screen
public class SplashScreen extends JFrame {
    public SplashScreen(BakerySystem bakerySystem) {
        setTitle("Welcome");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String imagePath = "/C:/Users/My PC/IdeaProjects/BakeryTPS/src/splash_background_image.jpg/";
        BackgroundPanel backgroundPanel = new BackgroundPanel(imagePath);
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());

        backgroundPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bakerySystem.createMainFrame();
                dispose();
            }
        });

        pack();
        setVisible(true);
    }
}