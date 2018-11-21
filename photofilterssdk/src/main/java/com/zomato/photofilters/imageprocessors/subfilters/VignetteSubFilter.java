package com.zomato.photofilters.imageprocessors.subfilters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.zomato.photofilters.R;
import com.zomato.photofilters.imageprocessors.SubFilter;


/**
 * @author varun
 * Subfilter to add Vignette effect on an image
 */
public class VignetteSubFilter implements SubFilter {

    private static String tag = "";
    private Context context;

    // value of alpha is between 0-255
    private int alpha = 0;

    /**
     * Initialise Vignette subfilter
     *
     * @param alpha value of alpha ranges from 0-255 (Intensity of Vignette effect)
     */
    public VignetteSubFilter(Context context, int alpha) {
        this.context = context;
        this.alpha = alpha;
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        Bitmap vignette = BitmapFactory.decodeResource(context.getResources(), R.drawable.vignette);

        vignette = Bitmap.createScaledBitmap(vignette, inputImage.getWidth(), inputImage.getHeight(), true);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setAlpha(alpha);

        Canvas comboImage = new Canvas(inputImage);
        comboImage.drawBitmap(vignette, 0f, 0f, paint);

        return inputImage;
    }

    @Override
    public Object getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        VignetteSubFilter.tag = (String) tag;
    }

    /**
     * Change alpha value to new value
     */
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    /**
     * Changes alpha value by that number
     */
    public void changeAlpha(int value) {
        this.alpha += value;
        if (alpha > 255) {
            alpha = 255;
        } else if (alpha < 0) {
            alpha = 0;
        }
    }
}
