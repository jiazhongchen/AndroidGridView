package com.example.jchen.gridviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;


/**
 * Created by jchen on 10/10/16.
 */

public class ImageAdapter extends BaseAdapter {

    private static final int PADDING = 5;
    private static final int WIDTH = 250;
    private static final int HEIGHT = 250;

    private Context mContext;
    private ArrayList<String> mThumbIds;

    public ImageAdapter(Context context, ArrayList<String> ids) {
        this.mContext = context;
        this.mThumbIds = ids;
    }

    public int getCount() {
        return mThumbIds.size();
    }

    /*
    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    */

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        Bitmap myBitmap;
        ImageView imageView;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(WIDTH, HEIGHT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(PADDING, PADDING, PADDING, PADDING);
        } else {
            imageView = (ImageView) convertView;
        }

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inJustDecodeBounds = true;
        //BitmapFactory.decodeFile(mThumbIds.get(position), options);

        if(mThumbIds.get(position).contains(".jpg")) {
            myBitmap = BitmapFactory.decodeFile(mThumbIds.get(position), options); //Creation of Thumbnail of image
        } else if(mThumbIds.get(position).contains(".mp4")) {
            myBitmap = ThumbnailUtils.createVideoThumbnail(mThumbIds.get(position), 0); //Creation of Thumbnail of video
        } else {
            myBitmap = BitmapFactory.decodeFile(mThumbIds.get(position), options);
        }
        // Set inSampleSize
        //options.inSampleSize = 4;

        // Decode bitmap with inSampleSize set
        //options.inJustDecodeBounds = false;
        //myBitmap = BitmapFactory.decodeFile(mThumbIds.get(position), options);

        imageView.setImageBitmap(myBitmap);

        return imageView;
    }

    /*public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(this.mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageBitmap(this.mBitmapList.get(position));
        return imageView;
    }*/

    // references to our images
    /*public static final Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };*/

}
