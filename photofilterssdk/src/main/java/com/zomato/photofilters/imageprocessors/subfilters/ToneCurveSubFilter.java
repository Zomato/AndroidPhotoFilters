package com.zomato.photofilters.imageprocessors.subfilters;

import android.graphics.Bitmap;

import com.zomato.photofilters.geometry.BezierSpline;
import com.zomato.photofilters.geometry.Point;
import com.zomato.photofilters.imageprocessors.ImageProcessor;
import com.zomato.photofilters.imageprocessors.SubFilter;


/**
 * Subfilter to tweak RGB channels of an image
 */
public class ToneCurveSubFilter implements SubFilter {
    private static String TAG = "";

    // These are knots which contains the plot points
    private Point[] RGBKnots, greenKnots, redKnots, blueKnots;
    private int[] RGB, R, G, B;

    private Point[] straightKnots;

    /**
     * Initialise ToneCurveSubfilter (NOTE : Don't pass null knots, pass straight line instead)
     * Knots are the points in 2D taken by tweaking photoshop channels(plane ranging from 0-255)
     * @param RGBKnots RGB Knots
     * @param redKnots Knots in Red Channel
     * @param greenKnots Knots in green Channel
     * @param blueKnots Knots in Blue channel
     */
    public ToneCurveSubFilter(Point[] RGBKnots, Point[] redKnots, Point[] greenKnots, Point[] blueKnots) {
        straightKnots = new Point[2];
        straightKnots[0] = new Point(0,0);
        straightKnots[1] = new Point(255,255);
        if (RGBKnots == null) {
            this.RGBKnots = straightKnots;
        } else {
            this.RGBKnots = RGBKnots;
        }
        if (redKnots == null) {
            this.redKnots = straightKnots;
        } else {
            this.redKnots = redKnots;
        }
        if (greenKnots == null) {
            this.greenKnots = straightKnots;
        } else {
            this.greenKnots = greenKnots;
        }
        if (blueKnots == null) {
            this.blueKnots = straightKnots;
        } else {
            this.blueKnots = blueKnots;
        }
    }

    @Override
    public Bitmap process(Bitmap inputImage) {
        RGBKnots = sortPointsOnXAxis(RGBKnots);
        redKnots = sortPointsOnXAxis(redKnots);
        greenKnots = sortPointsOnXAxis(greenKnots);
        blueKnots = sortPointsOnXAxis(blueKnots);
        if (RGB == null)
            RGB = BezierSpline.curveGenerator(RGBKnots);

        if (R == null)
            R = BezierSpline.curveGenerator(redKnots);

        if (G == null)
            G = BezierSpline.curveGenerator(greenKnots);

        if (B == null)
            B = BezierSpline.curveGenerator(blueKnots);

        inputImage = ImageProcessor.applyCurves(RGB, R, G, B, inputImage);
        return inputImage;
    }

    public Point[] sortPointsOnXAxis(Point[] points){
        if (points == null)
            return null;
        for (int s=1; s<points.length-1 ; s++) {
            for (int k = 0; k <= points.length-2 ; k++) {
                if (points[k].X > points[k+1].X) {
                    float temp = 0;
                    temp = points[k].X;
                    points[k].X = points[k+1].X; //swapping values
                    points[k+1].X = temp;
                }
            }
        }
        return points;
    }

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public void setTag(Object tag) {
        TAG = (String) tag;
    }
}
