import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ImageProcessingApplication {
    private BufferedImage originalImage;
    private BufferedImage processedImage;
    private JFrame frame;
    private JLabel imageLabel;

    public synchronized void loadImage(String filePath) {
        try {
            originalImage = ImageIO.read(new File(filePath));
            processedImage = originalImage;
            displayImage(processedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void applyFilter(String filterType) {
        ImageFilter filter = ChooseFilterFactory.createFilter(filterType);
        processedImage = filter.applyFilter(originalImage);
        displayImage(processedImage);
    }

    public void saveImage(String outputPath) {
        try {
            ImageIO.write(processedImage, "PNG", new File(outputPath));
            JOptionPane.showMessageDialog(frame, "Image saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void revertToOriginal() {
        processedImage = originalImage;
        displayImage(processedImage);
    }

    private void displayImage(BufferedImage image) {
        if (frame == null) {
            frame = new JFrame("Image Processing Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Create image label
            imageLabel = new JLabel();
            JScrollPane scrollPane = new JScrollPane(imageLabel);

            // Set the preferred size of the scroll pane
            scrollPane.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));

            frame.add(scrollPane, BorderLayout.CENTER);

            // Create filter buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());

            JButton grayscaleButton = new JButton("Grayscale");
            grayscaleButton.addActionListener(e -> applyFilter("grayscale"));

            JButton sepiaButton = new JButton("Sepia");
            sepiaButton.addActionListener(e -> applyFilter("sepia"));

            JButton blurButton = new JButton("Blur");
            blurButton.addActionListener(e -> applyFilter("blur"));

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(e -> saveImage("output.jpg"));

            JButton revertButton = new JButton("Revert");
            revertButton.addActionListener(e -> revertToOriginal());

            buttonPanel.add(grayscaleButton);
            buttonPanel.add(sepiaButton);
            buttonPanel.add(blurButton);
            buttonPanel.add(saveButton);
            buttonPanel.add(revertButton);

            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);
        }

        ImageIcon icon = new ImageIcon(image);
        imageLabel.setIcon(icon);
        frame.repaint();
    }

    public BufferedImage getOriginalImage() {return originalImage;}

    public BufferedImage getProcessedImage() {return processedImage;}
}