package com.zomato.photofilters;

import com.zomato.photofilters.geometry.Point;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ColorOverlaySubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ToneCurveSubFilter;

import java.lang.reflect.Array;

/**
 * @author Varun on 01/07/15.
 */
public final class SampleFilters {

    private SampleFilters() {
    }

    public static Filter getStarLitFilter() {
        Point[] rgbKnots;
        rgbKnots = new Point[8];
        rgbKnots[0] = new Point(0, 0);
        rgbKnots[1] = new Point(34, 6);
        rgbKnots[2] = new Point(69, 23);
        rgbKnots[3] = new Point(100, 58);
        rgbKnots[4] = new Point(150, 154);
        rgbKnots[5] = new Point(176, 196);
        rgbKnots[6] = new Point(207, 233);
        rgbKnots[7] = new Point(255, 255);
        Filter filter = new Filter();
        filter.addSubFilter(new ToneCurveSubFilter(rgbKnots, null, null, null));
        return filter;
    }

    public static Filter getBlueMessFilter() {
        Point[] redKnots;
        redKnots = new Point[8];
        redKnots[0] = new Point(0, 0);
        redKnots[1] = new Point(86, 34);
        redKnots[2] = new Point(117, 41);
        redKnots[3] = new Point(146, 80);
        redKnots[4] = new Point(170, 151);
        redKnots[5] = new Point(200, 214);
        redKnots[6] = new Point(225, 242);
        redKnots[7] = new Point(255, 255);
        Filter filter = new Filter();
        filter.addSubFilter(new ToneCurveSubFilter(null, redKnots, null, null));
        filter.addSubFilter(new BrightnessSubFilter(30));
        filter.addSubFilter(new ContrastSubFilter(1f));
        return filter;
    }

    public static Filter getAweStruckVibeFilter() {
        Point[] rgbKnots;
        Point[] redKnots;
        Point[] greenKnots;
        Point[] blueKnots;

        rgbKnots = new Point[5];
        rgbKnots[0] = new Point(0, 0);
        rgbKnots[1] = new Point(80, 43);
        rgbKnots[2] = new Point(149, 102);
        rgbKnots[3] = new Point(201, 173);
        rgbKnots[4] = new Point(255, 255);

        redKnots = new Point[5];
        redKnots[0] = new Point(0, 0);
        redKnots[1] = new Point(125, 147);
        redKnots[2] = new Point(177, 199);
        redKnots[3] = new Point(213, 228);
        redKnots[4] = new Point(255, 255);


        greenKnots = new Point[6];
        greenKnots[0] = new Point(0, 0);
        greenKnots[1] = new Point(57, 76);
        greenKnots[2] = new Point(103, 130);
        greenKnots[3] = new Point(167, 192);
        greenKnots[4] = new Point(211, 229);
        greenKnots[5] = new Point(255, 255);


        blueKnots = new Point[7];
        blueKnots[0] = new Point(0, 0);
        blueKnots[1] = new Point(38, 62);
        blueKnots[2] = new Point(75, 112);
        blueKnots[3] = new Point(116, 158);
        blueKnots[4] = new Point(171, 204);
        blueKnots[5] = new Point(212, 233);
        blueKnots[6] = new Point(255, 255);

        Filter filter = new Filter();
        filter.addSubFilter(new ToneCurveSubFilter(rgbKnots, redKnots, greenKnots, blueKnots));
        return filter;
    }

    public static Filter getLimeStutterFilter() {
        Point[] blueKnots;
        blueKnots = new Point[3];
        blueKnots[0] = new Point(0, 0);
        blueKnots[1] = new Point(165, 114);
        blueKnots[2] = new Point(255, 255);
        // Check whether output is null or not.
        Filter filter = new Filter();
        filter.addSubFilter(new ToneCurveSubFilter(null, null, null, blueKnots));
        return filter;
    }

