package com.example.proyecto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String ServerURL = "https://glassier-hardware.000webhostapp.com/app/peticiones.php?usuario=lilianac&clave=lilianac" ;
    EditText usuario, clave;
    int seguir = 0;
    String str_login, str_clave;
    String url = "https://glassier-hardware.000webhostapp.com/app/peticiones.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.edtCorreo);
        clave = findViewById(R.id.edtPassword);
    }

    public void Registro(View view) {//carga pantalla de registro de usuario
        Intent siguiente=new Intent(this, MainActivity2.class);
       startActivity(siguiente);
    }

    public void Siguiente(View view) {
       //validando usuario y contraseña
        usuario = findViewById(R.id.edtCorreo);
        clave = findViewById(R.id.edtPassword);

        if (usuario.getText().toString().equals("") || clave.getText().toString().equals("")) {
            //Toast.makeText(this, "Debe ingresar el usuario", Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, "Debe ingresar el usuario y password", Toast.LENGTH_SHORT).show();
            seguir = 0;
        } else {seguir = 1;};

        /*if (clave.getText().toString() == "") {
            Toast.makeText(this, "Debe ingresar el password", Toast.LENGTH_SHORT).show();
            seguir = 0;
        }*/
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Por favor espere...");
        if (seguir == 1) {
            str_login = usuario.getText().toString().trim();
            str_clave = clave.getText().toString().trim();
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    usuario.setText("");
                    clave.setText("");
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();

                    if (response.toString().equals("Inicio de sesion correcto")) {
                        cargarMenu();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this,  error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("verificarLogin", "");
                    params.put("usuario", str_login);
                    params.put("clave", str_clave);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(request);

        } else {

        }

    }

    public void cargarMenu() {
        //cargando menu cliente
        Intent siguiente=new Intent(this, MainActivity3.class);//menu de admin
        startActivity(siguiente);
    }
}