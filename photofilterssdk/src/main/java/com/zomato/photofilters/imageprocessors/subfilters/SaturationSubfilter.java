package com.zomato.photofilters.imageprocessors.subfilters;

import android.graphics.Bitmap;
import com.zomato.photofilters.imageprocessors.ImageProcessor;
import com.zomato.photofilters.imageprocessors.SubFilter;


/**
 * @author varun on 28/07/15.
 */
public class SaturationSubfilter implements SubFilter {
    private static String TAG = "";

    // The Level value is float, where level = 1 has no effect on the image
    private float level;

    public SaturationSubfilter(float level) {
        this.level = level;
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        inputImage = ImageProcessor.doSaturation(inputImage, level);
        return inputImage;
    }

    @Override
    public Object getTag() {
        return TAG;
    }

    @Override
    public void setTag(Object tag) {
        TAG = (String) tag;
    }

    public void setLevel(float level) {
        this.level = level;
    }
}
