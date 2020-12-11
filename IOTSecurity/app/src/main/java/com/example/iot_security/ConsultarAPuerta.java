package com.example.iot_security;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsultarAPuerta extends AppCompatActivity {
    private List<Persona2> listPerson = new ArrayList<Persona2>();
    ArrayAdapter<Persona2> arrayAdapterPersona;

    ListView listV_personas;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_apuerta);


        listV_personas = findViewById(R.id.lvlistar2);
        inicializarFirebase();
        listarDatos();



    }

    private void listarDatos() {
        databaseReference.child("Estado").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();

                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    Persona2 p = objSnaptshot.getValue(Persona2.class);
                    listPerson.add(p);

                    arrayAdapterPersona = new ArrayAdapter(ConsultarAPuerta.this, R.layout.user_list_item, listPerson);
                    listV_personas.setAdapter(arrayAdapterPersona);


                }
                Collections.reverse(listPerson);
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





}

