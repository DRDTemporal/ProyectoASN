package com.proyecto.asn.proyectoasn.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyecto.asn.proyectoasn.R;
import com.proyecto.asn.proyectoasn.models.Alumno;
import com.proyecto.asn.proyectoasn.models.Constants;
import com.proyecto.asn.proyectoasn.models.RecyclerAdapterAlumnos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetalleCursoActivity extends AppCompatActivity implements OnClickListener {

    // Declaración de variables
    RecyclerView rvAlumnos;
    List<Alumno> listaAlumnos = new ArrayList<>();
    public static String CHILD_ALUMNO="";
    private DatabaseReference dbReference;
    private FirebaseUser user;
    DatabaseReference cursoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_curso);
        initialize();
        initializeFirebase();
        obtenerAlumnos();
    }

    // Método para inicializar las vistas
    private void initialize() {
        findViewById(R.id.fbNuevoAlumno).setOnClickListener(this);
        findViewById(R.id.btnEditar).setOnClickListener(this);
        findViewById(R.id.btnEliminar).setOnClickListener(this);
        rvAlumnos = findViewById(R.id.rvAlumnos);
    }

    private void initializeFirebase() {
        dbReference = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        cursoRef = dbReference.child(Constants.CHILD_PROFESOR).child(user.getUid()).child(Constants.CHILD_CURSOS).child(CursosActivity.CHILD_CURSO);
    }

    private void obtenerAlumnos(){

        cursoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Alumno> tmpListAlumnos = new ArrayList<>();
                Alumno tmpAlumno;
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    tmpAlumno = postSnapshot.getValue(Alumno.class);
                    tmpAlumno.setId(snapshot.getKey());
                    tmpListAlumnos.add(tmpAlumno);

                }
                listaAlumnos = tmpListAlumnos;
                ingresarDatosRvAlumnos();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ingresarDatosRvAlumnos(){
        RecyclerAdapterAlumnos  adapterT = new RecyclerAdapterAlumnos(listaAlumnos);
        rvAlumnos.setAdapter(adapterT);
        rvAlumnos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvAlumnos.setHasFixedSize(true);
        adapterT.setMlistener(new RecyclerAdapterAlumnos.OnCustomItemClickListener() {
            @Override
            public void itemClick(int position) {
                CHILD_ALUMNO = listaAlumnos.get(position).getId();
                startActivity(new Intent(DetalleCursoActivity.this,DetalleAlumnoActivity.class));

            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fbNuevoAlumno:
                nuevoAlumno();
                break;
            case R.id.btnEditar:
                editarCurso();
                break;

            case R.id.btnEliminar:
                eliminarCurso();
                break;
        }

    }




    private void nuevoAlumno() {
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
                    cursoRef.push().setValue(new Alumno(txtNombreAlumno.getText().toString(),txtApellidoAlumno.getText().toString()));
                    Toast.makeText(DetalleCursoActivity.this, getString(R.string.agregado_alumno), Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }else{
                    Toast.makeText(DetalleCursoActivity.this, getString(R.string.vacios_campos), Toast.LENGTH_SHORT).show();
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

    private void editarCurso() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.item_nuevo_curso);
        final EditText txtNombreCurso = dialog.findViewById(R.id.txtNombreCurso);
        final Button btnAgregar = dialog.findViewById(R.id.btnAgregar);
        final Button btnCancelar = dialog.findViewById(R.id.btnCancelar);

        btnAgregar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNombreCurso.getText().toString().isEmpty()){
                    cursoRef.child(Constants.CHILD_CURSO_NOMBRE).setValue(txtNombreCurso.getText().toString());
                    Toast.makeText(DetalleCursoActivity.this, getString(R.string.editado_clase), Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }else{
                    Toast.makeText(DetalleCursoActivity.this, getString(R.string.vacio_nombre_clase), Toast.LENGTH_SHORT).show();
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

    private void eliminarCurso() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.eliminar_curso);
        builder.setMessage(R.string.pregunta_eliminar_curso);

        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cursoRef.removeValue();
                Toast.makeText(DetalleCursoActivity.this, getString(R.string.afirmacion_eliminado_curso), Toast.LENGTH_SHORT).show();
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