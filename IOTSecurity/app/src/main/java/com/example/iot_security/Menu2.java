package com.example.iot_security;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Menu2 extends AppCompatActivity {
CardView GestionarUsuario,GestionarPuerta,GestionarSistema,ManualUsuario, usuarioscasa,contactanos;
TextView NomRecepcionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        GestionarUsuario=(CardView) findViewById(R.id.cardGestUsuario);
        GestionarPuerta=(CardView) findViewById(R.id.cardGestionarPuerta);
        GestionarSistema=(CardView) findViewById(R.id.cardGestionarSistem);

        ManualUsuario=(CardView) findViewById(R.id.cardManualUsuario);
        usuarioscasa=(CardView) findViewById(R.id.cardUsuariosCasa);
        contactanos=(CardView) findViewById(R.id.cardContactanos);

        NomRecepcionado=findViewById(R.id.txtnombrecap);

        String dato = getIntent().getStringExtra("dato");
        NomRecepcionado.setText(dato);

        GestionarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu2.this, MenuGU.class));
            }
        });

        GestionarPuerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu2.this, MenuGP.class);
                i.putExtra("dato", NomRecepcionado.getText().toString());
                startActivity(i);
            }
        });

        GestionarSistema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu2.this, GestionarSistema.class));
            }
        });
        usuarioscasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu2.this, ListaUsuariosCasa.class));
            }
        });
        ManualUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu2.this, ManualUsuario.class));
            }
        });
        contactanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu2.this, Contactanos.class));
            }
        });

    }
}
