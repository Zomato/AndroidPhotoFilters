package com.zomato.photofilters.imageprocessors;


/**
 * @author Varun on 30/06/15.
 */
public final class NativeImageProcessor {
    private NativeImageProcessor() {
    }

    public static native int[] applyRGBCurve(int[] pixels, int[] rgb, int width, int height);

    public static native int[] applyChannelCurves(int[] pixels, int[] r, int[] g, int[] b, int width, int height);

    public static native int[] doBrightness(int[] pixels, int value, int width, int height);

    public static native int[] doContrast(int[] pixels, float value, int width, int height);

    public static native int[] doColorOverlay(int[] pixels, int depth,
                                              float red, float green, float blue,
                                              int width, int height);

    public static native int[] doSaturation(int[] pixels, float level, int width, int height);
}
