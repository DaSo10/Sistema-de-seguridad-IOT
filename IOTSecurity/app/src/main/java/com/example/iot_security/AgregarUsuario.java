package com.example.iot_security;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AgregarUsuario extends AppCompatActivity {
    private EditText tnombre;
    private EditText tcorreo;
    private EditText tcontraseña;
    private Button btregistrar;

    private String nombre= "";
    private String correo= "";
    private String contraseña= "";
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        mAuth= FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();

        tnombre=(EditText) findViewById(R.id.edtNombre);
        tcorreo=(EditText) findViewById(R.id.edtcorreo);
        tcontraseña=(EditText) findViewById(R.id.edtcontraseña);
        btregistrar=(Button) findViewById(R.id.btnAgregar);

        btregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = tnombre.getText().toString();
                String correo = tcorreo.getText().toString();
                String password = tcontraseña.getText().toString();


                Persona p = new Persona();
                p.setUid(UUID.randomUUID().toString());
                p.setNombre(nombre);
                p.setCorreo(correo);
                p.setContraseña(password);
                mDatabase.child("Usuarios").child(p.getUid()).setValue(p);
                Toast.makeText(AgregarUsuario.this, "Agregado", Toast.LENGTH_LONG).show();

            }
        });
    }


}
