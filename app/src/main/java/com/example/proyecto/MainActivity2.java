package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.*;

public class MainActivity2 extends AppCompatActivity {
    //Button bimage;
    EditText nombre,login, clave, clave2, direccion, fechaN;
    String str_login, str_nombre, str_direccion, str_fecha, str_clave, str_clave2;
    String url = "https://glassier-hardware.000webhostapp.com/app/peticiones.php";
    int seguir = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nombre = findViewById(R.id.edtNomP);
        login = findViewById(R.id.edtCorreo);
        direccion = findViewById(R.id.editTextTextPersonName4);
        clave = findViewById(R.id.edtPassword);
        clave2 = findViewById(R.id.editTextTextPassword2);
        fechaN = findViewById(R.id.editTextDate);
    }

    public void Siguiente(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity2.this);
        progressDialog.setMessage("Por favor espere...");
        nombre = findViewById(R.id.edtNomP);
        login = findViewById(R.id.edtCorreo);
        direccion = findViewById(R.id.editTextTextPersonName4);
        clave = findViewById(R.id.edtPassword);
        clave2 = findViewById(R.id.editTextTextPassword2);
        fechaN = findViewById(R.id.editTextDate);

        if (nombre.getText().toString().equals("")) {
            Toast.makeText(MainActivity2.this, "Ingrese su nombre completo", Toast.LENGTH_LONG).show();
            seguir = 0;
        } else {
            seguir = 1;
        }
        if (clave.getText().toString().equals("")) {
            Toast.makeText(MainActivity2.this, "Ingrese su contraseña", Toast.LENGTH_LONG).show();
            seguir = 0;
        } else {
            seguir = 1;
        }
        if (!clave.getText().toString().equals(clave2.getText().toString())) {
            Toast.makeText(MainActivity2.this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            seguir = 0;
        } else {
            seguir = 1;
        }

        if (direccion.getText().toString().equals("")) {
            Toast.makeText(MainActivity2.this, "Ingrese su direccion", Toast.LENGTH_LONG).show();
            seguir = 0;
        } else {
            seguir = 1;
        }

        if (fechaN.getText().toString().equals("")) {
            Toast.makeText(MainActivity2.this, "Ingrese su fecha de nacimiento", Toast.LENGTH_LONG).show();
            seguir = 0;
        } else {
            seguir = 1;
        }

        if (seguir == 1) {
            progressDialog.show();
            str_nombre = nombre.getText().toString().trim();
            str_login = login.getText().toString().trim();
            str_direccion = direccion.getText().toString().trim();
            str_fecha = fechaN.getText().toString().trim();
            str_clave = clave.getText().toString().trim();
            str_clave2 = clave2.getText().toString().trim();

            StringRequest request = new StringRequest(Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    nombre.setText("");
                    login.setText("");
                    direccion.setText("");
                    fechaN.setText("");
                    clave.setText("");
                    clave2.setText("");
                    Toast.makeText(MainActivity2.this, response, Toast.LENGTH_LONG).show();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity2.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("nuevoUsu", "");
                    params.put("nombre", str_nombre);
                    params.put("login", str_login);
                    params.put("direccion", str_direccion);
                    params.put("fecha", str_fecha);
                    params.put("clave", str_clave);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);
            requestQueue.add(request);
            //yendo al login
            Intent siguiente=new Intent(this, MainActivity.class);
            startActivity(siguiente);

        } else {

        }
    }
}