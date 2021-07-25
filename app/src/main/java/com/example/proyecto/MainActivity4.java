package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }
    /*public void Continuar(View view) {

    }*/

    //Pasando a la pantalla de agregar producto
    public void Agregar(View view) {
        Intent agregar=new Intent(this, MainActivity5.class);
        startActivity(agregar);
    }
    //Pasando a la pantalla de actualizar producto
    public void Actualizo(View view) {
        Intent actualizo=new Intent(this, MainActivity6.class);
        startActivity(actualizo);
    }

    public void Regturn(View view) {
        Intent regturn=new Intent(this, MainActivity3.class);
        startActivity(regturn);
    }
}