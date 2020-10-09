package com.example.iot_security;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MenuGU extends AppCompatActivity {
CardView regUsuario,actUasuario,elUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gu);
        regUsuario=(CardView) findViewById(R.id.cardRegUsuario);
        actUasuario=(CardView) findViewById(R.id.cardActUsuario);
        regUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuGU.this, AgregarUsuario2.class));
            }
        });

        actUasuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuGU.this,ActualizarUsuario.class));
            }
        });


    }
}
