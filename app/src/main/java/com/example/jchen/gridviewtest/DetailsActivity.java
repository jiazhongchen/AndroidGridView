package com.example.jchen.gridviewtest;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private VideoView videoView;
    private MediaController mediaControls;
    private int position = 0;
    //private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        /*
        ImageView imageView = (ImageView) findViewById(R.id.image);
        int bitmapID = getIntent().getIntExtra("image", R.drawable.sample_0);
        imageView.setImageResource(ImageAdapter.mThumbIds[bitmapID]);
        */

        imageView = (ImageView) findViewById(R.id.image_view);
        videoView = (VideoView) findViewById(R.id.video_view);

        String filename = getIntent().getStringExtra("BitmapImageFile");

        if ( filename.contains(".mp4") ) {
            imageView.setVisibility(View.INVISIBLE);
            videoView.setVideoURI(Uri.parse("file://" + filename));
            videoView.setMediaController(new MediaController(this));
            videoView.requestFocus();
            //videoView.start();
            /*videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer = mp;
                }
            });*/
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mediaPlayer) {
                    // close the progress bar and play the video
                    //if we have a position on savedInstanceState, the video playback should start from here
                    videoView.seekTo(position);
                    videoView.start();
                    /*if (position == 0) {
                        videoView.start();
                    } else {
                        //if we come from a resumed activity, video playback will be paused
                        videoView.pause();
                    }*/
                }
            });
        } else {
            Bitmap bmp = BitmapFactory.decodeFile(filename);
            videoView.setVisibility(View.INVISIBLE);
            imageView.setImageBitmap(bmp);
        }

        //try {
        //    FileInputStream is = openFileInput(filename);
        //    bmp = BitmapFactory.decodeStream(is);
        //    is.close();
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}

    }

    /*
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        //mMediaPlayer.start();
        Log.i("DetailsActivity", "on resume");
        videoView.resume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        //mMediaPlayer.pause();
        Log.i("DetailsActivity", "on pause");
        videoView.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        //mMediaPlayer.stop();
        Log.i("DetailsActivity", "on destroy");
        videoView.stopPlayback();
        super.onDestroy();
    }
    */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Position", videoView.getCurrentPosition());
        videoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("Position");
        videoView.seekTo(position);
    }

    @Override
    protected void onResume() {
        //mMediaPlayer.start();
        Log.i("DetailsActivity", "on resume");
        videoView.resume();
        super.onResume();
    }

}
