package com.example.iot_security;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarContra extends AppCompatActivity {

    private EditText mEmailRec;
    private Button RecP;

    private String Email="";

    private FirebaseAuth mAuth;

    private ProgressDialog mdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contra);

        mAuth=FirebaseAuth.getInstance();
        mdialog= new ProgressDialog(this);

        mEmailRec= (EditText) findViewById(R.id.tvusertest);
        RecP= (Button) findViewById(R.id.btn_Recutest);

        RecP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Email=mEmailRec.getText().toString();

                if(!Email.isEmpty()){
                    mdialog.setMessage("Espere un momento");
                    mdialog.setCanceledOnTouchOutside(false);
                    mdialog.show();
                    resetPassword();

                }else{
                    Toast.makeText(RecuperarContra.this, "Debe Ingresar el Correo", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void resetPassword(){
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()){
                Toast.makeText(RecuperarContra.this, "Se enviao el correo para restablecer la contraseña", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(RecuperarContra.this, "No se pudo enviar el correo para restablecer la contraseña", Toast.LENGTH_SHORT).show();
            }
            mdialog.dismiss();
            }
        });
    }
}
