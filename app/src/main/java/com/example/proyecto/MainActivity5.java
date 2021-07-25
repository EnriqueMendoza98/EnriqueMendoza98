package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity5 extends AppCompatActivity {
    EditText nombre,codigo,cantidad,categoria, estado;
    String str_codigo, str_categoria, str_estado, str_cantidad, str_nombre;
    String url = "https://glassier-hardware.000webhostapp.com/app/peticiones.php";
    int seguir=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        codigo = findViewById(R.id.edtCodP);
        categoria= findViewById(R.id.edtCategoria);
        estado = findViewById(R.id.edtEstado);
        nombre= findViewById(R.id.edtNomP);
        cantidad = findViewById(R.id.edtCantidad);
    }

    public void Next(View view) {
        /*Intent siguiente=new Intent(this, MainActivity6.class);
        startActivity(siguiente);*/
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity5.this);
        progressDialog.setMessage("Petición en proceso...");
        codigo = findViewById(R.id.edtCodP);
        categoria= findViewById(R.id.edtCategoria);
        estado = findViewById(R.id.edtEstado);
        nombre= findViewById(R.id.edtNomP);
        cantidad = findViewById(R.id.edtCantidad);

            if (codigo.getText().toString().equals("")) {
                Toast.makeText(MainActivity5.this, "Ingrese codigo de producto", Toast.LENGTH_LONG).show();
                seguir = 0;
            } else {
                seguir = 1;
            }
            if (nombre.getText().toString().equals("")) {
                Toast.makeText(MainActivity5.this, "Ingrese un nombre para el producto", Toast.LENGTH_LONG).show();
                seguir = 0;
            } else {
                seguir = 1;
            }
            if (categoria.getText().toString().equals("")) {
                Toast.makeText(MainActivity5.this, "Ingrese la categoria perteneciente del producto", Toast.LENGTH_LONG).show();
                seguir = 0;
            } else {
                seguir = 1;
            }
            if (cantidad.getText().toString().equals("")) {
                Toast.makeText(MainActivity5.this, "Ingrese una cantidad para producto", Toast.LENGTH_LONG).show();
                seguir = 0;
            } else {
                seguir = 1;
            }
            if (estado.getText().toString().equals("")) {
                Toast.makeText(MainActivity5.this, "Ingrese el estado: 1-Activo 2-Inactivo", Toast.LENGTH_LONG).show();
                seguir = 0;
            } else {
                seguir = 1;
            }


            if (seguir == 1) {
                progressDialog.show();
                str_codigo = codigo.getText().toString().trim();
                str_categoria= categoria.getText().toString().trim();
                str_estado = estado.getText().toString().trim();

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        codigo.setText("");
                        categoria.setText("");
                        estado.setText("");
                        Toast.makeText(MainActivity5.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity5.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("codigo", str_codigo);
                        params.put("categoria", str_categoria);
                        params.put("estado", str_estado);

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity5.this);
                requestQueue.add(request);
                //regresando a pantalla de categorias
                Intent siguiente=new Intent(this, MainActivity3.class);
                startActivity(siguiente);
            } else { }
    }
    public void Cancelacion(View view) {
        Intent cancelacion=new Intent(this, MainActivity4.class);
        startActivity(cancelacion);
    }
}