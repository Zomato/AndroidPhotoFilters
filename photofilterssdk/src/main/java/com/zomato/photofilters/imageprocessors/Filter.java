package com.zomato.photofilters.imageprocessors;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This Class represents a ImageFilter and includes many subfilters within, we add different subfilters to this class's
 * object and they are then processed in that particular order
 */
public class Filter {
    private List<Subfilter> subfilters = new ArrayList<>();

    public Filter(Filter filter) {
        this.subfilters = filter.subfilters;
    }

    public Filter() {
    }

    /**
     * Adds a Subfilter to the Main Filter
     *
     * @param subfilter Subfilter like contrast, brightness, tone Curve etc. subfilter
     * @see .subfilters.BrightnessSubFilter
     * @see .subfilters.ColorOverlaySubFilter
     * @see .subfilters.ContrastSubFilter
     * @see .subfilters.ToneCurveSubFilter
     * @see .subfilters.VignetteSubFilter
     * @see .subfilters.SaturationSubFilter
     */
    public void addSubfilter(Subfilter subfilter) {
        subfilters.add(subfilter);
    }

    /**
     * Clears all the subfilters from the Parent Filter
     */
    public void clearSubFilters() {
        subfilters.clear();
    }

    /**
     * Removes the subfilter containing Tag from the Parent Filter
     */
    public void removeSubFilterWithTag(String tag) {
        Iterator<Subfilter> iterator = subfilters.iterator();
        while (iterator.hasNext()) {
            Subfilter subFilter = iterator.next();
            if (subFilter.getTag().equals(tag)) {
                iterator.remove();
            }
        }
    }

    /**
     * Returns The filter containing Tag
     */
    public Subfilter getSubFilterByTag(String tag) {
        for (Subfilter subfilter : subfilters) {
            if (subfilter.getTag().equals(tag)) {
                return subfilter;
            }
        }
        return null;
    }

    /**
     * Give the output Bitmap by applying the defined filter
     *
     * @param inputImage Input Bitmap on which filter is to be applied
     * @return filtered Bitmap
     */
    public Bitmap processFilter(Bitmap inputImage) {
        Bitmap outputImage = inputImage;
        if (outputImage != null) {
            for (Subfilter subfilter : subfilters) {
                try {
                    outputImage = subfilter.process(outputImage);
                } catch (OutOfMemoryError oe) {
                    System.gc();
                    try {
                        outputImage = subfilter.process(outputImage);
                    } catch (OutOfMemoryError ignored) {
                    }
                }
            }
        }

        return outputImage;
    }

}
