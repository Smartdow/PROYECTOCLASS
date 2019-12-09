package com.example.aprendiz.proyectoclass;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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


public class Registro extends Activity {

    private ImageView ivFoto;
    private String documentos;
    private ImageButton ibCamera;
    private ImageButton ibG;
    private Gson gson;
    private EditText idTipoDocumento,documentoPersona,nombrePersona,apellidoPersona,
    correoPersona,telefonoPersona,avatarPersona,direccionPersona, usuario, password;
    private Button registrar;
    private TextView idTipoDocumentoNumb;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        ibCamera = findViewById(R.id.ib_camera);
        ibG = findViewById(R.id.ib_galleria);
        gson = new Gson();
        final String tipoIdent = getIntent().getStringExtra("tiposDocumentosBD");
        // Convertimos el JSON_ARRAY en una lista
        final List<tipoIdentificacion> tipoIdentificacionList = gson.fromJson(tipoIdent, new TypeToken<List<tipoIdentificacion>>(){}.getType());
        registrar=findViewById(R.id.btn_Registrar) ;
        spinner=(Spinner)findViewById(R.id.tipoDocumento);
        final String[] data1 = new String[tipoIdentificacionList.size()];
        for(int j = 0; j < tipoIdentificacionList.size();j++){
            data1[j] = tipoIdentificacionList.get(j).getTipoDeDocumento();}
        ArrayAdapter<String>adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data1);
        spinner.setAdapter(adaptador);

        idTipoDocumentoNumb=findViewById(R.id.idTipoDocumentoNumb);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner.getItemAtPosition(i);
                int position= 1;
                int id = position+i;

                String idTipoDocumentNumb= toString().valueOf(id);
                idTipoDocumentoNumb.setText(idTipoDocumentNumb);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              documentoPersona=findViewById(R.id.documentoPersona);
                nombrePersona=findViewById(R.id.nombrePersona);
                apellidoPersona=findViewById(R.id.apellidoPersona);
                correoPersona=findViewById(R.id.correoPersona);
                telefonoPersona=findViewById(R.id.telefonoPersona);
                direccionPersona=findViewById(R.id.direccionPersona);
                usuario=findViewById(R.id.Crearusuario);
                password=findViewById(R.id.crearPassword);
              if (documentoPersona.getText().toString().length()>0&&documentoPersona.getText().toString().length()==10&&
                        nombrePersona.getText().toString().length()>0 &&apellidoPersona.getText().toString().length()>0&&
                        correoPersona.getText().toString().length()>0 && correoPersona.toString().length() > 0 &&
                      telefonoPersona.getText().toString().length()>0 &&telefonoPersona.toString().length()>=10 &&
                        direccionPersona.getText().toString().length()>0&&usuario.getText().toString().length()>0&&password.getText().toString().length()>5){
                    validarSiExisteUsuario();

                    }else {

                  Toast.makeText(getApplicationContext(), "Ingrese datos validos", Toast.LENGTH_SHORT ).show();

                  if (documentoPersona.getText().toString().length()>0 &&documentoPersona.getText().toString().length()==10){
                      documentoPersona.setTextColor(getResources().getColor(R.color.blanco));
                  }else {

                      documentoPersona.setHintTextColor(getResources().getColor(R.color.colorAccent));
                      documentoPersona.setTextColor(getResources().getColor(R.color.colorAccent));
                  }
                    if (nombrePersona.getText().toString().length()>0){
                        nombrePersona.setTextColor(getResources().getColor(R.color.blanco));
                    }else {

                        nombrePersona.setHintTextColor(getResources().getColor(R.color.colorAccent));
                        nombrePersona.setTextColor(getResources().getColor(R.color.colorAccent));
                    }
                    if (apellidoPersona.getText().toString().length()>0 ){
                        apellidoPersona.setTextColor(getResources().getColor(R.color.blanco));
                    }else {

                        apellidoPersona.setHintTextColor(getResources().getColor(R.color.colorAccent));
                        apellidoPersona.setTextColor(getResources().getColor(R.color.colorAccent));
                    }
                    if (correoPersona.getText().toString().length()>0&& correoPersona.length() > 0){
                        correoPersona.setTextColor(getResources().getColor(R.color.blanco));
                    }else {

                        correoPersona.setHintTextColor(getResources().getColor(R.color.colorAccent));
                        correoPersona.setTextColor(getResources().getColor(R.color.colorAccent));
                    }
                  if (telefonoPersona.getText().toString().length()>0 &&telefonoPersona.getText().toString().length()==10){
                      telefonoPersona.setTextColor(getResources().getColor(R.color.blanco));
                  }else {

                      telefonoPersona.setHintTextColor(getResources().getColor(R.color.colorAccent));
                      telefonoPersona.setTextColor(getResources().getColor(R.color.colorAccent));
                  }
                    if (direccionPersona.getText().length()>0){
                        direccionPersona.setTextColor(getResources().getColor(R.color.blanco));
                    }else {

                        direccionPersona.setHintTextColor(getResources().getColor(R.color.colorAccent));
                        direccionPersona.setTextColor(getResources().getColor(R.color.colorAccent));
                    }
                    if (usuario.getText().length()>0){
                        usuario.setTextColor(getResources().getColor(R.color.blanco));
                    }else {

                        usuario.setHintTextColor(getResources().getColor(R.color.colorAccent));
                        usuario.setTextColor(getResources().getColor(R.color.colorAccent));
                    }
                    if (password.getText().toString().length()>5){
                        password.setTextColor(getResources().getColor(R.color.blanco));
                    }else{

                        password.setHintTextColor(getResources().getColor(R.color.colorAccent));
                        password.setTextColor(getResources().getColor(R.color.colorAccent));
                    }
                    }
            }
        });
    }



