package com.example.aprendiz.proyectoclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
public class validarUsuarioAEditar extends AppCompatActivity {

    Button btnLogin;
    EditText edtUserName, edtPassword;
    private String usuarioLogin;
    String usuario;
    String password;
    String usuarioLocal;
    String passwordLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_usuario_aeditar);

        btnLogin=(Button) findViewById(R.id.btn_Ingresar);

         usuario = getIntent().getStringExtra("usuario");
         password = getIntent().getStringExtra("password");

        edtUserName=(EditText)findViewById(R.id.et_Usuario);
        edtPassword=(EditText)findViewById(R.id.et_Contrasenia);
        Log.i("Nombre", usuario);
        Log.i("Password", password);




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              //  Log.i("Nombre1", edtUserName.getText().toString());
              //  Log.i("Password1", edtPassword.getText().toString());


               if (usuario.equals(edtUserName.getText().toString())&&password.equals(edtPassword.getText().toString())){

                    Login();

               }else{

                   Toast.makeText(getApplicationContext(), "ingrese usuario/contraseña correctos" , Toast.LENGTH_SHORT).show();
               }

            }
        });


    }


    private  void Login(){

        btnLogin=(Button) findViewById(R.id.btn_Ingresar);
        edtUserName=(EditText)findViewById(R.id.et_Usuario);
        edtPassword=(EditText)findViewById(R.id.et_Contrasenia);



        //String url = "http://192.168.43.123:8080/miTiendaOnline/?c=AuthMovil";
        //String url = "http://192.168.43.127/miTiendaOnline/?c=AuthMovil";
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


                        usuarioLogin=retorno[1];
                        Intent intent = new Intent(validarUsuarioAEditar.this, EditarUsuarioActivity.class);
                        intent.putExtra("usuario", usuarioLogin);
                        startActivity(intent);



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
}
