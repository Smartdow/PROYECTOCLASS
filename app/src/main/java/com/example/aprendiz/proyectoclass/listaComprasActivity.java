package com.example.aprendiz.proyectoclass;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Andrea y Oscar EDAM.
 */

public class listaComprasActivity extends Activity {

    Button EnviarBD;
    private List<Carrito> listaCarrito;
    private List<Integer> listFactura;
    // List<Factura> listaPaDactura;
    private Factura factura;
    String cantidadxprecio;
    private AdaptadorCarrito adaptadorCarrito;
    GridView lista;
    Gson gson;
    private Usuario usuario;
    private Carrito carrito;
    private TextView usuarioIniciado, totalView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_compras);
        usuarioIniciado=(TextView)findViewById(R.id.usuarioIniciado);
        lista=(GridView)findViewById(R.id.am_gv_gridviewDeseos1);
        totalView=(TextView)findViewById(R.id.total);
        gson = new Gson();
        if(getIntent()!= null) {
            String jsonCarrito = getIntent().getStringExtra("listaCarrito");
            listaCarrito = gson.fromJson(jsonCarrito, new TypeToken<List<Carrito>>(){}.getType());
            Log.i("jsonCarrito",jsonCarrito);
            String usuarioJson = getIntent().getStringExtra("usuario");
            usuario = gson.fromJson(usuarioJson, Usuario.class);
        }
        adaptadorCarrito =new AdaptadorCarrito(this, listaCarrito);
        lista.setAdapter(adaptadorCarrito);


        usuarioIniciado.setText(usuario.getLogin());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        final String fecha = dateFormat.format(date);
        //Recibe los datos





        //Para estar actualizando
        //aa.notifyDataSetChanged();
        int total=0;
        final List<String> listaC = new ArrayList<>();
        for(Carrito c : listaCarrito) {
            Integer Precio = c.getCantidad() * c.getPrecio();

            total=total+Precio;
            listaC.add(c.getNombre() + "   |   " + c.getCantidad() + "  |  " + Precio);


            totalView.setText(String.valueOf(total));
            factura = new Factura(listaCarrito, usuario.getIdPersona(), usuario.getIdUsuario(), fecha, total, 1);

        }



        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position= i;
                new AlertDialog.Builder(listaComprasActivity.this)
                        .setIcon(android.R.drawable.ic_delete).setTitle(" Eliminar")
                        .setMessage("confirmar eliminar").setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        listaC.remove(position);
                                        listaCarrito.remove(position);
                                        adaptadorCarrito.notifyDataSetChanged();

                                        int total=0;
                                        for(Carrito c : listaCarrito) {



                                            Integer Precio = c.getCantidad() * c.getPrecio();

                                            total=total+Precio;



                                            totalView.setText(String.valueOf(total));

                                            factura = new Factura(listaCarrito, usuario.getIdPersona(), usuario.getIdUsuario(), fecha, total, 1);

                                        }
                                        setResult(RESULT_OK);
                                    }}).show();
            }});



        EnviarBD=(Button)findViewById(R.id.EnviarBD);
        EnviarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                confirmaCompra();

               // Carrito car=listaCarrito.remove();


            }
        });


    }

    public void confirmaCompra(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_input_add)
                .setTitle(" Confirmar")
                .setMessage("Esta seguro de realizar esta compra?")
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //un listener al pulsar, cierra la aplicacioz
                               aniadir();
                                setResult(RESULT_OK);
                            }
                        }).show();
    }


    private  void aniadir(){
        final String jsonCarrito = gson.toJson(listaCarrito);
        final String jsonFactura = gson.toJson(factura);
        TextView vt;

        Bundle extras=getIntent().getExtras();

        final String Precio=extras.getString("precio");
        final String Cantidad=extras.getString("cantidad");


        //String url = "http://192.168.43.123:8080/miTiendaOnline/private/usuario/?c=FacturaMovil&a=createJson";
      //  String url = "http://192.168.43.127/miTiendaOnline/?c=FacturaMovil&a=createJson";
        String url = "http://10.75.198.156/miTiendaOnline/?c=FacturaMovil&a=createJson";
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.i("JSON", jsonFactura);
                if (response.trim().equals("exito")){


                    Intent intent = new Intent();
                    intent.putExtra("respuesta11", "1");

                    Toast.makeText(getApplicationContext(), "Compra Existosa!", Toast.LENGTH_SHORT ).show();

                    setResult(RESULT_OK, intent);
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(), "Ocurrio un error!" , Toast.LENGTH_SHORT ).show();
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
                params.put("jsonFactura", jsonFactura);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
