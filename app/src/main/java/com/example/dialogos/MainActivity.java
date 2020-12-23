package com.example.dialogos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements DialogoUsuario.OnDialogoConfirmacionListener{

    private         DialogoUsuario d;
    //private EditText usuario, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);/*
        usuario = findViewById(R.id.Usuario);
        contrasena = findViewById(R.id.Contrasena);*/
        d  = new DialogoUsuario();
        d.show(getSupportFragmentManager(),null);
/*        try {
            finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }*/
    }
//

/*    private void eventos (){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(usuario.getText().toString().equals("usuario1") && contrasena.getText().toString().equals("123456")){
                    Intent i = new Intent(MainActivity.this,PestanasMain.class);
                    startActivity(i);
                }
            }
        });
    }*/



    @Override
    public void onPossitiveButtonClick() {
        String usuario =d.getUsuario().getText().toString();

        if(usuario.equals("usuario1") && d.getContrasena().getText().toString().equals("123456")){
/*
        if(d.dameLogin()){
*/
            Toast.makeText(this, "Login correcto", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this,PestanasMain.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Login fallido", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onNegativeButtonClick() {

    }


}