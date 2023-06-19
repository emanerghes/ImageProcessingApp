
// Factory class for creating filters
interface  FilterFactory {
    public  ImageFilter createFilter();
}

// Factory for  Grayscale filter
class GrayscaleFilterFactory implements FilterFactory {
    public ImageFilter createFilter() {return new GrayscaleFilter();}
}

// Factory for Sepia filter
class SepiaFilterFactory implements FilterFactory {
    public ImageFilter createFilter() {return new SepiaFilter();}
}

// Factory for  Blur filter
class BlurFilterFactory implements FilterFactory {
    public ImageFilter createFilter() {
        return new BlurFilter();
    }
}