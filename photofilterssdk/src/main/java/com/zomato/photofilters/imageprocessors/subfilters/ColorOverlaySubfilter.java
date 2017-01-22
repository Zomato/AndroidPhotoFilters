package com.zomato.photofilters.imageprocessors.subfilters;

import android.graphics.Bitmap;
import com.zomato.photofilters.imageprocessors.ImageProcessor;
import com.zomato.photofilters.imageprocessors.SubFilter;


/**
 * @author varun
 * Subfilter used to overlay bitmap with the color defined
 */
public class ColorOverlaySubfilter implements SubFilter {
    private static String tag = "";

    // the color overlay depth is between 0-255
    private final int colorOverlayDepth;

    // these values are between 0-1
    private final float colorOverlayRed;
    private final float colorOverlayGreen;
    private final float colorOverlayBlue;

    /**
     * Initialize Color Overlay Subfilter
     *
     * @param depth Value ranging from 0-255 {Defining intensity of color overlay}
     * @param red   Red value between 0-1
     * @param green Green value between 0-1
     * @param blue  Blue value between 0-1
     */
    public ColorOverlaySubfilter(int depth, float red, float green, float blue) {
        this.colorOverlayDepth = depth;
        this.colorOverlayRed = red;
        this.colorOverlayBlue = blue;
        this.colorOverlayGreen = green;
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        return ImageProcessor.doColorOverlay(
                colorOverlayDepth, colorOverlayRed, colorOverlayGreen, colorOverlayBlue, inputImage
        );
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        ColorOverlaySubfilter.tag = (String) tag;
    }
}
