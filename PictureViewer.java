import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class PictureViewer {

    // Constants for window dimensions and minimum picture size
    private static final int WINDOW_HEIGHT = 540;
    private static final int WINDOW_WIDTH = 960;
    private static final int MIN_PIC_WIDTH = 200;
    private static final int MIN_PIC_HEIGHT = 150;

    // Panel to display images
    private static MyPanel thePanel = new MyPanel();
    
    // Variables to store the current image and its name
    private static Image currentImage = null;
    private static String currentImageName = null;

    // Method to show an image
    public static void showImage(String imageName) {
        try {
            // Replace spaces in the image name with "%20" for URL handling
            String picName = imageName.replace(" ", "%20");
            URL imageURL = new URL(picName);
            
            // Read the image from the URL
            BufferedImage temp = ImageIO.read(imageURL);
            
            // Check if the image is not null and meets minimum size requirements
            if (temp != null && temp.getHeight() >= MIN_PIC_HEIGHT && temp.getWidth() >= MIN_PIC_WIDTH) {
                currentImage = temp;
                currentImageName = imageName; // Use the original imageName, not the modified picName
            }
            
            // Repaint the panel to display the new image
            thePanel.repaint();
        } catch (Exception e) {
            // If any exception occurs, print an error message and stack trace
            System.out.println("Error displaying image: " + imageName);
            e.printStackTrace();
        }
    }

    // Method to set up the panel
    private static void setupPanel() {
        thePanel.setLayout(null);
        thePanel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        thePanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT); // Correct bounds setup
    }

    // Method to set up the frame
    private static void setupFrame() {
        JFrame frame = new JFrame("Picture Viewer");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(thePanel);
        frame.pack();
        frame.setVisible(true);
    }

    // Inner class representing the panel
    private static class MyPanel extends JPanel {

        private static final long serialVersionUID = 1L;
        private static Font theFont = new Font("Arial", Font.BOLD, 18);

        // Constructor for MyPanel
        public MyPanel() {
            javax.swing.SwingUtilities.invokeLater(() -> {
                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                setupPanel();
                setupFrame();
            });
        }

        // Method to paint components on the panel
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (currentImage != null) {
                double scaleHeight = 1.0;
                double scaleWidth = 1.0;
                int h = currentImage.getHeight(null);
                int w = currentImage.getWidth(null);
                
                // Calculate scaling factors for the image
                scaleHeight = (double) WINDOW_HEIGHT / h;
                scaleWidth = (double) WINDOW_WIDTH / w;
                double finalScale = Math.min(scaleHeight, scaleWidth); // Use Math.min
                
                // Draw the scaled image on the panel
                g.drawImage(currentImage, 0, 0, (int) (finalScale * w), (int) (finalScale * h), null);
            }
            
            // Display the image name on the panel
            if (currentImageName != null) {
                g.setFont(theFont);
                g.setColor(Color.GREEN);
                g.drawString(currentImageName, 0, 30);
            }
        }
    }
}
