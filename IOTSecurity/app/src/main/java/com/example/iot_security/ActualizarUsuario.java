package com.example.iot_security;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ActualizarUsuario extends AppCompatActivity {

    private List<Persona> listPerson = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;

    EditText nomP,tel,correoP,passwordP;
    ListView listV_personas;
    Button btnactulizar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String nombreU,telU,correoU,contraseñaU;
    Persona personaSelected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_usuario);

        nomP = findViewById(R.id.tvnombre);
        correoP = findViewById(R.id.tvcorreo);
        passwordP = findViewById(R.id.tvcontraseña);
        tel=findViewById(R.id.tvtelefono2);
        btnactulizar= findViewById(R.id.btn_resgistar2);
        listV_personas = findViewById(R.id.lvlistar);
        inicializarFirebase();
        listarDatos();

        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSelected = (Persona) parent.getItemAtPosition(position);
                nomP.setText(personaSelected.getNombre());
                correoP.setText(personaSelected.getCorreo());
                passwordP.setText(personaSelected.getContraseña());
                tel.setText(personaSelected.getTelefono());
            }
        });

        btnactulizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreU=nomP.getText().toString();
                correoU=correoP.getText().toString();
                contraseñaU=passwordP.getText().toString();
                telU=tel.getText().toString();

                if (!nombreU.isEmpty() &&!telU.isEmpty()) {

                        if (telU.length() == 9) {
                            Persona p = new Persona();
                            p.setUid(personaSelected.getUid());
                            p.setNombre(nomP.getText().toString().trim());
                            p.setTelefono(tel.getText().toString().trim());
                            p.setCorreo(correoP.getText().toString().trim());
                            p.setContraseña(passwordP.getText().toString().trim());
                            databaseReference.child("Usuarios").child(p.getUid()).setValue(p);
                            Toast.makeText(ActualizarUsuario.this, "los datos se han Actualizado", Toast.LENGTH_LONG).show();
                            limpiarCajas();
                        } else {
                            Toast.makeText(ActualizarUsuario.this, "se requiere 9 digitos de telefono ", Toast.LENGTH_LONG).show();
                        }

                }
            }
        });

    }

    private void listarDatos() {
        databaseReference.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();

                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Persona p = objSnaptshot.getValue(Persona.class);
                    listPerson.add(p);

                    arrayAdapterPersona = new ArrayAdapter<Persona>(ActualizarUsuario.this, R.layout.user_list_item, listPerson);
                    listV_personas.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }


    private void limpiarCajas() {
        nomP.setText("");
        correoP.setText("");
        passwordP.setText("");
        tel.setText("");
    }




}