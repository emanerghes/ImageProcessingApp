import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class GrayscaleFilterTest {
    @Test
    void testGrayscaleFilter() throws IOException {
        // Create a test image
        BufferedImage testImage =ImageIO.read(new File("C:\\Users\\a\\Pictures\\image2.png"));

        // Apply the grayscale filter
        GrayscaleFilter grayscaleFilter = new GrayscaleFilter();
        BufferedImage processedImage = grayscaleFilter.applyFilter(testImage);

        // Verify the output image properties
        Assertions.assertEquals(testImage.getWidth(), processedImage.getWidth());
        Assertions.assertEquals(testImage.getHeight(), processedImage.getHeight());

        // Verify if the image is converted to grayscale
        for (int y = 0; y < testImage.getHeight(); y++) {
            for (int x = 0; x < testImage.getWidth(); x++) {
                Color originalColor = new Color(testImage.getRGB(x, y));
                Color processedColor = new Color(processedImage.getRGB(x, y));
                int originalGray = (int) (0.21 * originalColor.getRed() + 0.72 * originalColor.getGreen() + 0.07 * originalColor.getBlue());

                Assertions.assertEquals(originalGray, processedColor.getRed());
                Assertions.assertEquals(originalGray, processedColor.getGreen());
                Assertions.assertEquals(originalGray, processedColor.getBlue());
            }
        }
    }
}
