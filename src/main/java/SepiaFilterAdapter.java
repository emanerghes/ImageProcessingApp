import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

// Adapter interface for external filters
interface ExternalFilterAdapter {
    BufferedImage processImage(BufferedImage image);
}

class SepiaFilterAdapter implements ExternalFilterAdapter {
    private SepiaFilter sepiaFilter;

    public SepiaFilterAdapter(SepiaFilter sepiaFilter) {
        this.sepiaFilter = sepiaFilter;
    }

    public BufferedImage processImage(BufferedImage image) {
        BufferedImage convertedImage = convertToBufferedImage(image);
        // Call the SepiaFilter's method
        BufferedImage filteredImage = SepiaFilter2.applySepia(convertedImage);
        return filteredImage;
    }

    public static BufferedImage convertToBufferedImage(RenderedImage image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        } else {
            BufferedImage bufferedImage = new BufferedImage(
                    image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            bufferedImage.getGraphics().drawImage((Image) image, 0, 0, null);
            return bufferedImage;
        }
    }
}



        // Adaptee class representing the external SepiaFilter
    class SepiaFilter2 {
            public static BufferedImage applySepia(BufferedImage image) {
                int width = image.getWidth();
                int height = image.getHeight();

                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        Color color = new Color(image.getRGB(x, y));
                        int r = (int) (0.393 * color.getRed() + 0.769 * color.getGreen() + 0.189 * color.getBlue());
                        int g = (int) (0.349 * color.getRed() + 0.686 * color.getGreen() + 0.168 * color.getBlue());
                        int b = (int) (0.272 * color.getRed() + 0.534 * color.getGreen() + 0.131 * color.getBlue());
                        r = Math.min(r, 255);
                        g = Math.min(g, 255);
                        b = Math.min(b, 255);
                        Color sepiaColor = new Color(r, g, b);
                        image.setRGB(x, y, sepiaColor.getRGB());
                        return image;
                    }
                }
                return image;
            }
        }