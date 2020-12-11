package com.example.iot_security;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PreguntaCasa extends AppCompatActivity {
TextView Nombrerec,Idrecep;
Button Enviar;
RadioButton si,no;
String idCap = "";
String nomCap = "";
DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta_casa);

        mDatabase= FirebaseDatabase.getInstance().getReference();

        Nombrerec= findViewById(R.id.tvtnombrepreg);
        Idrecep=findViewById(R.id.txtidcap);
        Enviar=findViewById(R.id.btn_EnviarResp);
        si=findViewById(R.id.rbsi);
        no=findViewById(R.id.rbno);

        String dato = getIntent().getStringExtra("dato");
        Nombrerec.setText(dato);
        String dato2 = getIntent().getStringExtra("dato2");
        Idrecep.setText(dato2);

        idCap=Idrecep.getText().toString();
        nomCap=Nombrerec.getText().toString();

        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (si.isChecked() == true){
                    Map<String,Object> p = new HashMap<>();
                    p.put("estado", "Si");
                    mDatabase.child("Usuarios").child(idCap).updateChildren(p);
                    Intent i = new Intent(PreguntaCasa.this, Menu2.class);
                    i.putExtra("dato", nomCap);
                    i.putExtra("dato2", idCap);
                    startActivity(i);

                }else if (no.isChecked() == true){
                    Map<String,Object> p = new HashMap<>();
                    p.put("estado", "No");
                    mDatabase.child("Usuarios").child(idCap).updateChildren(p);
                    Intent i = new Intent(PreguntaCasa.this, Menu2.class);
                    i.putExtra("dato", nomCap);
                    i.putExtra("dato2", idCap);
                    startActivity(i);
                }

            }
        });
    }
}
