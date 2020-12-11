package com.example.iot_security;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadpter2 extends FirebaseRecyclerAdapter<Persona, myadpter2.myviewHolder> {

    public myadpter2(@NonNull FirebaseRecyclerOptions<Persona> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder myviewHolder, int i, @NonNull Persona persona) {
        myviewHolder.header.setText(persona.getNombre()+" "+ persona.getEstado()+" Se Encuentra En Casa");


    }

    @NonNull
    @Override
    public myadpter2.myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diseno_usuarios,parent,false);
        return  new myadpter2.myviewHolder(view);
    }

    public class  myviewHolder extends RecyclerView.ViewHolder{

        ImageView img1;
        TextView header;
        public myviewHolder(@NonNull View itemView){
            super(itemView);

            img1=itemView.findViewById(R.id.img11);
            header=itemView.findViewById(R.id.txttitulo);

        }
    }
}

