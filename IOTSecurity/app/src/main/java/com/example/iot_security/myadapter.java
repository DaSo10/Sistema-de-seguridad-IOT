package com.example.iot_security;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter  extends FirebaseRecyclerAdapter<model, myadapter.myviewHolder> {

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewHolder myviewHolder, int i, @NonNull final model model) {
            myviewHolder.header.setText(model.getName());

            myviewHolder.img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(myviewHolder.img1.getContext(),VerPDF.class);
                    intent.putExtra("name",model.getName());
                    intent.putExtra("url",model.getUrl());

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    myviewHolder.img1.getContext().startActivity(intent);
                }
            });
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diseno_libros,parent,false);
     return  new myviewHolder(view);
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
