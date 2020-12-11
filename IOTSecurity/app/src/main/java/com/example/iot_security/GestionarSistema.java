package com.example.iot_security;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GestionarSistema extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refSistema = database.getReference("Gestionar");
    DatabaseReference refGesSistema, refEstado;
    ToggleButton btnToggle;
    TextView Mensaje1;

    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_sistema);
        //referencia al hijo del home en este caso luces
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Mensaje1 = findViewById(R.id.txtMen3);

        refGesSistema = refSistema.child("GestionarSistema");
        refEstado = refGesSistema.child("EstadoSistema");

        btnToggle = (ToggleButton) findViewById(R.id.toggleButton3);
        btnToggle.setTextOn("Pulse Para Desactivar");
        btnToggle.setTextOff("Pulse Para Activar");

        controlSistema(refEstado, btnToggle);
    }

    private void controlSistema(final DatabaseReference refEstado, final ToggleButton toggle_btn3) {

        toggle_btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refEstado.setValue(isChecked);
            }
        });

        refEstado.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean estado_sistema = (Boolean) dataSnapshot.getValue();
                toggle_btn3.setChecked(estado_sistema);
                if (estado_sistema) {
                    toggle_btn3.setTextOn("Pulse Para Desactivar");
                    Mensaje1.setText("El Sistema Esta Activado");


                } else {
                    toggle_btn3.setTextOff("Pulse Para Activar");
                    Mensaje1.setText("El Sistema Esta Desactivado");


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }


}