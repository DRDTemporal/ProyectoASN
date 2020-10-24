package com.proyecto.asn.proyectoasn.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.proyecto.asn.proyectoasn.R;
import com.proyecto.asn.proyectoasn.models.Alumno;
import com.proyecto.asn.proyectoasn.models.Constants;

public class DetalleAlumnoActivity extends AppCompatActivity implements OnClickListener {

    // Declaración de variables
    TextView txtNombreAlumno, txtTestNoRealizado;
    ImageView imgTest;
    public static DatabaseReference alumnoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_alumno);
        initialize();
        initializeFirebaseRefs();
        getInfoAlumno();
    }

    // Método para inicializar las vistas
    private void initialize() {
        txtNombreAlumno = findViewById(R.id.txtNombreAlumno);
        txtTestNoRealizado = findViewById(R.id.txtTestNoRealizado);
        imgTest = findViewById(R.id.imgCalificacion);
        findViewById(R.id.btnEditar).setOnClickListener(this);
        findViewById(R.id.btnEliminar).setOnClickListener(this);
        findViewById(R.id.btnRealizarTest).setOnClickListener(this);
    }

    private void initializeFirebaseRefs() {
        alumnoRef = DetalleCursoActivity.alumnosRef.child(DetalleCursoActivity.CHILD_ALUMNO);
        MenuActivity.alumnoRef = alumnoRef;

    }

    private void getInfoAlumno(){
        alumnoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setInfoAlumno(snapshot.getValue(Alumno.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setInfoAlumno(Alumno alumno){
        txtNombreAlumno.setText(alumno.getNombreCompleto());
        if (alumno.getPuntuacion()==0){
            ponerVisibleSoloTxtTestNoRealizado();
        } else {
            ponerVisibleSoloImgTest();
            // TODO: agregar los emojis.
            switch (alumno.getPuntuacion()){
                case 1:
                    imgTest.setBackground(getResources().getDrawable(R.drawable.ctriste));
                    break;
                case 2:
                    imgTest.setBackground(getResources().getDrawable(R.drawable.cfeliz));
                    break;
                case 3:
                    imgTest.setBackground(getResources().getDrawable(R.drawable.cenojada));
                    break;

            }
        }

    }

    private void ponerVisibleSoloImgTest(){
        imgTest.setVisibility(View.VISIBLE);
        txtTestNoRealizado.setVisibility(View.INVISIBLE);
    }

    private void ponerVisibleSoloTxtTestNoRealizado(){
        txtTestNoRealizado.setVisibility(View.VISIBLE);
        imgTest.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEditar:
                editarAlumno();
                break;

            case R.id.btnEliminar:
                eliminarAlumno();
                break;

            case R.id.btnRealizarTest:
                enviarAInteraccionActivity();
        }
    }

    private void enviarAInteraccionActivity() {
        Intent intent = new Intent(DetalleAlumnoActivity.this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    private void editarAlumno() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.item_nuevo_alumno);
        final EditText txtNombreAlumno = dialog.findViewById(R.id.txtNombreAlumno);
        final EditText txtApellidoAlumno = dialog.findViewById(R.id.txtApellidoAlumno);
        final Button btnAgregar = dialog.findViewById(R.id.btnAgregar);
        final Button btnCancelar = dialog.findViewById(R.id.btnCancelar);

        btnAgregar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNombreAlumno.getText().toString().isEmpty() && !txtApellidoAlumno.getText().toString().isEmpty()){
                    alumnoRef.child(Constants.CHILD_ALUMNO_NOMBRE).setValue(txtNombreAlumno.getText().toString());
                    alumnoRef.child(Constants.CHILD_ALUMNO_APELLIDO).setValue(txtApellidoAlumno.getText().toString());
                    Toast.makeText(DetalleAlumnoActivity.this, getString(R.string.editado_alumno), Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }else{
                    Toast.makeText(DetalleAlumnoActivity.this, getString(R.string.vacios_campos), Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCancelar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();

    }

    private void eliminarAlumno() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.eliminar_curso);
        builder.setMessage(R.string.pregunta_eliminar_curso);

        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alumnoRef.removeValue();
                Toast.makeText(DetalleAlumnoActivity.this, getString(R.string.afirmacion_eliminado_alumno), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancelar,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}