    public static Filter getNightWhisperFilter() {
        Point[] rgbKnots;
        Point[] redKnots;
        Point[] greenKnots;
        Point[] blueKnots;

        rgbKnots = new Point[3];
        rgbKnots[0] = new Point(0, 0);
        rgbKnots[1] = new Point(174, 109);
        rgbKnots[2] = new Point(255, 255);

        redKnots = new Point[4];
        redKnots[0] = new Point(0, 0);
        redKnots[1] = new Point(70, 114);
        redKnots[2] = new Point(157, 145);
        redKnots[3] = new Point(255, 255);

        greenKnots = new Point[3];
        greenKnots[0] = new Point(0, 0);
        greenKnots[1] = new Point(109, 138);
        greenKnots[2] = new Point(255, 255);

        blueKnots = new Point[3];
        blueKnots[0] = new Point(0, 0);
        blueKnots[1] = new Point(113, 152);
        blueKnots[2] = new Point(255, 255);

        Filter filter = new Filter();
        filter.addSubFilter(new ToneCurveSubFilter(rgbKnots, redKnots, greenKnots, blueKnots));
        return filter;
    }

    public static Filter getDefaultFilter() {
        Filter filter = new Filter();
        filter.addSubFilter(new BrightnessSubFilter(20));
        filter.addSubFilter(new ContrastSubFilter(1.4f));
        filter.addSubFilter(new SaturationSubFilter(1.4f));
        return filter;
    }

    public static Filter getMayFairFilter() {
        Point[] rgbKnots = new Point[8];
        rgbKnots[0] = new Point(0f, 0f);
        rgbKnots[1] = new Point(34f, 6f);
        rgbKnots[2] = new Point(69f, 23f);
        rgbKnots[3] = new Point(100f, 58f);
        rgbKnots[4] = new Point(150f, 154f);
        rgbKnots[5] = new Point(176f, 196f);
        rgbKnots[6] = new Point(207f, 233f);
        rgbKnots[7] = new Point(255f, 255f);
        Filter filter = new Filter();
        filter.addSubFilter(new ToneCurveSubFilter(rgbKnots, null, null, null));
        return filter;
    }

    public static Filter getSierraFilter() {
        Point[] rgbKnots = new Point[2];
        Point[] redKnots = new Point[2];

        rgbKnots[0] = new Point(0f, 54f);
        rgbKnots[1] = new Point(255f, 255f);

        redKnots[0] = new Point(0f, 21f);
        redKnots[1] = new Point(255f, 255f);


        Filter filter = new Filter();
        filter.addSubFilter(new ToneCurveSubFilter(rgbKnots, redKnots, null, null));
        filter.addSubFilter(new ContrastSubFilter(1.33f));
        filter.addSubFilter(new BrightnessSubFilter(-30));
        return filter;
    }

    public static Filter getAmazonFilter() {
        Point[] blueKnots = new Point[6];
        blueKnots[0] = new Point(0f, 0f);
        blueKnots[1] = new Point(11f, 40f);
        blueKnots[2] = new Point(36f, 99f);
        blueKnots[3] = new Point(86f, 151f);
        blueKnots[4] = new Point(167f, 209f);
        blueKnots[5] = new Point(255f, 255f);
        Filter filter = new Filter();
        filter.addSubFilter(new ContrastSubFilter(1.2f));
        filter.addSubFilter(new ToneCurveSubFilter(null, null, null, blueKnots));
        return filter;
    }

    public static Filter getAdeleFilter() {
        Filter filter = new Filter();
        filter.addSubFilter(new SaturationSubFilter(-100f));
        return filter;
    }

    public static Filter getCruzFilter() {
        Filter filter = new Filter();
        filter.addSubFilter(new SaturationSubFilter(-100f));
        filter.addSubFilter(new ContrastSubFilter(1.3f));
        filter.addSubFilter(new BrightnessSubFilter(20));
        return filter;
    }

    public static Filter getMetropolisFilter() {
        Filter filter = new Filter();
        filter.addSubFilter(new SaturationSubFilter(-1f));
        filter.addSubFilter(new ContrastSubFilter(1.7f));
        filter.addSubFilter(new BrightnessSubFilter(70));
        return filter;
    }

    public static Filter getAudreyFilter() {
        Filter filter = new Filter();

        Point[] redKnots = new Point[3];
        redKnots[0] = new Point(0f, 0f);
        redKnots[1] = new Point(124f, 138f);
        redKnots[2] = new Point(255f, 255f);

        filter.addSubFilter(new SaturationSubFilter(-100f));
        filter.addSubFilter(new ContrastSubFilter(1.3f));
        filter.addSubFilter(new BrightnessSubFilter(20));
        filter.addSubFilter(new ToneCurveSubFilter(null, redKnots, null, null));
        return filter;
    }

