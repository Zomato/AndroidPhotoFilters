package com.zomato.photofilters.imageprocessors;


/**
 * @author Varun on 30/06/15.
 */
public class NativeImageProcessor {


    public native static int[] applyRGBCurve(int[] pixels, int[] RGB, int width, int height);

    public native static int[] applyChannelCurves(int[] pixels, int[] R, int[] G, int[] B, int width, int height);

    public native static int[] doBrightness(int[] pixels, int value, int width, int height);

    public native static int[] doContrast(int[] pixels, float value, int width, int height);

    public native static int[] doColorOverlay(int[] pixels, int depth, float red, float green, float blue, int width, int height);

    public native static int[] doSaturation(int[] pixels, float level, int width, int height);
}
