package com.zomato.photofilters.imageprocessors;


import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ColorOverlaySubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ToneCurveSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.VignetteSubFilter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This Class represents a ImageFilter and includes many subfilters within, we add different subfilters to this class's
 * object and they are then processed in that particular order
 */
public class Filter {
    private List<SubFilter> subFilters = new ArrayList<>();

    public Filter(Filter filter) {
        this.subFilters = filter.subFilters;
    }

    public Filter() {
    }

    /**
     * Adds a Subfilter to the Main Filter
     *
     * @param subFilter Subfilter like contrast, brightness, tone Curve etc. subfilter
     * @see BrightnessSubFilter
     * @see ColorOverlaySubFilter
     * @see ContrastSubFilter
     * @see ToneCurveSubFilter
     * @see VignetteSubFilter
     * @see SaturationSubFilter
     */
    public void addSubFilter(SubFilter subFilter) {
        subFilters.add(subFilter);
    }

    /**
     * Adds all {@link SubFilter}s from the List to the Main Filter.
     * @param subFilterList a list of {@link SubFilter}s; must not be null
     */
    public void addSubFilters(@NonNull List<SubFilter> subFilterList) {
        subFilters.addAll(subFilterList);
    }

    /**
     * Get a new list of currently applied subfilters.
     *
     * @return a {@link List} of {@link SubFilter}.
     *         Empty if no filters are added to {@link #subFilters};
     *         never null
     *
     * @see #addSubFilter(SubFilter)
     * @see #addSubFilters(List)
     */
    @NonNull
    public List<SubFilter> getSubFilters() {
        if (subFilters == null || subFilters.isEmpty())
            return new ArrayList<>(0);
        return new ArrayList<>(subFilters);
    }

    /**
     * Clears all the subfilters from the Parent Filter
     */
    public void clearSubFilters() {
        subFilters.clear();
    }

    /**
     * Removes the subfilter containing Tag from the Parent Filter
     */
    public void removeSubFilterWithTag(String tag) {
        Iterator<SubFilter> iterator = subFilters.iterator();
        while (iterator.hasNext()) {
            SubFilter subFilter = iterator.next();
            if (subFilter.getTag().equals(tag)) {
                iterator.remove();
            }
        }
    }

    /**
     * Returns The filter containing Tag
     */
    public SubFilter getSubFilterByTag(String tag) {
        for (SubFilter subFilter : subFilters) {
            if (subFilter.getTag().equals(tag)) {
                return subFilter;
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
            for (SubFilter subFilter : subFilters) {
                try {
                    outputImage = subFilter.process(outputImage);
                } catch (OutOfMemoryError oe) {
                    System.gc();
                    try {
                        outputImage = subFilter.process(outputImage);
                    } catch (OutOfMemoryError ignored) {
                    }
                }
            }
        }

        return outputImage;
    }

}
