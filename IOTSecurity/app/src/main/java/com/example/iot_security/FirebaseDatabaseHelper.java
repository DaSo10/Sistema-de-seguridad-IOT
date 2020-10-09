package com.example.iot_security;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private List<Persona> listPerson = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;
    FirebaseDatabase mDatabase;
    DatabaseReference mReferencePerson;

    public interface DataStatus{
        void DataIsLoaded(List<Persona> list_Person, List<String> keys );
        void DataIsUpdate();

    }
    public FirebaseDatabaseHelper (){
        mDatabase = FirebaseDatabase.getInstance();
        mReferencePerson= mDatabase.getReference("Usuarios");
    }
    public void readUsuarios(final DataStatus dataStatus){
        mReferencePerson.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Persona persona = keyNode.getValue(Persona.class);
                    listPerson.add(persona);
                }
                dataStatus.DataIsLoaded(listPerson,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updatePersona(String key, Persona persona, final DataStatus dataStatus){
        mReferencePerson.child(key).setValue(persona).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdate();
            }
        });
    }
}
