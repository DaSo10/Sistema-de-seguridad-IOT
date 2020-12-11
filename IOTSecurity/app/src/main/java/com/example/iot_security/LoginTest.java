package com.example.iot_security;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginTest extends AppCompatActivity {
    DatabaseReference databaseReference;
    private EditText tcorreo;
    private EditText tcontraseña;
    private TextView Reccontraseña;
    private Button btninicio;
    private String NomEnviado="";
    private String IdEnviado="";
    private String correo = "";
    private String contraseña= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test);

        tcorreo=(EditText) findViewById(R.id.tvusertest);
        tcontraseña=(EditText) findViewById(R.id.tvpasswordtest);
        btninicio=(Button) findViewById(R.id.btn_Recutest);


        databaseReference= FirebaseDatabase.getInstance().getReference();

        btninicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo =tcorreo.getText().toString().trim();
                contraseña=tcontraseña.getText().toString().trim();


                if (!correo.isEmpty() && !contraseña.isEmpty()) {
                         if (contraseña.length() >= 6) {
                            login();
                         } else {
                             Toast.makeText(LoginTest.this, "contraseña minima 6 caracteres", Toast.LENGTH_SHORT).show();
                         }
                     }else{
                         Toast.makeText(LoginTest.this, "complete los campos", Toast.LENGTH_SHORT).show();
                     }
            }

        });
    }
    private void login() {
        Query query = databaseReference.child("Usuarios").orderByChild("correo").equalTo(correo);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    Persona usersBean = user.getValue(Persona.class);

                    if (usersBean.getContraseña().equals(contraseña)) {
                        Toast.makeText(LoginTest.this, "succesful", Toast.LENGTH_SHORT).show();
                        finish();
                        NomEnviado=usersBean.getNombre();
                        IdEnviado=usersBean.getUid();
                        Intent i = new Intent(LoginTest.this, PreguntaCasa.class);
                        i.putExtra("dato", NomEnviado);
                        i.putExtra("dato2", IdEnviado);

                        startActivity(i);

                    } else {
                        Toast.makeText(LoginTest.this, "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
