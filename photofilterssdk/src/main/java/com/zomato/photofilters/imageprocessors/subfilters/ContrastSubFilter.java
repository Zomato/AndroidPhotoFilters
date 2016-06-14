package com.zomato.photofilters.imageprocessors.subfilters;

import android.graphics.Bitmap;
import com.zomato.photofilters.imageprocessors.ImageProcessor;
import com.zomato.photofilters.imageprocessors.SubFilter;


/**
 * Class to add Contrast Subfilter
 */
public class ContrastSubFilter implements SubFilter {

    private static String TAG = "";

    // The value is in fraction, value 1 has no effect
    private float contrast = 0;

    /**
     * Initialise contrast subfilter
     *
     * @param contrast The contrast value ranges in fraction, value 1 has no effect
     */
    public ContrastSubFilter(float contrast) {
        this.contrast = contrast;
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        inputImage = ImageProcessor.doContrast(contrast, inputImage);
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

    /**
     * Sets the contrast value by the value passed in as parameter
     */
    public void setContrast(float contrast) {
        this.contrast = contrast;
    }

    /**
     * Changes contrast value by the value passed in as a parameter
     */
    public void changeContrast(float value) {
        this.contrast += value;
    }
}
