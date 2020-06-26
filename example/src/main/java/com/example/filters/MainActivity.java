package com.example.filters;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.zomato.photofilters.SampleFilters;
import com.zomato.photofilters.imageprocessors.Filter;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ThumbnailCallback {
    static {
        System.loadLibrary("NativeImageProcessor");
    }

    private Activity activity;
    private RecyclerView thumbListView;
    private ImageView placeHolderImageView;
    private Uri imageUri;
    private Bitmap bitmap;
    private Bitmap filteredBitmap;

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
        bitmap = BitmapFactory.decodeResource(this.getApplicationContext().getResources(), R.drawable.photo);
        placeHolderImageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 640, 640, false));
        initHorizontalList();
        ImageView import_photo = (ImageView) findViewById(R.id.import_photo);
        ImageView rotate_left = (ImageView) findViewById(R.id.rotate_left);
        ImageView rotate_right = (ImageView) findViewById(R.id.rotate_right);
        ImageView save_photo = (ImageView) findViewById(R.id.save_photo);
        assert save_photo != null;
        save_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_photo();
            }
        });

        assert import_photo != null;
        import_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                import_photo();
            }
        });

        assert rotate_right != null;
        rotate_right.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(filteredBitmap, filteredBitmap.getWidth(), filteredBitmap.getHeight(), true);
                filteredBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
                bitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
                placeHolderImageView.setImageBitmap(filteredBitmap);
            }
        });

        assert rotate_left != null;
        rotate_left.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Matrix matrix = new Matrix();
                matrix.postRotate(-90);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(filteredBitmap, filteredBitmap.getWidth(), filteredBitmap.getHeight(), true);
                filteredBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
                bitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
                placeHolderImageView.setImageBitmap(filteredBitmap);
            }
        });
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
                Bitmap thumbImage = Bitmap.createScaledBitmap(bitmap, placeHolderImageView.getDrawable().getIntrinsicWidth(), placeHolderImageView.getDrawable().getIntrinsicHeight(), false);
                ThumbnailItem t1 = new ThumbnailItem();
                ThumbnailItem t2 = new ThumbnailItem();
                ThumbnailItem t3 = new ThumbnailItem();
                ThumbnailItem t4 = new ThumbnailItem();
                ThumbnailItem t5 = new ThumbnailItem();
                ThumbnailItem t6 = new ThumbnailItem();
                ThumbnailItem t7 = new ThumbnailItem();
                ThumbnailItem t8 = new ThumbnailItem();

                t1.image = thumbImage;
                t2.image = thumbImage;
                t3.image = thumbImage;
                t4.image = thumbImage;
                t5.image = thumbImage;
                t6.image = thumbImage;
                t7.image = thumbImage;
                t8.image = thumbImage;
                ThumbnailsManager.clearThumbs();
                ThumbnailsManager.addThumb(t1); // Original Image

                t2.filter = SampleFilters.getStarLitFilter();
                ThumbnailsManager.addThumb(t2);

                t3.filter = SampleFilters.getBlueMessFilter();
                ThumbnailsManager.addThumb(t3);

                t4.filter = SampleFilters.getAweStruckVibeFilter();
                ThumbnailsManager.addThumb(t4);

                t5.filter = SampleFilters.getLimeStutterFilter();
                ThumbnailsManager.addThumb(t5);

                t6.filter = SampleFilters.getNightWhisperFilter();
                ThumbnailsManager.addThumb(t6);

                t7.filter = SampleFilters.getMonoChromeFilter();
                ThumbnailsManager.addThumb(t7);

                t8.filter = SampleFilters.getVioletFilter();
                ThumbnailsManager.addThumb(t8);

                List<ThumbnailItem> thumbs = ThumbnailsManager.processThumbs(context);

                ThumbnailsAdapter adapter = new ThumbnailsAdapter(thumbs, (ThumbnailCallback) activity);
                thumbListView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };
        handler.post(r);
    }

    @Override
    public void onThumbnailClick(Filter filter)
    {
        filteredBitmap = filter.processFilter(Bitmap.createScaledBitmap(bitmap, placeHolderImageView.getDrawable().getIntrinsicWidth(), placeHolderImageView.getDrawable().getIntrinsicHeight(), false));
        placeHolderImageView.setImageBitmap(filteredBitmap);
    }

    private void save_photo()
    {
        Bitmap filteredBitmap = ((BitmapDrawable) placeHolderImageView.getDrawable()).getBitmap();
        MediaStore.Images.Media.insertImage(getContentResolver(), filteredBitmap,
                "photo_" + System.currentTimeMillis(), "");
        Toast.makeText(MainActivity.this, "saved in Pictures", Toast.LENGTH_SHORT).show();
    }

    private void import_photo()
    {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose Photo");
        builder.setItems(options, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int item)
            {
                if (options[item].equals("Take Photo"))
                {
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "New Picture");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                    imageUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null)
                        startActivityForResult(takePictureIntent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    startActivityForResult(gallery, 2);
                }
                else if (options[item].equals("Cancel"))
                {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == 1)
            {
                try
                {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    placeHolderImageView.setImageBitmap(bitmap);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                assert bitmap != null;
                bitmap = bitmap.copy(Bitmap.Config.ARGB_8888 , true);
                filteredBitmap = bitmap;
                bindDataToAdapter();
            }
            else if (requestCode == 2)
            {
                imageUri = data.getData();
                placeHolderImageView.setImageURI(imageUri);
                try
                {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                assert bitmap != null;
                bitmap = bitmap.copy(Bitmap.Config.ARGB_8888 , true);
                filteredBitmap = bitmap;
                bindDataToAdapter();
            }
        }
    }
}
