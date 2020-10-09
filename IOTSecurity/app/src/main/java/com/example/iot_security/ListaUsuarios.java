package com.example.iot_security;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaUsuarios extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);
        mRecyclerView =(RecyclerView) findViewById(R.id.list_usuarios);
        new FirebaseDatabaseHelper().readUsuarios(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Persona> list_Person, List<String> keys) {

            }

            @Override
            public void DataIsUpdate() {

            }
        });
    }
}
