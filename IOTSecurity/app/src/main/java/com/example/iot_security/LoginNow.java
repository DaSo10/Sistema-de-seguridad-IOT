package com.example.iot_security;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginNow extends AppCompatActivity {
    private EditText tcorreo;
    private EditText tcontraseña;
    private Button btninicio;

    private String correo = "";
    private String contraseña= "";
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_now);

        mAuth= FirebaseAuth.getInstance();
        tcorreo=(EditText) findViewById(R.id.tvusername);
        tcontraseña=(EditText) findViewById(R.id.tvpassword);
        btninicio=(Button) findViewById(R.id.btn_login);

        btninicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo = tcorreo.getText().toString();
                contraseña = tcontraseña.getText().toString();

                if (!correo.isEmpty() && !contraseña.isEmpty()){
                    loginUser();
                }else
                {
                    Toast.makeText(LoginNow.this, "complete los campos vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void loginUser(){
        mAuth.signInWithEmailAndPassword(correo, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginNow.this, Menu2.class));
                    finish();
                } else {
                    Toast.makeText(LoginNow.this, "no  se pudo iniciar sesion ", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
