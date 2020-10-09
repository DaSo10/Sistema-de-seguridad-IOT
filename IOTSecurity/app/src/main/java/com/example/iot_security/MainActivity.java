package com.example.iot_security;

import android.content.Intent;
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
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText tcorreo;
    private EditText tcontraseña;
    private Button btninicio;

    private String correo = "";
    private String contraseña= "";
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth= FirebaseAuth.getInstance();
        tcorreo=(EditText) findViewById(R.id.edtcorreo);
        tcontraseña=(EditText) findViewById(R.id.edtcontraseña);
        btninicio=(Button) findViewById(R.id.btnAgregar);

        btninicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo = tcorreo.getText().toString();
                contraseña = tcontraseña.getText().toString();

                if (!correo.isEmpty() && !contraseña.isEmpty()){
                    loginUser();
                }else
                    {
                Toast.makeText(MainActivity.this, "complete los campos vacios", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }
    private void loginUser(){
        mAuth.signInWithEmailAndPassword(correo, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(MainActivity.this, Menu.class));
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "no  se pudo iniciar sesion ", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
