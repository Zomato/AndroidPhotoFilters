package com.zomato.photofilters.imageprocessors.subfilters;

import android.graphics.Bitmap;
import com.zomato.photofilters.imageprocessors.ImageProcessor;
import com.zomato.photofilters.imageprocessors.SubFilter;


/**
 * Subfilter used to overlay bitmap with the color defined
 */
public class ColorOverlaySubFilter implements SubFilter {
    private static String TAG = "";

    // the color overlay depth is between 0-255
    protected int colorOverlayDepth;

    // these values are between 0-1
    protected float colorOverlayRed, colorOverlayGreen, colorOverlayBlue;

    /**
     * Initialize Color Overlay Subfilter
     *
     * @param depth Value ranging from 0-255 {Defining intensity of color overlay}
     * @param red   Red value between 0-1
     * @param green Green value between 0-1
     * @param blue  Blue value between 0-1
     */
    public ColorOverlaySubFilter(int depth, float red, float green, float blue) {
        this.colorOverlayDepth = depth;
        this.colorOverlayRed = red;
        this.colorOverlayBlue = blue;
        this.colorOverlayGreen = green;
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        inputImage = ImageProcessor.doColorOverlay(colorOverlayDepth, colorOverlayRed, colorOverlayGreen, colorOverlayBlue, inputImage);
        return inputImage;
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public void setTag(Object tag) {
        TAG = (String) tag;
    }
}
