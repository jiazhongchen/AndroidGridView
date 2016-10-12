package com.example.jchen.gridviewtest;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private VideoView videoView;
    //private MediaController mediaControls;
    private int position = 0;
    ImageButton BtnShare;
    private Bitmap bmp = null;
    private String filename;
    //private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        hideActionBar();


        /*
        ImageView imageView = (ImageView) findViewById(R.id.image);
        int bitmapID = getIntent().getIntExtra("image", R.drawable.sample_0);
        imageView.setImageResource(ImageAdapter.mThumbIds[bitmapID]);
        */

        imageView = (ImageView) findViewById(R.id.image_view);
        videoView = (VideoView) findViewById(R.id.video_view);

        filename = getIntent().getStringExtra("BitmapImageFile");
/*
        File file = new File(filename);
        Uri uri = Uri.parse(file.getAbsolutePath());
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("/*");
        intent.setClassName("com.twitter.android", "com.twitter.android.PostActivity");
        intent.putExtra(Intent.EXTRA_TEXT, "This is a share message");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        startActivity(intent);*/

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
            bmp = BitmapFactory.decodeFile(filename);
            videoView.setVisibility(View.INVISIBLE);
            imageView.setImageBitmap(bmp);
        }

        BtnShare = (ImageButton) findViewById(R.id.share_button);
        BtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("video/*, image/*");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + filename));
                startActivity(Intent.createChooser(intent, "Share video"));

                /*if ( filename.contains(".mp4") ) {
                    intent.setType("video/*, image/*");
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + filename));
                    startActivity(Intent.createChooser(intent, "Share video"));
                } else {
                    intent.setType("image/*");
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                    File f = new File(filename);
                    try {
                        f.createNewFile();
                        FileOutputStream fo = new FileOutputStream(f);
                        fo.write(bytes.toByteArray());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + filename));
                    startActivity(Intent.createChooser(intent, "share image"));
                }*/

            }

        });
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

    public void hideActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
