import java.awt.*;
import java.awt.image.BufferedImage;

// Concrete class for grayscale filter
class GrayscaleFilter extends ImageFilter {
    @Override
    BufferedImage applyFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Apply grayscale filter
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get the color of the pixel at (x, y)
                Color color = new Color(image.getRGB(x, y));

                // Calculate the grayscale value using weighted averages of the color channels
                int gray = (int) (0.21 * color.getRed() + 0.72 * color.getGreen() + 0.07 * color.getBlue());

                // Create a new Color object with the grayscale value
                Color grayColor = new Color(gray, gray, gray);

                // Set the grayscale color as the result for the corresponding pixel in the output image
                result.setRGB(x, y, grayColor.getRGB());
            }
        }

        return result;
    }
}
