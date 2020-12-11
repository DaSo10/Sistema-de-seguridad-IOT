package com.example.iot_security;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Contactanos extends AppCompatActivity {
    TextView telefono,web,whats,face;

    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactanos);
        mDatabase= FirebaseDatabase.getInstance().getReference();

        telefono=findViewById(R.id.texttelefono);
        web=findViewById(R.id.textweb);
        whats=findViewById(R.id.textws);
        face=findViewById(R.id.textfac);

       mDatabase.child("Contacto").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String tel1=snapshot.child("telefono").getValue().toString();
                    String web1=snapshot.child("web").getValue().toString();
                    String whats1=snapshot.child("whatsapp").getValue().toString();
                    String face1=snapshot.child("facebook").getValue().toString();
                    telefono.setText(tel1);
                    web.setText(web1);
                    whats.setText(whats1);
                    face.setText(face1);

                }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

    }
}
