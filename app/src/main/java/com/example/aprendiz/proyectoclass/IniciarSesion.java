package com.example.aprendiz.proyectoclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
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

/**
 * Created by Andrea y Oscar EDAM.
 */

public class IniciarSesion extends Activity {

    Button btnLogin;
    EditText edtUserName, edtPassword;
    private String usuarioLogin, Productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fm_cuenta);
        btnLogin=(Button) findViewById(R.id.btn_Ingresar);
        edtUserName=(EditText)findViewById(R.id.et_Usuario);
        edtPassword=(EditText)findViewById(R.id.et_Contrasenia);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();

            }
        });
    }
    private  void Login(){



        //String url = "http://192.168.43.123:8080/miTiendaOnline/?c=AuthMovil";
       // String url = "http://192.168.43.127/miTiendaOnline/?c=AuthMovil";
        String url = "http://10.75.198.156/miTiendaOnline/?c=AuthMovil";
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                String[] retorno = response.trim().split("-");
              //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG ).show();

                if(retorno[0].trim().equals("error")){
                    Toast.makeText(getApplicationContext(), retorno[1] , Toast.LENGTH_SHORT).show();
                }else{
                    if(retorno[0].trim().equals("usuario")){
                        Toast.makeText(getApplicationContext(), "Bienvenid@ ".toString() + edtUserName.getText().toString(), Toast.LENGTH_SHORT).show();

                        usuarioLogin = retorno[1];
                        obtenerProductos();


                    }else{
                        Toast.makeText(getApplicationContext(), "Usuario no permitido" , Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "Error al conectarse".toString(), Toast.LENGTH_SHORT ).show();
                Toast.makeText(getApplicationContext(), "Verifique su conexion a la red".toString(), Toast.LENGTH_SHORT ).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("login", edtUserName.getText().toString().trim());
                params.put("password", edtPassword.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void obtenerProductos() {
        //String url = "http://192.168.43.123:8080/MiTiendaOnline/private/cliente/?c=ProductoMovil";
       // String url = "http://192.168.43.127/MiTiendaOnline/?c=ProductoMovil";
        String url = "http://10.75.198.156/MiTiendaOnline/?c=ProductoMovil";
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Productos=response;
                traercategorias();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "Error al conectarse".toString(), Toast.LENGTH_SHORT ).show();
                Toast.makeText(getApplicationContext(), "Verifique su conexion a la red".toString(), Toast.LENGTH_SHORT ).show();
            }
        });
        requestQueue.add(stringRequest);
    }
    private void traercategorias(){

        //String url="http://192.168.43.123:8080/miTiendaOnline/private/cliente/?c=TipoProductoMovil&a=actionCreate";
      // String url="http://192.168.43.127/miTiendaOnline/?c=TipoProductoMovil&a=actionCreate";
       String url="http://10.75.198.156/miTiendaOnline/?c=TipoProductoMovil&a=actionCreate";
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Intent intent = new Intent(IniciarSesion.this, MainActivity.class);
                intent.putExtra("tipoProductos", response);
                intent.putExtra("productos",Productos);
                intent.putExtra("usuario", usuarioLogin);
                intent.putExtra("respuesta11", "0");
                startActivity(intent);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "Error al conectarse".toString(), Toast.LENGTH_SHORT ).show();
                Toast.makeText(getApplicationContext(), "Verifique su conexion a la red".toString(), Toast.LENGTH_SHORT ).show();
            }
        });
        requestQueue.add(stringRequest);


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás

           Intent intent= new Intent(IniciarSesion.this, inicioPrincipalActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
