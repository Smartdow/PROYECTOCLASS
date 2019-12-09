package com.example.aprendiz.proyectoclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Andrea y Oscar EDAM.
 */

public class ComprasRealizadas extends AppCompatActivity {


    private MisFacturas misFacturas;
    private Gson gson;
    private GridView listaMisFactura;
    private AdaptadorMiFacturas adaptadorMiFacturas;
    private TextView tv_idFactura;
    private List<MisFacturas> listaMisFacturas;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras_realizadas);

        gson = new Gson();


        if(getIntent()!= null) {

            String usuarioJson = getIntent().getStringExtra("usuario");
            usuario = gson.fromJson(usuarioJson, Usuario.class);
        }



      //  String url="http://192.168.43.127/miTiendaOnline/?c=FacturaMovil&a=traerFacturas";
        String url="http://10.75.198.156/miTiendaOnline/?c=FacturaMovil&a=traerFacturas";
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


               String mmisFacturas=response;

                listaMisFacturas= gson.fromJson(mmisFacturas, new TypeToken<List<MisFacturas>>(){}.getType());
                listaMisFactura = (GridView) findViewById(R.id.misfacturas);
               // String aqui=misFacturas.getNombreProducto();

                adaptadorMiFacturas=new AdaptadorMiFacturas(ComprasRealizadas.this, listaMisFacturas);
                listaMisFactura.setAdapter(adaptadorMiFacturas);
               // tv_idFactura=(TextView)findViewById(R.id.tv_iddFactura);
               // for (MisFacturas m:listaMisFacturas){
               //     tv_idFactura.setText(misFacturas.getIdFactura());
               // }

                listaMisFactura.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MisFacturas m = listaMisFacturas.get(i);
                String idFactura= String.valueOf(m.getIdFactura());
                String total = String.valueOf(m.getTotalFactura());
                String fecha = String.valueOf(m.getFechaFactura());

               Intent intent = new Intent(ComprasRealizadas.this, descripcionFactura.class);
                intent.putExtra("idFactura", idFactura);
                intent.putExtra("total", total);
                intent.putExtra("fecha", fecha);
                intent.putExtra("usuario", usuario.getLogin().toString());
              startActivity(intent);
            }
        });



              //  Toast.makeText(getApplicationContext(), mmisFacturas.toString(), Toast.LENGTH_SHORT ).show();

            }
        },  new Response.ErrorListener() {
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
            params.put("usuarioIdUsuario", usuario.getIdUsuario().toString());
           // Log.i("id", String.valueOf(usuarioIdUsuario));
            return params;
        }
    };
        requestQueue.add(stringRequest);



    }








    }





