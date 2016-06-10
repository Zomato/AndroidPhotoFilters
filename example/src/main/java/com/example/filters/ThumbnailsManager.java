package com.example.filters;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Varun on 30/06/15.
 *
 * Singleton Class Used to Manage filters and process them all at once
 */
public class ThumbnailsManager {
    private static List<ThumbnailItem> filterThumbs;
    private static List<ThumbnailItem> processedThumbs;

    public ThumbnailsManager(){
        filterThumbs = new ArrayList<ThumbnailItem>();
        processedThumbs = new ArrayList<ThumbnailItem>();
    }

    public static void addThumb(ThumbnailItem thumbnailItem){
        filterThumbs.add(thumbnailItem);
    }

    public static List<ThumbnailItem> processThumbs(Context context) {
        for (ThumbnailItem thumb : filterThumbs) {
            // scaling down the image
            float size = context.getResources().getDimension(R.dimen.thumbnail_size);
            thumb.image = Bitmap.createScaledBitmap(thumb.image, (int)size, (int)size, false);
            thumb.image = thumb.filter.processFilter(thumb.image);
            //cropping circle
            thumb.image = GeneralUtils.generateCircularBitmap(thumb.image);
            processedThumbs.add(thumb);
        }
        return processedThumbs;
    }

    public static void clearThumbs() {
        filterThumbs = new ArrayList<>();
        processedThumbs = new ArrayList<>();
    }
}
