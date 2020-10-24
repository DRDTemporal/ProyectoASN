package com.proyecto.asn.proyectoasn.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyecto.asn.proyectoasn.R;
import com.proyecto.asn.proyectoasn.models.Constants;
import com.proyecto.asn.proyectoasn.models.Profesor;

public class MenuActivity extends AppCompatActivity {

    public static DatabaseReference alumnoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        saludarUsuario();

    }
    // Método el cual muestra un Toast de Bienvenida.
    private void saludarUsuario(){
        Toast.makeText(MenuActivity.this, R.string.welcome, Toast.LENGTH_SHORT).show();

    }


}
