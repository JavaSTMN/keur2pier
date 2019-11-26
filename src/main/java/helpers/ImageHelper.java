package helpers;

import java.awt.*;

public class ImageHelper {
    private static Toolkit toolkit;
    private static MediaTracker tracker;


    public static <T> Image findImage(String imageLocation, T component) {

        // Get an image
        toolkit = Toolkit.getDefaultToolkit();
        tracker = new MediaTracker((Component) component);
        Image image = toolkit.getImage(imageLocation);
        tracker.addImage(image, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return image;
    }
}