    public static Filter getRiseFilter() {
        Point[] blueKnots = new Point[4];
        Point[] redKnots = new Point[4];

        blueKnots[0] = new Point(0f, 0f);
        blueKnots[1] = new Point(39f, 70f);
        blueKnots[2] = new Point(150f, 200f);
        blueKnots[3] = new Point(255f, 255f);

        redKnots[0] = new Point(0f, 0f);
        redKnots[1] = new Point(45f, 64f);
        redKnots[2] = new Point(170f, 190f);
        redKnots[3] = new Point(255f, 255f);

        Filter filter = new Filter();
        filter.addSubFilter(new ContrastSubFilter(1.9f));
        filter.addSubFilter(new BrightnessSubFilter(60));
//        filter.addSubFilter(VignetteSubfilter(ctx, 200))
        filter.addSubFilter(new ToneCurveSubFilter(null, redKnots, null, blueKnots));
        return filter;
    }

    public static Filter getMarsFilter() {
        Filter filter = new Filter();
        filter.addSubFilter(new ContrastSubFilter(1.5f));
        filter.addSubFilter(new BrightnessSubFilter(10));
        return filter;
    }

    public static Filter getAprilFilter() {
        Point[] blueKnots = new Point[4];
        Point[] redKnots = new Point[4];

        blueKnots[0] = new Point(0f, 0f);
        blueKnots[1] = new Point(39f, 70f);
        blueKnots[2] = new Point(150f, 200f);
        blueKnots[3] = new Point(255f, 255f);

        redKnots[0] = new Point(0f, 0f);
        redKnots[1] = new Point(45f, 64f);
        redKnots[2] = new Point(170f, 190f);
        redKnots[3] = new Point(255f, 255f);

        Filter filter = new Filter();
        filter.addSubFilter(new ContrastSubFilter(1.5f));
        filter.addSubFilter(new BrightnessSubFilter(5));
//        filter.addSubFilter(VignetteSubfilter(context, 150))
        filter.addSubFilter(new ToneCurveSubFilter(null, redKnots, null, blueKnots));
        return filter;
    }

    public static Filter getHaanFilter() {
        Point[] greenKnots = new Point[3];
        greenKnots[0] = new Point(0f, 0f);
        greenKnots[1] = new Point(113f, 142f);
        greenKnots[2] = new Point(255f, 255f);

        Filter filter = new Filter();
        filter.addSubFilter(new ContrastSubFilter(1.3f));
        filter.addSubFilter(new BrightnessSubFilter(60));
//        filter.addSubFilter(VignetteSubfilter(context, 200))
        filter.addSubFilter(new ToneCurveSubFilter(null, null, greenKnots, null));
        return filter;
    }

    public static Filter getOldManFilter() {
        Filter filter = new Filter();
        filter.addSubFilter(new BrightnessSubFilter(30));
        filter.addSubFilter(new SaturationSubFilter(0.8f));
        filter.addSubFilter(new ContrastSubFilter(1.3f));
//        filter.addSubFilter(VignetteSubfilter(context, 100))
        filter.addSubFilter(new ColorOverlaySubFilter(100, .2f, .2f, .1f));
        return filter;
    }

    public static Filter getClarendonFilter() {
        Point[] redKnots = new Point[4];
        Point[] greenKnots = new Point[4];
        Point[] blueKnots = new Point[4];

        redKnots[0] = new Point(0f, 0f);
        redKnots[1] = new Point(56f, 68f);
        redKnots[2] = new Point(196f, 206f);
        redKnots[3] = new Point(255f, 255f);


        greenKnots[0] = new Point(0f, 0f);
        greenKnots[1] = new Point(46f, 77f);
        greenKnots[2] = new Point(160f, 200f);
        greenKnots[3] = new Point(255f, 255f);


        blueKnots[0] = new Point(0f, 0f);
        blueKnots[1] = new Point(33f, 86f);
        blueKnots[2] = new Point(126f, 220f);
        blueKnots[3] = new Point(255f, 255f);

        Filter filter = new Filter();
        filter.addSubFilter(new ContrastSubFilter(1.5f));
        filter.addSubFilter(new BrightnessSubFilter(-10));
        filter.addSubFilter(new ToneCurveSubFilter(null, redKnots, greenKnots, blueKnots));
        return filter;
    }
}
