package com.proyecto.asn.proyectoasn.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.proyecto.asn.proyectoasn.R;

public class VideoActivity extends AppCompatActivity {
    SimpleExoPlayer exoPlayer;
    PlayerView playerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_video);
        initiailze();
        initializePlayer();
    }

    private void initiailze() {
        playerView = findViewById(R.id.playerView);
    }

    private void initializePlayer(){
        exoPlayer = new SimpleExoPlayer.Builder(this).build();
        exoPlayer.setPlayWhenReady(true);
        playerView.setPlayer(exoPlayer);
        Uri mp4Uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/molten-box-215004.appspot.com/o/Capsula10.mp4?alt=media&token=df8f927e-b804-4901-a6ef-1a9ed0be4c12");
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, getString(R.string.app_name)));
        DefaultExtractorsFactory extractorsFactory =
                new DefaultExtractorsFactory()
                        .setMp4ExtractorFlags(Mp4Extractor.FLAG_WORKAROUND_IGNORE_EDIT_LISTS);
        ProgressiveMediaSource progressiveMediaSource =
                new ProgressiveMediaSource.Factory(dataSourceFactory, extractorsFactory)
                        .createMediaSource(mp4Uri);


        MediaSource videoSource =progressiveMediaSource;
        exoPlayer.prepare(videoSource);




    }

    @Override
    protected void onResume() {
        super.onResume();
        playerView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        playerView.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        playerView.onPause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        playerView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exoPlayer.release();
    }
}
