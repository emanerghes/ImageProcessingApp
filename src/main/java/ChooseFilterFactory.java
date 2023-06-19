
class ChooseFilterFactory {

    public  ImageFilter createFilter(String filterType) {

        return switch (filterType) {
            case "grayscale" -> new GrayscaleFilterFactory().createFilter();
            case "sepia" -> new SepiaFilterFactory().createFilter();
            case "blur" -> new BlurFilterFactory().createFilter();
            default -> throw new IllegalArgumentException("Invalid filter type: " + filterType);
        };
    }

}

