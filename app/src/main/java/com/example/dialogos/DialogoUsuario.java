package com.example.dialogos;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
public class DialogoUsuario extends DialogFragment  {

    private EditText usuario,contrasena;
    private DialogoUsuario.OnDialogoConfirmacionListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v  = inflater.inflate(R.layout.dialogo_usuario,null);
        usuario = v.findViewById(R.id.Usuario);
        contrasena = v.findViewById(R.id.Contrasena);
        builder.setView(v).
            setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                    listener.onPossitiveButtonClick();
            }
        });
        return builder.create();
    }

    public EditText getUsuario() {
        return usuario;
    }

    public EditText getContrasena() {
        return contrasena;
    }

    public boolean dameLogin(){
        boolean estaBien = false;
        if(usuario.getText().toString().equals("u") && contrasena.getText().toString().equals("u")){
            estaBien = true;
        }
        return  estaBien;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (DialogoUsuario.OnDialogoConfirmacionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +" no Implemento OnDialogoConfirmacionListener");
        }
    }



    public interface OnDialogoConfirmacionListener {
        void onPossitiveButtonClick(); //Eventos Botón Positivos
        void onNegativeButtonClick();
    }

}


