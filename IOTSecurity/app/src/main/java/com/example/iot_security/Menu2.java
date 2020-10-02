package com.example.iot_security;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu2 extends AppCompatActivity {
CardView GestionarUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        GestionarUsuario=(CardView) findViewById(R.id.cardGestUsuario);

        GestionarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu2.this, MenuGU.class));
            }
        });

    }
}
