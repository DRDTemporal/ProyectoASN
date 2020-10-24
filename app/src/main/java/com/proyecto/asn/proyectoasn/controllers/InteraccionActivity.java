package com.proyecto.asn.proyectoasn.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.proyecto.asn.proyectoasn.R;

public class InteraccionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaccion);
        findViewById(R.id.btnShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InteraccionActivity.this, VideoActivity.class));
            }
        });
    }
}