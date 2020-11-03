package com.proyecto.asn.proyectoasn.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.proyecto.asn.proyectoasn.R;

import java.util.Timer;
import java.util.TimerTask;

public class InteraccionActivity extends AppCompatActivity  implements OnClickListener{
    MediaPlayer sonido;
    boolean bandera = false;
    boolean bandera1 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarVista();
        inicializarVistas();
        sonido =  MediaPlayer.create(this,R.raw.abdomen);
    }

    // Método para inicializar el modo de presentación
    private void inicializarVista() {
        if(MenuActivity.modoPresentacion==0){
            setContentView(R.layout.activity_interaccion);
        }else{
            setContentView(R.layout.activity_interaccion_nina);
        }

    }


    private void inicializarVistas() {
        findViewById(R.id.btnShow).setOnClickListener(this);
        findViewById(R.id.btnCabeza).setOnClickListener(this);
        findViewById(R.id.btnOjo1).setOnClickListener(this);
        findViewById(R.id.btnOjo2).setOnClickListener(this);
        findViewById(R.id.btnMano1).setOnClickListener(this);
        findViewById(R.id.btnMano2).setOnClickListener(this);
        findViewById(R.id.btnPecho).setOnClickListener(this);
        findViewById(R.id.btnAbdomen).setOnClickListener(this);
        findViewById(R.id.btnGenital).setOnClickListener(this);
        findViewById(R.id.btnRodilla1).setOnClickListener(this);
        findViewById(R.id.btnRodilla2).setOnClickListener(this);
        findViewById(R.id.btnPie1).setOnClickListener(this);
        findViewById(R.id.btnPie2).setOnClickListener(this);
        findViewById(R.id.btnEspalda).setOnClickListener(this);
        findViewById(R.id.btnCabello).setOnClickListener(this);
        findViewById(R.id.btnTrasero).setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnShow:
                startActivity(new Intent(InteraccionActivity.this, VideoActivity.class));
                break;
            case R.id.btnCabeza:
                sonarInteraccion(R.raw.cabeza);
                break;

            case R.id.btnOjo1:
            case R.id.btnOjo2:
                sonarInteraccion(R.raw.ojos);
                break;

            case R.id.btnMano1:
            case R.id.btnMano2:
                sonarInteraccion(R.raw.manos);
                break;

            case R.id.btnPecho:
                mostrarImgNO();
                if(MenuActivity.modoPresentacion==0){
                    sonarInteraccion(R.raw.pecho);
                }else{
                    sonarInteraccion(R.raw.busto);
                }
                break;

            case R.id.btnAbdomen:
                sonarInteraccion(R.raw.abdomen);
                break;

            case R.id.btnGenital:
                mostrarImgNO();
                if(MenuActivity.modoPresentacion==0){
                    sonarInteraccion(R.raw.pene);
                }else{
                    sonarInteraccion(R.raw.vagina);
                }
                break;

            case R.id.btnRodilla1:
            case R.id.btnRodilla2:
                sonarInteraccion(R.raw.rodillas);
                break;

            case R.id.btnPie1:
            case R.id.btnPie2:
                sonarInteraccion(R.raw.pies);
                break;

            case R.id.btnCabello:
                sonarInteraccion(R.raw.cabello);
                break;

            case R.id.btnEspalda:
                sonarInteraccion(R.raw.espalda);
                break;

            case R.id.btnTrasero:
                mostrarImgNO();
                sonarInteraccion(R.raw.trasero);
                break;
        }
    }

    private void mostrarImgNO(){
        findViewById(R.id.imgNO).setVisibility(View.VISIBLE);
        bandera = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(bandera1){
                                findViewById(R.id.imgNO).setVisibility(View.INVISIBLE);
                                bandera1 = false;
                            }

                        }
                    });
                }
            }
        }).start();

    }

    private void sonarInteraccion(int mp3Zona) {
        sonido.stop();
        sonido =  MediaPlayer.create(this,mp3Zona);
        sonido.start();
    }
}