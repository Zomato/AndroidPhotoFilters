package com.example.filters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.zomato.photofilters.SampleFilters;
import com.zomato.photofilters.imageprocessors.Filter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ThumbnailCallback {
    static {
        System.loadLibrary("NativeImageProcessor");
    }

    private Activity activity;
    private RecyclerView thumbListView;
    private ImageView placeHolderImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        initUIWidgets();
    }

    private void initUIWidgets() {
        thumbListView = (RecyclerView) findViewById(R.id.thumbnails);
        placeHolderImageView = (ImageView) findViewById(R.id.place_holder_imageview);
        placeHolderImageView.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getApplicationContext().getResources(), R.drawable.photo), 640, 640, false));
        initHorizontalList();
    }

    private void initHorizontalList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.scrollToPosition(0);
        thumbListView.setLayoutManager(layoutManager);
        thumbListView.setHasFixedSize(true);
        bindDataToAdapter();
    }

    private void bindDataToAdapter() {
        final Context context = this.getApplication();
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                Bitmap thumbImage = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.photo), 640, 640, false);

                ThumbnailItem originalImage = new ThumbnailItem();
                originalImage.image = thumbImage;
                ThumbnailsManager.clearThumbs();
                ThumbnailsManager.addThumb(originalImage); // Original Image

                ThumbnailItem defaultImage = new ThumbnailItem();
                defaultImage.image = thumbImage;
                defaultImage.filter = SampleFilters.getDefaultFilter();
                ThumbnailsManager.addThumb(defaultImage);

                ThumbnailItem starLitImage = new ThumbnailItem();
                starLitImage.image = thumbImage;
                starLitImage.filter = SampleFilters.getStarLitFilter();
                ThumbnailsManager.addThumb(starLitImage);

                ThumbnailItem clarendonImage = new ThumbnailItem();
                clarendonImage.image = thumbImage;
                clarendonImage.filter = SampleFilters.getClarendonFilter();
                ThumbnailsManager.addThumb(clarendonImage);

                ThumbnailItem seirraImage = new ThumbnailItem();
                seirraImage.image = thumbImage;
                seirraImage.filter = SampleFilters.getSierraFilter();
                ThumbnailsManager.addThumb(seirraImage);

                ThumbnailItem mayfairImage = new ThumbnailItem();
                mayfairImage.image = thumbImage;
                mayfairImage.filter = SampleFilters.getMayFairFilter();
                ThumbnailsManager.addThumb(mayfairImage);

                ThumbnailItem aweStruckVibeImage = new ThumbnailItem();
                aweStruckVibeImage.image = thumbImage;
                aweStruckVibeImage.filter = SampleFilters.getAweStruckVibeFilter();
                ThumbnailsManager.addThumb(aweStruckVibeImage);

                ThumbnailItem blueMessImage = new ThumbnailItem();
                blueMessImage.image = thumbImage;
                blueMessImage.filter = SampleFilters.getBlueMessFilter();
                ThumbnailsManager.addThumb(blueMessImage);

                ThumbnailItem limeStutterImage = new ThumbnailItem();
                limeStutterImage.image = thumbImage;
                limeStutterImage.filter = SampleFilters.getLimeStutterFilter();
                ThumbnailsManager.addThumb(limeStutterImage);

                ThumbnailItem nightWhisperImage = new ThumbnailItem();
                nightWhisperImage.image = thumbImage;
                nightWhisperImage.filter = SampleFilters.getNightWhisperFilter();
                ThumbnailsManager.addThumb(nightWhisperImage);

                ThumbnailItem amazonImage = new ThumbnailItem();
                amazonImage.image = thumbImage;
                amazonImage.filter = SampleFilters.getAmazonFilter();
                ThumbnailsManager.addThumb(amazonImage);

                ThumbnailItem audreyImage = new ThumbnailItem();
                audreyImage.image = thumbImage;
                audreyImage.filter = SampleFilters.getAudreyFilter();
                ThumbnailsManager.addThumb(audreyImage);

                ThumbnailItem riseImage = new ThumbnailItem();
                riseImage.image = thumbImage;
                riseImage.filter = SampleFilters.getRiseFilter();
                ThumbnailsManager.addThumb(riseImage);

                ThumbnailItem marsImage = new ThumbnailItem();
                marsImage.image = thumbImage;
                marsImage.filter = SampleFilters.getMarsFilter();
                ThumbnailsManager.addThumb(marsImage);

                ThumbnailItem aprilImage = new ThumbnailItem();
                aprilImage.image = thumbImage;
                aprilImage.filter = SampleFilters.getAprilFilter();
                ThumbnailsManager.addThumb(aprilImage);

                ThumbnailItem haanImage = new ThumbnailItem();
                haanImage.image = thumbImage;
                haanImage.filter = SampleFilters.getHaanFilter();
                ThumbnailsManager.addThumb(haanImage);

                ThumbnailItem oldManImage = new ThumbnailItem();
                oldManImage.image = thumbImage;
                oldManImage.filter = SampleFilters.getOldManFilter();
                ThumbnailsManager.addThumb(oldManImage);

                ThumbnailItem adeleImage = new ThumbnailItem();
                adeleImage.image = thumbImage;
                adeleImage.filter = SampleFilters.getAdeleFilter();
                ThumbnailsManager.addThumb(adeleImage);

                ThumbnailItem cruzImage = new ThumbnailItem();
                cruzImage.image = thumbImage;
                cruzImage.filter = SampleFilters.getCruzFilter();
                ThumbnailsManager.addThumb(cruzImage);

                ThumbnailItem metropolisImage = new ThumbnailItem();
                metropolisImage.image = thumbImage;
                metropolisImage.filter = SampleFilters.getMetropolisFilter();
                ThumbnailsManager.addThumb(metropolisImage);

                List<ThumbnailItem> thumbs = ThumbnailsManager.processThumbs(context);

                ThumbnailsAdapter adapter = new ThumbnailsAdapter(thumbs, (ThumbnailCallback) activity);
                thumbListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };
        handler.post(r);
    }

    @Override
    public void onThumbnailClick(Filter filter) {
        placeHolderImageView.setImageBitmap(filter.processFilter(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getApplicationContext().getResources(), R.drawable.photo), 640, 640, false)));
    }
}
