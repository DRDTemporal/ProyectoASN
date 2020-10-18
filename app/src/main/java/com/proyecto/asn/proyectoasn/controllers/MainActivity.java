package com.proyecto.asn.proyectoasn.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.asn.proyectoasn.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    // Declaración de constantes y variables.
    ImageView imageView;
    boolean bandera = true;
    int valor =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initialize();
        initializeFirebase();
        crearSplash();
    }

    public void onStart() {
        super.onStart();
    }

    // Método para inicializar las vistas y variables.
    private void initialize(){
        imageView = findViewById(R.id.imgSplash);
        imageView.setVisibility(View.INVISIBLE);
        bandera= true;
        valor=0;
    }

    // Método para inicializar Firebase.
    private void initializeFirebase() {
        FirebaseApp.initializeApp(this);
        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }catch (Exception ignored){

        }


    }

    // Método para la creacion del Splash.
    private void crearSplash(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            valor++;
                            if (valor==2){
                                animationSplash();
                                bandera=false;
                            }

                        }
                    });
                }
            }
        }).start();

    }

    // Método para la animación a la imagen.
    private void animationSplash(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            try {

                Animator animator = ViewAnimationUtils.createCircularReveal(imageView,0,imageView.getWidth(),0,imageView.getHeight()*1.5f);
                final Animator animator1 = ViewAnimationUtils.createCircularReveal(imageView,imageView.getWidth()/2,imageView.getHeight()/2,imageView.getHeight()*1.5f,0);
                animator.setDuration(800);
                animator1.setDuration(800);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        imageView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        animator1.start();
                    }
                });

                animator1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        imageView.setVisibility(View.INVISIBLE);
                        super.onAnimationEnd(animation);

                        intoLogin();
                    }
                });

                animator.start();


            }catch (Exception e){

                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        imageView.setVisibility(View.VISIBLE);
                        intoLogin();
                    }
                };
                new Timer().schedule(timerTask,1000);

            }


        }else {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    imageView.setVisibility(View.VISIBLE);
                    intoLogin();
                }
            };
            new Timer().schedule(timerTask,1000);
        }


    }

    /* Método para ingresar al login después de haber pasado el tiempo en el Slash
    */
    private void intoLogin() {
        Intent intent;
        intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
