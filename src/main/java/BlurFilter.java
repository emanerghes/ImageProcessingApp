import java.awt.image.BufferedImage;

// Concrete class for blur filter
class BlurFilter extends ImageFilter {
    @Override
    BufferedImage applyFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Apply blur filter
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = getAverageRGB(image, x, y);
                result.setRGB(x, y, rgb);
            }
        }

        return result;
    }

    // Calculate the average RGB value of the surrounding pixels
    private int getAverageRGB(BufferedImage image, int x, int y) {
        int rgbSum = 0;
        int count = 0;

        // Iterate over the surrounding pixels (3x3 neighborhood)
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i;
                int newY = y + j;

                // Check if the pixel is within the image bounds
                if (newX >= 0 && newX < image.getWidth() && newY >= 0 && newY < image.getHeight()) {
                    // Sum up the RGB values
                    rgbSum += image.getRGB(newX, newY);
                    count++;
                }
            }
        }

        // Calculate the average RGB value
        return rgbSum / count;
    }
}