private void validarSiExisteUsuario(){


    documentoPersona=findViewById(R.id.documentoPersona);
    usuario=findViewById(R.id.Crearusuario);
    password=findViewById(R.id.crearPassword);

        //String url = "http://192.168.43.123:8080/miTiendaOnline/?c=AuthMovil";
        //String url = "http://192.168.43.127/miTiendaOnline/?c=PersonaMovil&a=actionValidar";
        String url = "http://10.75.198.156/miTiendaOnline/?c=PersonaMovil&a=actionValidar";
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                String[] retorno = response.trim().split("-");
                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG ).show();

                if(retorno[0].trim().equals("error")){
                    Toast.makeText(getApplicationContext(), retorno[1] , Toast.LENGTH_SHORT).show();
                }else{
                    if(retorno[0].trim().equals("existeiden")){

                        Toast.makeText(getApplicationContext(), "Este numero de identificacion ya esta registrado", Toast.LENGTH_SHORT).show();
                        documentoPersona.setHintTextColor(getResources().getColor(R.color.colorAccent));
                        documentoPersona.setTextColor(getResources().getColor(R.color.colorAccent));


                    }else{


                        documentoPersona.setHintTextColor(getResources().getColor(R.color.blanco));
                        documentoPersona.setTextColor(getResources().getColor(R.color.blanco));

                        if(retorno[0].trim().equals("existe")){

                            Toast.makeText(getApplicationContext(), "Este nombre de usuario ya existe", Toast.LENGTH_SHORT).show();
                            usuario.setHintTextColor(getResources().getColor(R.color.colorAccent));
                            usuario.setTextColor(getResources().getColor(R.color.colorAccent));


                        }else{
                            usuario.setHintTextColor(getResources().getColor(R.color.blanco));
                            usuario.setTextColor(getResources().getColor(R.color.blanco));
                            Registrar();
                        }

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
                params.put("documentoPersona", documentoPersona.getText().toString().trim());
                params.put("login", usuario.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }



    private  void Registrar(){
        ivFoto = findViewById(R.id.avatar);
        spinner=(Spinner)findViewById(R.id.tipoDocumento);
        documentoPersona=findViewById(R.id.documentoPersona);
        nombrePersona=findViewById(R.id.nombrePersona);
        apellidoPersona=findViewById(R.id.apellidoPersona);
        correoPersona=findViewById(R.id.correoPersona);
        telefonoPersona=findViewById(R.id.telefonoPersona);
        direccionPersona=findViewById(R.id.direccionPersona);
        usuario=findViewById(R.id.Crearusuario);
        password=findViewById(R.id.crearPassword);
        idTipoDocumentoNumb=findViewById(R.id.idTipoDocumentoNumb);
       // String url = "http://192.168.43.123:8080/miTiendaOnline/private/administrador/?c=PersonaMovil&a=actionCreate";
       // String url = "http://192.168.43.127/miTiendaOnline/?c=PersonaMovil&a=actionCreate";
        String url = "http://10.75.198.156/miTiendaOnline/?c=PersonaMovil&a=actionCreate";

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("parametros")){
                    Toast.makeText(getApplicationContext(), "No hay parametros" , Toast.LENGTH_SHORT).show();
                }else {
                    if (response.trim().equals("Registrado")) {
                        Intent intent= new Intent();
                        Toast.makeText(getApplicationContext(), "REGISTRADO", Toast.LENGTH_LONG ).show();
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "no encuentra ninguna respuesta", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error al conectarse".toString(), Toast.LENGTH_LONG ).show();
                Toast.makeText(getApplicationContext(), "Verifique su conexion a la red".toString(), Toast.LENGTH_LONG ).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idTipoDocumento", idTipoDocumentoNumb.getText().toString().trim());
                params.put("documentoPersona", documentoPersona.getText().toString().trim());
                params.put("nombrePersona", nombrePersona.getText().toString().trim());
                params.put("apellidoPersona", apellidoPersona.getText().toString().trim());
                params.put("correoPersona", correoPersona.getText().toString().trim());
                params.put("telefonoPersona", telefonoPersona.getText().toString().trim());
                params.put("direccionPersona", direccionPersona.getText().toString().trim());
                params.put("login", usuario.getText().toString().trim());
                params.put("password", password.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


}
