package com.zomato.photofilters.imageprocessors;

import android.graphics.Bitmap;

/**
 * @author varun on 27/07/15.
 */
public interface Subfilter {
    Bitmap process(Bitmap inputImage);

    Object getTag();

    void setTag(Object tag);
}
