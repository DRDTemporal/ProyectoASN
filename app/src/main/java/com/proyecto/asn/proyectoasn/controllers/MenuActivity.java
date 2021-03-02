package com.proyecto.asn.proyectoasn.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.proyecto.asn.proyectoasn.R;

import static android.view.View.*;

public class MenuActivity extends AppCompatActivity implements OnClickListener {

    public static DatabaseReference alumnoRef;
    public static byte modoPresentacion =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);
        inicializar();
        saludarUsuario();

    }

    // Método para inicializar los clicks de las imagenes de la vista
    private void inicializar() {
        findViewById(R.id.btnNino).setOnClickListener(this);
        findViewById(R.id.btnNina).setOnClickListener(this);
    }

    // Método el cual muestra un Toast de Bienvenida.
    private void saludarUsuario(){
        Toast.makeText(MenuActivity.this, R.string.welcome, Toast.LENGTH_SHORT).show();

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNino:
                modoPresentacion = 0;
                startActivity(new Intent(MenuActivity.this, InteraccionActivity.class));
                break;
            case R.id.btnNina:
                modoPresentacion = 1;
                startActivity(new Intent(MenuActivity.this, InteraccionActivity.class));
                break;
        }

    }
}
