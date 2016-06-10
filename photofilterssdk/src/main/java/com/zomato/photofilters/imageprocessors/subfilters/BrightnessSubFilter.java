package com.zomato.photofilters.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.zomato.photofilters.imageprocessors.ImageProcessor;
import com.zomato.photofilters.imageprocessors.SubFilter;


/**
 * subfilter used to tweak brightness of the Bitmap
 */
public class BrightnessSubFilter implements SubFilter {
    // Value is in integer
    private int brightness = 0;
    private static String TAG = "";

    /**
     * Takes Brightness of the image
     *
     * @param brightness Integer brightness value {value 0 has no effect}
     */
    public BrightnessSubFilter(int brightness) {
        this.brightness = brightness;
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        inputImage = ImageProcessor.doBrightness(brightness, inputImage);
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

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    /**
     * Changes the brightness by the value passed as parameter
     *
     * @param value
     */
    public void changeBrightness(int value) {
        this.brightness += value;
    }

}
