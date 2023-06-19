import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

class ProxyImageFilter extends ImageFilter {
    private ImageFilter realImageFilter;
    private String filterType;
    ChooseFilterFactory chooseFilterFactory;
    private Map<String, BufferedImage> cache;

    public ProxyImageFilter() {
        this.cache = new HashMap<>();
        chooseFilterFactory = new ChooseFilterFactory();

    }

    public BufferedImage createFilter(BufferedImage image, String filterType) {

        this.filterType = filterType;
        realImageFilter = chooseFilterFactory.createFilter(filterType);
        BufferedImage returnImage = applyFilter(image);
        return returnImage;
    }

    @Override
    BufferedImage applyFilter(BufferedImage image) {

        // Check if the filtered image is already cached
        if (cache.containsKey(filterType)) {
            System.out.println("\n" + "Image already filtered with this filter");
            return cache.get(filterType);
        } else {
            BufferedImage filteredImage = realImageFilter.applyFilter(image);
            cache.put(filterType, filteredImage);
            System.out.println("\n" + "Image  filtered with this filter");
            return filteredImage;
        }
    }
}
