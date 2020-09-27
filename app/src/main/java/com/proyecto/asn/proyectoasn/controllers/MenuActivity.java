package com.proyecto.asn.proyectoasn.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.proyecto.asn.proyectoasn.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        saludarUsuario();
        findViewById(R.id.btnShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, VideoActivity.class));
            }
        });
    }

    // Método el cual muestra un Toast de Bienvenida.
    private void saludarUsuario(){
        Toast.makeText(MenuActivity.this, R.string.welcome, Toast.LENGTH_SHORT).show();

    }

}
