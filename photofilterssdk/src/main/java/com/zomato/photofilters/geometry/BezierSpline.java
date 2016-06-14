package com.zomato.photofilters.geometry;


import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.view.animation.PathInterpolator;

/**
 * @author Varun on 29/06/15.
 */
public class BezierSpline {

    private static final String TAG = BezierSpline.class.getSimpleName();

    /**
     * Generates Curve {in a plane ranging from 0-255} using the knots provided
     */
    public static int[] curveGenerator(Point[] knots) {
        if (knots == null) {
            throw new NullPointerException("Knots cannot be null");
        }

        int n = knots.length - 1;
        if (n < 1) {
            throw new IllegalArgumentException("Atleast two points are required");
        }

        if (Build.VERSION.SDK_INT >= 21) {
            return getOutputPointsV21(knots);
        } else {
            return getOutputPointsOlderDevices(knots);
        }
    }

    // This is for lollipop and newer devices
    private static int[] getOutputPointsV21(Point[] knots) {

        Point[] controlPoints = calculateControlPoints(knots);
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(knots[0].X / 255.0f, knots[0].Y / 255.0f);
        path.moveTo(knots[0].X / 255.0f, knots[0].Y / 255.0f);

        for (int index = 1; index < knots.length; index++) {
            path.quadTo(controlPoints[index - 1].X / 255.0f, controlPoints[index - 1].Y / 255.0f, knots[index].X / 255.0f, knots[index].Y / 255.0f);
            path.moveTo(knots[index].X / 255.0f, knots[index].Y / 255.0f);
        }

        path.lineTo(1, 1);
        path.moveTo(1, 1);

        float[] allPoints = new float[256];

        for (int x = 0; x < 256; x++) {
            PathInterpolator pathInterpolator = new PathInterpolator(path);
            allPoints[x] = 255.0f * pathInterpolator.getInterpolation((float) x / 255.0f);
        }

        allPoints[0] = knots[0].Y;
        allPoints[255] = knots[knots.length - 1].Y;
        return validateCurve(allPoints);
    }


    //This is for devices older than lollipop
    private static int[] getOutputPointsOlderDevices(Point[] knots) {
        Point[] controlPoints = calculateControlPoints(knots);
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(knots[0].X, knots[0].Y);
        path.moveTo(knots[0].X, knots[0].Y);

        for (int index = 1; index < knots.length; index++) {
            path.quadTo(controlPoints[index - 1].X, controlPoints[index - 1].Y, knots[index].X, knots[index].Y);
            path.moveTo(knots[index].X, knots[index].Y);
        }

        path.lineTo(255, 255);
        path.moveTo(255, 255);

        float[] allPoints = new float[256];

        PathMeasure pm = new PathMeasure(path, false);
        for (int i = 0; i < 256; i++) {
            allPoints[i] = -1;
        }

        int x = 0;
        float[] acoordinates = {0, 0};

        do {
            float pathLength = pm.getLength();
            for (float i = 0; i <= pathLength; i += 0.08f) {
                pm.getPosTan(i, acoordinates, null);
                if ((int) (acoordinates[0]) > x && x < 256) {
                    allPoints[x] = acoordinates[1];
                    x++;
                }
                if (x > 255) {
                    break;
                }
            }
        } while (pm.nextContour());


        allPoints[0] = knots[0].Y;
        for (int i = 0; i < 256; i++) {
            if (allPoints[i] == -1) {
                allPoints[i] = allPoints[i - 1];
            }
        }
        allPoints[255] = knots[knots.length - 1].Y;
        return validateCurve(allPoints);
    }

    private static int[] validateCurve(float[] allPoints) {
        int[] curvedPath = new int[256];
        for (int x = 0; x < 256; x++) {
            if (allPoints[x] > 255.0f) {
                curvedPath[x] = 255;
            } else if (allPoints[x] < 0.0f) {
                curvedPath[x] = 0;
            } else {
                curvedPath[x] = Math.round(allPoints[x]);
            }
        }
        return curvedPath;
    }

    // Calculates the control points for the specified knots
    private static Point[] calculateControlPoints(Point[] knots) {
        int n = knots.length - 1;
        Point[] controlPoints = new Point[n];

        if (n == 1) { // Special case: Bezier curve should be a straight line.
            // 3P1 = 2P0 + P3
            controlPoints[0] = new Point((2 * knots[0].X + knots[1].X) / 3, (2 * knots[0].Y + knots[1].Y) / 3);
            // P2 = 2P1 – P0
//            controlPoints[1][0] = new Point( 2 * controlPoints[0][0].X - knots[0].X, 2 * controlPoints[0][0].Y - knots[0].Y);
        } else {
            // Calculate first Bezier control points
            // Right hand side vector
            float[] rhs = new float[n];

            // Set right hand side X values
            for (int i = 1; i < n - 1; ++i)
                rhs[i] = 4 * knots[i].X + 2 * knots[i + 1].X;
            rhs[0] = knots[0].X + 2 * knots[1].X;
            rhs[n - 1] = (8 * knots[n - 1].X + knots[n].X) / 2.0f;
            // Get first control points X-values
            float[] x = getFirstControlPoints(rhs);

            // Set right hand side Y values
            for (int i = 1; i < n - 1; ++i)
                rhs[i] = 4 * knots[i].Y + 2 * knots[i + 1].Y;
            rhs[0] = knots[0].Y + 2 * knots[1].Y;
            rhs[n - 1] = (8 * knots[n - 1].Y + knots[n].Y) / 2.0f;
            // Get first control points Y-values
            float[] y = getFirstControlPoints(rhs);
            for (int i = 0; i < n; ++i) {
                controlPoints[i] = new Point(x[i], y[i]);
            }
        }

        return controlPoints;
    }

    private static float[] getFirstControlPoints(float[] rhs) {
        int n = rhs.length;
        float[] x = new float[n]; // Solution vector.
        float[] tmp = new float[n]; // Temp workspace.

        float b = 1.0f;   // Control Point Factor
        x[0] = rhs[0] / b;
        for (int i = 1; i < n; i++) // Decomposition and forward substitution.
        {
            tmp[i] = 1 / b;
            b = (i < n - 1 ? 4.0f : 3.5f) - tmp[i];
            x[i] = (rhs[i] - x[i - 1]) / b;
        }
        for (int i = 1; i < n; i++)
            x[n - i - 1] -= tmp[n - i] * x[n - i]; // Backsubstitution.
        return x;
    }
}
