package com.proyecto.asn.proyectoasn.controllers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyecto.asn.proyectoasn.R;
import com.proyecto.asn.proyectoasn.models.Constants;
import com.proyecto.asn.proyectoasn.models.Profesor;

public class RegistroActivity extends AppCompatActivity implements OnClickListener {

    // Declaración de variables
    private EditText txtEmail, txtContrasena, txtConfirmarContrasena, txtNombres, txtApellidos;
    private Button btnRegistrar, btnCancelar;
    private ProgressBar pbRegistrar;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        initialize();
        inizialiteFirebase();
    }

    // Método para la inicializacion de vistas,
    private void initialize() {
        txtEmail = findViewById(R.id.txtEmail);
        txtContrasena = findViewById(R.id.txtContrasena);
        txtConfirmarContrasena = findViewById(R.id.txtConfirmarContrasena);
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);

        pbRegistrar = findViewById(R.id.pbRegistrar);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnRegistrar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }

    // Método para inicializar la autentificación de Firebase.
    private void inizialiteFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

    }

    // Método el cual hará los procesos para el registro de usuario..
    private void registarUsuario() {
        if (compareToPasswords()) {
            createAccount(txtEmail.getText().toString(),txtContrasena.getText().toString());
        }

    }

    // Método encargado de crear la cuenta.
    private void createAccount(String email, String password) {
        showProgressDialog();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    databaseReference.child(Constants.CHILD_PROFESOR).child(user.getUid()).setValue(datosProfesor());
                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(RegistroActivity.this, "Error en la creación de usuario.",
                            Toast.LENGTH_SHORT).show();

                    updateUI(null);
                }

                // [START_EXCLUDE]
                hideProgressDialog();
                // [END_EXCLUDE]
            }
        });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = txtEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            txtEmail.setError(getString(R.string.este_valor_requerido));
            valid = false;
        } else {
            txtEmail.setError(null);
        }

        String password = txtContrasena.getText().toString();
        if (TextUtils.isEmpty(password)) {
            txtContrasena.setError(getString(R.string.este_valor_requerido));
            valid = false;
        } else {
            txtContrasena.setError(null);
        }

        String password1 = txtConfirmarContrasena.getText().toString();
        if (TextUtils.isEmpty(password1)) {
            txtConfirmarContrasena.setError(getString(R.string.este_valor_requerido));
            valid = false;
        } else {
            txtConfirmarContrasena.setError(null);
        }

        String nombres = txtNombres.getText().toString();
        if (TextUtils.isEmpty(nombres)) {
            txtNombres.setError(getString(R.string.este_valor_requerido));
            valid = false;
        } else {
            txtNombres.setError(null);
        }

        String apellidos = txtApellidos.getText().toString();
        if (TextUtils.isEmpty(apellidos)) {
            txtApellidos.setError(getString(R.string.este_valor_requerido));
            valid = false;
        } else {
            txtApellidos.setError(null);
        }

        return valid;

    }

    // Método para comparar que las contraseñas coincidan.
    private boolean compareToPasswords(){
        boolean valid = false;

        String password1 = txtContrasena.getText().toString();
        String password2 = txtConfirmarContrasena.getText().toString();

        if (validateForm()){
            if (password1.length()<8){
                txtContrasena.setError(getString(R.string.contrasena_muy_corta));
                Toast.makeText(this, R.string.contrasena_muy_corta_m2, Toast.LENGTH_SHORT).show();
                valid=false;
            }else {

                if (password1.equals(password2)) {
                    valid = true;
                } else {
                    txtConfirmarContrasena.setError(getString(R.string.contrasenas_no_coinciden));
                    txtConfirmarContrasena.setError(getString(R.string.contrasenas_no_coinciden));
                    Toast.makeText(this, R.string.contrasenas_no_coinciden, Toast.LENGTH_SHORT).show();
                    valid = false;
                }
            }

        }else {
            Toast.makeText(this, R.string.revise_datos, Toast.LENGTH_SHORT).show();
        }

        return valid;
    }

    // Método para mostrar el progressDialog como señal de carga e inhabilitar las vistas.
    private void showProgressDialog() {
        pbRegistrar.setVisibility(View.VISIBLE);
        btnRegistrar.setEnabled(false);
        btnCancelar.setEnabled(false);
        txtEmail.setEnabled(false);
        txtContrasena.setEnabled(false);
        txtConfirmarContrasena.setEnabled(false);
        txtNombres.setEnabled(false);
        txtApellidos.setEnabled(false);


    }

    // Método para ocultar el progressDialog como señal de terminación de carga y habilitar las vistas.
    private void hideProgressDialog() {
        pbRegistrar.setVisibility(View.INVISIBLE);
        btnRegistrar.setEnabled(true);
        btnCancelar.setEnabled(true);
        txtEmail.setEnabled(true);
        txtContrasena.setEnabled(true);
        txtConfirmarContrasena.setEnabled(true);
        txtNombres.setEnabled(true);
        txtApellidos.setEnabled(true);

    }

    // Método que permite verificar que el usuario ha sido creado.
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Toast.makeText(this, R.string.mensaje_de_inicio, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegistroActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();

        }

    }

    // Función que devuelve un objeto Profesor para cargar los datos de las vistas en ese dicho objeto.
    private Profesor datosProfesor(){
        Profesor data = new Profesor();
        data.setEmaill(txtEmail.getText().toString());
        data.setNombre(txtNombres.getText().toString());
        data.setApellido(txtApellidos.getText().toString());

        return data;
    }

    // Método para la utilización del onClick a los items.
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnRegistrar:
                registarUsuario();
                break;

            case R.id.btnCancelar:
                finish();
                break;


        }
    }

}
