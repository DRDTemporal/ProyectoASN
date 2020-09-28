package com.proyecto.asn.proyectoasn.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;
import com.proyecto.asn.proyectoasn.R;
import com.proyecto.asn.proyectoasn.models.Constants;

public class VideoActivity extends AppCompatActivity {
    // Declaración de variables
    SimpleExoPlayer exoPlayer;
    PlayerView playerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_video);
        initialize();
        initializePlayer();
    }

    // Método para inicializar la vista del reproductor
    private void initialize() {
        playerView = findViewById(R.id.playerView);
    }

    // Método para inicializar el reproductor
    private void initializePlayer(){
        exoPlayer = new SimpleExoPlayer.Builder(this).build();
        exoPlayer.setPlayWhenReady(true);
        playerView.setPlayer(exoPlayer);


        try {
            Uri mp4Uri = obtenerVideoRandom();
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, getString(R.string.app_name)));
            DefaultExtractorsFactory extractorsFactory =
                    new DefaultExtractorsFactory().setMp4ExtractorFlags(Mp4Extractor.FLAG_WORKAROUND_IGNORE_EDIT_LISTS);

            MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory, extractorsFactory)
                    .createMediaSource(mp4Uri);
            exoPlayer.prepare(videoSource);
            exoPlayer.addListener(new Player.EventListener() {
                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    if (playbackState == ExoPlayer.STATE_ENDED){
                        calificarVideo();
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(this, R.string.error_video, Toast.LENGTH_SHORT).show();
        }

    }

    // Método qué envía a la actividad CalficiarVideoActivity
    private void calificarVideo() {
        startActivity(new Intent(this, CalficarVideoActivity.class));
        finish();
    }

    // Método para obtener la direccion de un vídeo al azar.
    private Uri obtenerVideoRandom() {
        int numeroVideos = Constants.DIRECTORIO_VIDEOS.length;
        int numeroRandom = (int) (numeroVideos * Math.random());
        Uri uriVideoRandom = RawResourceDataSource.buildRawResourceUri(Constants.DIRECTORIO_VIDEOS[numeroRandom]);
        return uriVideoRandom;
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
