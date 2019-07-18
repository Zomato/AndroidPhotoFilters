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

                ThumbnailItem starLitImage = new ThumbnailItem();
                starLitImage.image = thumbImage;
                starLitImage.filter = SampleFilters.getStarLitFilter();
                ThumbnailsManager.addThumb(starLitImage);

                ThumbnailItem blueMessImage = new ThumbnailItem();
                blueMessImage.image = thumbImage;
                blueMessImage.filter = SampleFilters.getBlueMessFilter();
                ThumbnailsManager.addThumb(blueMessImage);

                ThumbnailItem aweStruckImage = new ThumbnailItem();
                aweStruckImage.image = thumbImage;
                aweStruckImage.filter = SampleFilters.getAweStruckVibeFilter();
                ThumbnailsManager.addThumb(aweStruckImage);

                ThumbnailItem limeStutterImage = new ThumbnailItem();
                limeStutterImage.image = thumbImage;
                limeStutterImage.filter = SampleFilters.getLimeStutterFilter();
                ThumbnailsManager.addThumb(limeStutterImage);

                ThumbnailItem nightWhisperImage = new ThumbnailItem();
                limeStutterImage.image = thumbImage;
                nightWhisperImage.filter = SampleFilters.getNightWhisperFilter();
                ThumbnailsManager.addThumb(nightWhisperImage);

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
