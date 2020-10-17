package com.proyecto.asn.proyectoasn.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.proyecto.asn.proyectoasn.models.Curso;
import com.proyecto.asn.proyectoasn.models.RecyclerAdapterAlumnos;
import com.proyecto.asn.proyectoasn.models.RecyclerAdapterCursos;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.*;

public class CursosActivity extends AppCompatActivity implements OnClickListener {

    // Declaración de variables
    RecyclerView rvCursos;
    private DatabaseReference dbReference;
    private List<Curso> listaCursos;
    public static String CHILD_CURSO="";
    private FirebaseUser user;
    DatabaseReference cursosRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);
        findViewById(R.id.fbNuevaCurso).setOnClickListener(this);
        initialize();
        initializeFirebase();
        obtenerCursos();
    }

    private void initialize() {
        rvCursos = findViewById(R.id.rvCurso);
    }


    private void initializeFirebase() {
        dbReference = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        cursosRef = dbReference.child(Constants.CHILD_PROFESOR).child(user.getUid()).child(Constants.CHILD_CURSOS);
    }

    @Override
    public void onClick(View v) {
        if(R.id.fbNuevaCurso == v.getId()){
            nuevoCurso();
        }
    }
    private void obtenerCursos(){

        cursosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Curso> tmpListCursos = new ArrayList<>();
                Curso tmpCurso;
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    tmpCurso = postSnapshot.getValue(Curso.class);
                    tmpCurso.setId(snapshot.getKey());
                    tmpListCursos.add(tmpCurso);
                }
                listaCursos = tmpListCursos;
                ingresarDatosRvCursos();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ingresarDatosRvCursos() {
        RecyclerAdapterCursos adapterT = new RecyclerAdapterCursos(listaCursos);
        rvCursos.setAdapter(adapterT);
        rvCursos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvCursos.setHasFixedSize(true);
        adapterT.setMlistener(new RecyclerAdapterAlumnos.OnCustomItemClickListener() {
            @Override
            public void itemClick(int position) {
                CHILD_CURSO = listaCursos.get(position).getId();
                startActivity(new Intent(CursosActivity.this,DetalleCursoActivity.class));

            }
        });
    }

    private void nuevoCurso() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.item_nuevo_curso);
        final EditText txtNombreCurso = dialog.findViewById(R.id.txtNombreCurso);
        final Button btnAgregar = dialog.findViewById(R.id.btnAgregar);
        final Button btnCancelar = dialog.findViewById(R.id.btnCancelar);

        btnAgregar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNombreCurso.getText().toString().isEmpty()){
                    cursosRef.push().setValue(new Curso(txtNombreCurso.getText().toString()));
                    Toast.makeText(CursosActivity.this, getString(R.string.agregado_clase), Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }else{
                    Toast.makeText(CursosActivity.this, getString(R.string.vacio_nombre_clase), Toast.LENGTH_SHORT).show();
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
}
