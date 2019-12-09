  package com.example.aprendiz.proyectoclass;

import android.content.Intent;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

  /**
   * Created by Andrea y Oscar EDAM.
   */

  public class EditarUsuarioActivity extends AppCompatActivity {
      private Gson gson;
      private Usuario usuario;
      private EditText et_Usuario, et_Contrasenia,et_confirmar;
      private Integer idUsuario;
      private String usuarioLogin, Productos;
      private String newLoginUsu, newPassUsu;
      private Button editar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        editar=(Button)findViewById(R.id.btn_Editar);
        et_Usuario=(EditText)findViewById(R.id.et_Usuario11);
        et_Contrasenia=(EditText)findViewById(R.id.et_Contrasenia_Nueva);
        et_confirmar=(EditText)findViewById(R.id.et_Contrasenia_Confirma);

        Gson gson= new Gson();
        if(getIntent()!= null) {
            String usuarioJson = getIntent().getStringExtra("usuario");
            usuario = gson.fromJson(usuarioJson, Usuario.class);
        }
        et_Usuario.setText(usuario.getLogin());

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_Contrasenia.getText().toString().equals(et_confirmar.getText().toString())){
                    Editar();
                }else
                {
                    et_Contrasenia.setHintTextColor(getResources().getColor(R.color.colorAccent));
                    et_Contrasenia.setTextColor(getResources().getColor(R.color.colorAccent));
                    et_confirmar.setHintTextColor(getResources().getColor(R.color.colorAccent));
                    et_confirmar.setTextColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(getApplicationContext(),"Debe confirmar la contraseña", Toast.LENGTH_LONG ).show();
                }
            }
        });
    }
//Servicios
      private  void Editar(){
          Gson gson= new Gson();

          if(getIntent()!= null) {
              String usuarioJson = getIntent().getStringExtra("usuario");
              usuario = gson.fromJson(usuarioJson, Usuario.class);
          }


          idUsuario=usuario.getIdUsuario();
          et_Usuario=(EditText)findViewById(R.id.et_Usuario11);
          et_Contrasenia=(EditText)findViewById(R.id.et_Contrasenia_Nueva);
          et_confirmar=(EditText)findViewById(R.id.et_Contrasenia_Confirma);

          String url = "http://10.75.198.156/miTiendaOnline/?c=usuarioMovil&a=actionEdit";
         // String url = "http://192.168.43.127/miTiendaOnline/?c=usuarioMovil&a=actionEdit";
          //String url = "http://192.168.43.123:8080/miTiendaOnline/private/usuario/?c=usuarioMovil&a=actionEdit";
          RequestQueue requestQueue= Volley.newRequestQueue(this);
          StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {



                  //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG ).show();

                  if(response.trim().equals("editado")){
                      Toast.makeText(getApplicationContext()," Editado", Toast.LENGTH_SHORT).show();
                      Login();


                  }else{
                      if(response.trim().equals("Error")){
                          Toast.makeText(getApplicationContext(), "No se ha podido editar".toString().toString(), Toast.LENGTH_SHORT).show();



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
                  params.put("idUsuario", idUsuario.toString().trim());
                  params.put("login", et_Usuario.getText().toString().trim());
                  params.put("password", et_Contrasenia.getText().toString().trim());
                  return params;
              }
          };
          requestQueue.add(stringRequest);
      }



      private  void Login(){

          et_Usuario=(EditText)findViewById(R.id.et_Usuario11);
          et_Contrasenia=(EditText)findViewById(R.id.et_Contrasenia_Nueva);

          newLoginUsu=et_Usuario.getText().toString();
          newPassUsu=et_Contrasenia.getText().toString();

        String url = "http://10.75.198.156/miTiendaOnline/?c=AuthMovil";

        //  String url = "http://192.168.43.123:8080/miTiendaOnline/?c=AuthMovil";

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
                  params.put("login", newLoginUsu);
                  params.put("password", newPassUsu);
                  return params;
              }
          };
          requestQueue.add(stringRequest);
      }


      private void obtenerProductos() {
        //  String url = "http://192.168.43.123:8080/MiTiendaOnline/private/cliente/?c=ProductoMovil";
          //String url = "http://192.168.43.127/MiTiendaOnline/?c=ProductoMovil";
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

          String url="http://10.75.198.156/miTiendaOnline/?c=TipoProductoMovil&a=actionCreate";
          RequestQueue requestQueue= Volley.newRequestQueue(this);
          StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {


                  Intent intent = new Intent(EditarUsuarioActivity.this, MainActivity.class);
                  intent.putExtra("tipoProductos", response);
                  intent.putExtra("productos",Productos);
                  intent.putExtra("usuario", usuarioLogin);
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




  }
