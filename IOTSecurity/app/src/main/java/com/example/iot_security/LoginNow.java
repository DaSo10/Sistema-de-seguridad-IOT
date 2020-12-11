package com.example.iot_security;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginNow extends AppCompatActivity {
    private EditText tcorreo;
    private EditText tcontraseña;
    private TextView Reccontraseña;
    private Button btninicio;

    private String correo = "";
    private String contraseña= "";
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_now);

        mAuth= FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();

        tcorreo=(EditText) findViewById(R.id.tvusertest);
        tcontraseña=(EditText) findViewById(R.id.tvpasswordtest);
        btninicio=(Button) findViewById(R.id.btn_Recutest);
        Reccontraseña=(TextView)findViewById(R.id.forgot_password);

        Reccontraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginNow.this, RecuperarContra.class));
            }
        });

        btninicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo = tcorreo.getText().toString();
                contraseña = tcontraseña.getText().toString();

                if (!correo.isEmpty() && !contraseña.isEmpty()){
                    if (contraseña.length()>=6) {

                        loginUser();
                    }else{
                        Toast.makeText(LoginNow.this, "Contraseña minima 6 caracteres ", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(LoginNow.this, "complete los campos ", Toast.LENGTH_SHORT).show();
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