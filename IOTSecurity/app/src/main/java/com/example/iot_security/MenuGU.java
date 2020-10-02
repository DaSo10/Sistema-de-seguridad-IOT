package com.example.iot_security;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuGU extends AppCompatActivity {
CardView regUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gu);
        regUsuario=(CardView) findViewById(R.id.cardRegUsuario);
        regUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuGU.this, AgregarUsuario2.class));
            }
        });

    }
}
