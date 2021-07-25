package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    //Metodo para poder pasar a la pantalla de ingreso de nueva categoria
    public void Ingresar(View view) {
        Intent ingresar=new Intent(this, MainActivity7.class);
        startActivity(ingresar);
    }

    //Metodo para poder pasar a la pantalla de ingreso de nueva categoria
    public void Actualizar(View view) {
        Intent actualizar=new Intent(this, MainActivity9.class);
        startActivity(actualizar);
    }

    //Metodo para poder salir y aparecer en el login
    public void Salir(View view) {

        this.finish();
    }

    //Para pasar a pantalla de productos en el algún dado caso haya seleccionado una categoria
    public void Mostrar(View view){
        Intent mostrar=new Intent(this, MainActivity4.class);
        startActivity(mostrar);
    }
}