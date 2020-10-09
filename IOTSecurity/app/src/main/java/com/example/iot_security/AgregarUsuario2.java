package com.example.iot_security;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AgregarUsuario2 extends AppCompatActivity {
    private EditText tnombre;
    private EditText tcorreo;
    private EditText tcontraseña;
    private EditText tTelefono;
    private Button btregistrar;

    private String nombre= "";
    private String correo= "";
    private String contraseña= "";
  private String Telefono = "";
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario2);

        mAuth= FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        tnombre=(EditText) findViewById(R.id.tvnombre);
        tcorreo=(EditText) findViewById(R.id.tvcorreo);
        tcontraseña=(EditText) findViewById(R.id.tvcontraseña);
        tTelefono=(EditText) findViewById(R.id.tvtelefono);
        btregistrar=(Button) findViewById(R.id.btn_resgistar2);



        btregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre=tnombre.getText().toString();
                correo=tcorreo.getText().toString();
                contraseña=tcontraseña.getText().toString();
                Telefono=tTelefono.getText().toString();



                if (!nombre.isEmpty() && !correo.isEmpty() && !contraseña.isEmpty() &&!Telefono.isEmpty()){

                    if(contraseña.length()>=6){
                        if(Telefono.length() == 9){
                        RegisterUser();
                        }else{
                            Toast.makeText(AgregarUsuario2.this, "Se requiere 9 digitos de telefono", Toast.LENGTH_SHORT).show();
                        }
                    }else{

                        Toast.makeText(AgregarUsuario2.this, "Contraseña minima 6 caracteres ", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(AgregarUsuario2.this, "complete los campos vacios ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void RegisterUser(){
        mAuth.createUserWithEmailAndPassword(correo, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Persona p = new Persona();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setCorreo(correo);
                    p.setContraseña(contraseña);
                    p.setTelefono(Telefono);

                    mDatabase.child("Usuarios").child(p.getUid()).setValue(p).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            Toast toast;
                            if (task2.isSuccessful()){
                                toast=Toast.makeText(AgregarUsuario2.this, "Se Registro al Usuario ", Toast.LENGTH_SHORT); toast.show();
                                if (toast!=null){
                                    tcontraseña.setText("");
                                    tcorreo.setText("");
                                    tnombre.setText("");
                                    tTelefono.setText("");
                                    tnombre.requestFocus();

                                }
                            }else{
                                Toast.makeText(AgregarUsuario2.this, "No se pudo registrar al usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(AgregarUsuario2.this, "No se pudo registar ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
