import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;

class ApplyFilterTest {
    private ImageProcessingApplication app;

    @Test
    void testApplyFilter() {

        app = new ImageProcessingApplication();
        app.loadImage("C:\\Users\\a\\Pictures\\image3.png");
        BufferedImage originalImage = app.getOriginalImage();

        // Apply the grayscale filter
        app.applyFilter("grayscale");

        // Verify that the processed image is different from the original image
        BufferedImage processedImage = app.getProcessedImage();
        Assertions.assertNotSame(originalImage, processedImage);

    }
}

