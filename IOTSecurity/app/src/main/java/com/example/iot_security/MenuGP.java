package com.example.iot_security;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MenuGP extends AppCompatActivity {
    CardView CaperPuerta,CconcultaPuerta;
    TextView NomRecepcionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gp);
        CaperPuerta=(CardView) findViewById(R.id.cardPuerta);
        CconcultaPuerta=(CardView) findViewById(R.id.cardConsulAperPuerta);

        NomRecepcionado=findViewById(R.id.txtnombrecap2);

        String dato = getIntent().getStringExtra("dato");
        NomRecepcionado.setText(dato);

        CaperPuerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuGP.this, GestionarPuerta.class);
                i.putExtra("dato", NomRecepcionado.getText().toString());
                startActivity(i);
            }
        });

        CconcultaPuerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuGP.this,ConsultarAPuerta.class));
            }
        });

    }
}
