import java.awt.*;
import java.awt.image.BufferedImage;

// Concrete class for sepia filter
class SepiaFilter extends ImageFilter {
    @Override
    BufferedImage applyFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Apply sepia filter
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get the color of the pixel at (x, y)
                Color color = new Color(image.getRGB(x, y));

                // Calculate the new RGB values for the sepia effect
                int r = (int) (0.393 * color.getRed() + 0.769 * color.getGreen() + 0.189 * color.getBlue());
                int g = (int) (0.349 * color.getRed() + 0.686 * color.getGreen() + 0.168 * color.getBlue());
                int b = (int) (0.272 * color.getRed() + 0.534 * color.getGreen() + 0.131 * color.getBlue());

                // Ensure the RGB values are within the valid range (0-255)
                r = Math.min(r, 255);
                g = Math.min(g, 255);
                b = Math.min(b, 255);

                // Create a new Color object with the sepia RGB values
                Color sepiaColor = new Color(r, g, b);

                // Set the sepia color as the result for the corresponding pixel in the output image
                result.setRGB(x, y, sepiaColor.getRGB());
            }
        }

        return result;
    }
}
