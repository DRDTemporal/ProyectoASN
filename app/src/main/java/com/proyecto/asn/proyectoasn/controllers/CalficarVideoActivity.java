package com.proyecto.asn.proyectoasn.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.asn.proyectoasn.R;
import com.proyecto.asn.proyectoasn.models.Constants;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.*;

public class CalficarVideoActivity extends AppCompatActivity implements OnClickListener {

    // Declaración de lista para los botones.
    private List<ImageButton> btnsCalfiicaciones =new ArrayList();

    // Declaración de variables
    private DatabaseReference dbReference;
    private FirebaseUser user;
    private DatabaseReference alumnoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calficar_video);
        initialize();
        initializeFirebase();
        ingresarOnClickListeners();
    }

    // Método que agrega a una lista de ImageButton los botones correspondientes.
    private void initialize(){
        btnsCalfiicaciones.add((ImageButton) findViewById(R.id.btnCalificacion1));
        btnsCalfiicaciones.add((ImageButton) findViewById(R.id.btnCalificacion2));
        btnsCalfiicaciones.add((ImageButton) findViewById(R.id.btnCalificacion3));
        btnsCalfiicaciones.add((ImageButton) findViewById(R.id.btnCalificacion4));
        btnsCalfiicaciones.add((ImageButton) findViewById(R.id.btnCalificacion5));
    }

    // Método para ingresar las escuchas de las imagenes.
    private void ingresarOnClickListeners(){
        for ( ImageButton btn: btnsCalfiicaciones)  {
            btn.setOnClickListener(this);
        }
    }

    // Método para inicializar Firebase.
    private void initializeFirebase() {
        dbReference = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        alumnoRef = MenuActivity.alumnoRef;
    }

    // Método que permite activar o desactivar los botones (true = activados; false = desactivados)
    private void cambiarEstadoBoton(boolean estado){
        for ( ImageButton btn: btnsCalfiicaciones)  {
            btn.setEnabled(estado);
        }
    }

    // Método sobreescrito que permite saber a que vista se le está dando click.
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCalificacion1:
                ingresarCalificacion(1);
                break;

            case R.id.btnCalificacion2:
                ingresarCalificacion(2);
                break;

            case R.id.btnCalificacion3:
                ingresarCalificacion(3);
                break;

            case R.id.btnCalificacion4:
                ingresarCalificacion(4);
                break;

            case R.id.btnCalificacion5:
                ingresarCalificacion(5);
                break;


        }

    }

    // Método que ingresa a la base de datos la calificación, con el nombre del usuario.
    private void ingresarCalificacion(int calificacion) {
        alumnoRef.child(Constants.CHILD_ALUMNO_PUNTUACION).setValue(calificacion);
        cambiarEstadoBoton(false);
        finish();
    }


}