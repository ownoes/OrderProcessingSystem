import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// creates the order type screen
public class MainFrame extends JFrame {
    public MainFrame(BakerySystem bakerySystem) {
        setTitle("New Order");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // shows the background

        String imagePath = "/C:/Users/My PC/IdeaProjects/BakeryTPS/src/bgIMG.jpg/";
        BackgroundPanel backgroundPanel = new BackgroundPanel(imagePath);
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // dine in button
        ImageIcon dineInIcon = new ImageIcon("/C:/Users/My PC/IdeaProjects/BakeryTPS/src/Button.png/");
        ImageButton dineInButton = new ImageButton("Dine In", dineInIcon);
        dineInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Dine In button clicked");
                bakerySystem.setOrderType("Dine In");
                bakerySystem.createProductTypeFrame();
                dispose();
            }
        });
        backgroundPanel.add(dineInButton, gbc);

        gbc.gridy++;

        // take out button
        ImageIcon takeOutIcon = new ImageIcon("/C:/Users/My PC/IdeaProjects/BakeryTPS/src/Button.png/");
        ImageButton takeOutButton = new ImageButton("Take Out", takeOutIcon);
        takeOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Take Out button clicked");
                bakerySystem.setOrderType("Take Out");
                bakerySystem.createProductTypeFrame();
                dispose();
            }
        });
        backgroundPanel.add(takeOutButton, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        // assuming BakerySystem is properly defined
        BakerySystem bakerySystem = new BakerySystem();
        new MainFrame(bakerySystem);
    }
}
