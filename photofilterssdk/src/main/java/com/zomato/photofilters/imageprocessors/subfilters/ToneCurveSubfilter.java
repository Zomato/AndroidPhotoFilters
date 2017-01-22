package com.zomato.photofilters.imageprocessors.subfilters;

import android.graphics.Bitmap;
import com.zomato.photofilters.geometry.BezierSpline;
import com.zomato.photofilters.geometry.Point;
import com.zomato.photofilters.imageprocessors.ImageProcessor;
import com.zomato.photofilters.imageprocessors.SubFilter;


/**
 * @author varun
 * Subfilter to tweak rgb channels of an image
 */
public class ToneCurveSubfilter implements SubFilter {
    private static String tag = "";

    // These are knots which contains the plot points
    private Point[] rgbKnots;
    private Point[] greenKnots;
    private Point[] redKnots;
    private Point[] blueKnots;
    private int[] rgb;
    private int[] r;
    private int[] g;
    private int[] b;

    /**
     * Initialise ToneCurveSubfilter (NOTE : Don't pass null knots, pass straight line instead)
     * Knots are the points in 2D taken by tweaking photoshop channels(plane ranging from 0-255)
     *
     * @param rgbKnots   rgb Knots
     * @param redKnots   Knots in Red Channel
     * @param greenKnots Knots in green Channel
     * @param blueKnots  Knots in Blue channel
     */
    public ToneCurveSubfilter(Point[] rgbKnots, Point[] redKnots, Point[] greenKnots, Point[] blueKnots) {
        Point[] straightKnots = new Point[2];
        straightKnots[0] = new Point(0, 0);
        straightKnots[1] = new Point(255, 255);
        if (rgbKnots == null) {
            this.rgbKnots = straightKnots;
        } else {
            this.rgbKnots = rgbKnots;
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
        rgbKnots = sortPointsOnXAxis(rgbKnots);
        redKnots = sortPointsOnXAxis(redKnots);
        greenKnots = sortPointsOnXAxis(greenKnots);
        blueKnots = sortPointsOnXAxis(blueKnots);
        if (rgb == null) {
            rgb = BezierSpline.curveGenerator(rgbKnots);
        }

        if (r == null) {
            r = BezierSpline.curveGenerator(redKnots);
        }

        if (g == null) {
            g = BezierSpline.curveGenerator(greenKnots);
        }

        if (b == null) {
            b = BezierSpline.curveGenerator(blueKnots);
        }

        return ImageProcessor.applyCurves(rgb, r, g, b, inputImage);
    }

    public Point[] sortPointsOnXAxis(Point[] points) {
        if (points == null) {
            return null;
        }
        for (int s = 1; s < points.length - 1; s++) {
            for (int k = 0; k <= points.length - 2; k++) {
                if (points[k].x > points[k + 1].x) {
                    float temp = 0;
                    temp = points[k].x;
                    points[k].x = points[k + 1].x; //swapping values
                    points[k + 1].x = temp;
                }
            }
        }
        return points;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(Object tag) {
        ToneCurveSubfilter.tag = (String) tag;
    }
}
