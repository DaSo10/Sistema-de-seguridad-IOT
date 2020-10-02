package com.example.iot_security;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AgregarUsuario2 extends AppCompatActivity {
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
        setContentView(R.layout.activity_agregar_usuario2);

        mAuth= FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        tnombre=(EditText) findViewById(R.id.tvnombre);
        tcorreo=(EditText) findViewById(R.id.tvcorreo);
        tcontraseña=(EditText) findViewById(R.id.tvcontraseña);
        btregistrar=(Button) findViewById(R.id.btn_resgistar2);

        btregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre=tnombre.getText().toString();
                correo=tcorreo.getText().toString();
                contraseña=tcontraseña.getText().toString();


                if (!nombre.isEmpty() && !correo.isEmpty() && !contraseña.isEmpty()){

                    if(contraseña.length()>=6){
                        RegisterUser();

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
                    Map<String ,Object> map= new HashMap<>();
                    map.put("nombre", nombre);
                    map.put("correo", correo);
                    map.put("contraseña", contraseña);

                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            Toast toast;
                            if (task2.isSuccessful()){
                                toast=Toast.makeText(AgregarUsuario2.this, "Se Registro al Usuario ", Toast.LENGTH_SHORT); toast.show();
                                if (toast!=null){
                                    tcontraseña.setText("");
                                    tcorreo.setText("");
                                    tnombre.setText("");
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
