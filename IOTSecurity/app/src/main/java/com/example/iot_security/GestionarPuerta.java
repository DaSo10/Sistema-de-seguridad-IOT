package com.example.iot_security;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GestionarPuerta extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference refUsuarios = database.getReference("Gestionar");

    DatabaseReference refGesPuerta, refAutorizacion, refPuerta, refGesSistema, refEstadoSistema;
    ToggleButton btnToggle,btnToggle2;
    TextView Mensaje1, Mensaje2, NomRecepcionado;
    private String Dt1,dato, fecha,Hora;
    DatabaseReference mDatabase;
    byte[] nbyte = {10,20,30};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_puerta);
        //referencia al hijo del home en este caso luces
        mDatabase= FirebaseDatabase.getInstance().getReference();

        Date date  = new Date();

        Mensaje1=findViewById(R.id.txtMen1);
        Mensaje2=findViewById(R.id.txtMen2);
        NomRecepcionado=findViewById(R.id.txtNombreRecep);

        dato= getIntent().getStringExtra("dato");
        NomRecepcionado.setText(dato);

        Dt1=NomRecepcionado.getText().toString();

        SimpleDateFormat fechac=new SimpleDateFormat("d 'de' MMMM 'del' yyyy");
        fecha=fechac.format(date);
        SimpleDateFormat horac=new SimpleDateFormat("h:mm a");
        Hora=horac.format(date);


        refGesPuerta= refUsuarios.child("GestionarPuerta");
        refGesSistema=refUsuarios.child("GestionarSistema");

        refAutorizacion= refGesPuerta.child("Autorizacion");
        refPuerta= refGesPuerta.child("Puerta");

        refEstadoSistema=refGesSistema.child("EstadoSistema");


        btnToggle = (ToggleButton)  findViewById(R.id.toggleButton);
        btnToggle.setTextOn("Pulse Para Desautorizar");
        btnToggle.setTextOff("Pulse Para Autorizar");

        btnToggle2 = (ToggleButton)  findViewById(R.id.toggleButton2);
        btnToggle2.setTextOn("Pulse Para Cerrar");
        btnToggle2.setTextOff("Pulse Para Abrir");


        Sistema(refEstadoSistema);



    }

    private void Sistema(final DatabaseReference refEstadoSistema){
        refEstadoSistema.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean estado_sistema = (Boolean) dataSnapshot.getValue();
                if (estado_sistema.equals(true)){

                    btnToggle.setEnabled(true);
                    btnToggle2.setEnabled(true);

                    controlAutorizacion(refAutorizacion, btnToggle);
                    controlPuerta(refPuerta, btnToggle2);
                }else if (estado_sistema.equals(false)){
                    btnToggle.setEnabled(false);
                    btnToggle2.setEnabled(false);
                    btnToggle.setText("Sistema Inactivo");
                    btnToggle2.setText("Sistema Inactivo");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void controlAutorizacion(final DatabaseReference refAutorizacion, final ToggleButton toggle_btn ) {

        toggle_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refAutorizacion.setValue(isChecked);
            }
        });

        refAutorizacion.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean estado_autorizacion = (Boolean) dataSnapshot.getValue();
                toggle_btn.setChecked(estado_autorizacion);
                if(estado_autorizacion){
                    toggle_btn.setTextOn("Pulse Para Desautorizar");
                    Mensaje1.setText("Esta autorizada La Apertura de Puerta");
                    btnToggle2.setEnabled(true);
                    refPuerta.setValue(false);
                } else {
                    toggle_btn.setTextOff("Pulse Para Autorizar");
                    Mensaje1.setText("No Esta autorizada La Apertura de Puerta");
                    btnToggle2.setEnabled(false);
                    refPuerta.setValue(false);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }

        });
    }

    private void controlPuerta(final DatabaseReference refPuerta, final ToggleButton toggle_btn2 ) {

        toggle_btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                refPuerta.setValue(isChecked);
            }
        });

        refPuerta.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean estado_led  = (Boolean) dataSnapshot.getValue();
                toggle_btn2.setChecked(estado_led);


                if(estado_led){
                    toggle_btn2.setTextOn("Pulse Para Cerrar");
                    Mensaje2.setText("La Puerta Se Encuentra Abierta");

                    toggle_btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Map<String, Object> p = new HashMap<>();
                            p.put("nombre", Dt1);
                            p.put("fecha", fecha+" a las "+Hora);
                            p.put("estado","Cerro la Puerta");
                            mDatabase.child("Estado").push().setValue(p);
                        }
                    });



                } else {
                    toggle_btn2.setTextOff("Pulse Para Abrir");
                    Mensaje2.setText("La Puerta Se Encuentra Cerrada");

                        toggle_btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Map<String, Object> p = new HashMap<>();
                                p.put("nombre", Dt1);
                                p.put("fecha", fecha+" a las "+Hora);
                                p.put("estado","Abrio la Puerta");
                                mDatabase.child("Estado").push().setValue(p);
                            }
                        });
                    }
                }


            @Override
            public void onCancelled(DatabaseError databaseError) { }

        });
    }



}
