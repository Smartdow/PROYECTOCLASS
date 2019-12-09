package com.example.aprendiz.proyectoclass;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.aprendiz.proyectoclass.R.id.Compras;
import static com.example.aprendiz.proyectoclass.R.id.menuSearch;

/**
 * Created by Andrea y Oscar EDAM.
 */

public class MainActivity extends AppCompatActivity {

    private static final int PROD = 101;
    private static final int COMP = 102;
    private static final int refreshCar = 103;



    private GridView lista, listaCatego;
    private AdaptadorProducto adaptadorProducto;
    private AdaptadorCategorias adaptadorCategoria;
    String nombre,precio;

    private String respuesta;
    private Integer idTipoProducto;
    private String foto;
    private Carrito carrito;
    private List<Carrito> listaCarrito;
    private List<Categoria> listaCategoria;
    private Gson gson;
    private Producto producto;
    private String usuario;
    private Categoria categoria;
    private Integer cambioIcon=0;
    ArrayAdapter <String>adapter;
    private   String productosCatego;
   private String idProductoComp;
    private int idProducto;
    private List<Producto> listaProductos;

    Button btnMas, btnMenos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();



        final String tipoProductos = getIntent().getStringExtra("tipoProductos");
        final String productos = getIntent().getStringExtra("productos");
        usuario = getIntent().getStringExtra("usuario");
        respuesta=getIntent().getStringExtra("respuesta11");


        // JSON OBJECT
          //categoria = gson.fromJson(tipoProductos, Categoria.class);
        // JSON ARRAY
        final List<Categoria> listaCategoria= gson.fromJson(tipoProductos, new TypeToken<List<Categoria>>(){}.getType());
        listaCatego = (GridView) findViewById(R.id.categoriaas);
        adaptadorCategoria=new AdaptadorCategorias(this, listaCategoria);
        listaCatego.setAdapter(adaptadorCategoria);


         listaProductos = gson.fromJson(productos, new TypeToken<List<Producto>>(){}.getType());
        lista = (GridView) findViewById(R.id.am_gv_gridview);
        adaptadorProducto = new AdaptadorProducto(this, listaProductos);
        lista.setAdapter(adaptadorProducto);
        lista.setTextFilterEnabled(true);
        lista.setFastScrollEnabled(true);


        carrito =new Carrito();
        listaCarrito = new ArrayList<>();


        listaCatego.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Categoria catego=listaCategoria.get(i);


                idTipoProducto=catego.getIdTipoProducto();






                //String url = "http://192.168.43.123:8080/miTiendaOnline/?c=AuthMovil";
               // String url = "http://192.168.43.127/miTiendaOnline/?c=ProductoMovil&a=productosPorCatego";
                String url = "http://10.75.198.156/miTiendaOnline/?c=ProductoMovil&a=productosPorCatego";
                final RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
                StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG ).show();


                        productosCatego=response;

                        final String productos = productosCatego;

                        final List<Producto> listaProductos = gson.fromJson(productos, new TypeToken<List<Producto>>(){}.getType());
                        lista = (GridView) findViewById(R.id.am_gv_gridview);
                        adaptadorProducto = new AdaptadorProducto(MainActivity.this, listaProductos);
                        lista.setAdapter(adaptadorProducto);



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
                        params.put("idTipoProducto", idTipoProducto.toString());

                        return params;
                    }
                };
                requestQueue.add(stringRequest);

            }
        });



        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Producto p= listaProductos.get(i);
              //saco los datos del item seleccionado
                idProducto=p.getIdProducto();
                nombre=p.getNombreProducto();
                precio=p.getPrecioProducto();
                foto=p.getFotoProducto();
               int itemPosition = i;
                String  itemValue = (String)lista.getItemAtPosition(i);

//Envio la informacion a descripcion_Producto
                Intent intent=new Intent(MainActivity.this, descripcion_ProductoActivity.class);

                intent.putExtra("usuario", usuario);
                intent.putExtra("idProducto",String.valueOf(idProducto));
                intent.putExtra("Nombre", nombre);
                intent.putExtra("Precio", precio);
                intent.putExtra("Foto", foto);

                startActivityForResult(intent, PROD);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){

            switch (requestCode) {
                case PROD:


                // respuesta = getIntent().getStringExtra("respuesta11");
                 //   Log.i("respuesta", respuesta);
                 /*   if (respuesta.trim().equals("1")) {
                        //
                    }else{*/
                    respuesta=data.getStringExtra("respuesta11");
                    Log.i("respuesta", respuesta);

                    if (respuesta.equals("1")){
                        listaCarrito.remove(carrito);
                        cambioIcon=1;

                    }else{
                        if (respuesta.equals("2")){
                            cambioIcon=2;

                            carrito = new Carrito();
                            carrito.setIdProducto(data.getIntExtra("idProducto", idProducto));
                            carrito.setNombre(data.getStringExtra("nombre"));
                            carrito.setFoto(data.getStringExtra("foto"));
                            carrito.setPrecio(data.getIntExtra("precio", 0));
                            carrito.setCantidad(data.getIntExtra("cantidad", 0));
                            listaCarrito.add(carrito);
                        }
                    }

                 /*for (Carrito c:listaCarrito) {
                    if (carrito.getIdProducto() == data.getIntExtra("idProducto", idProducto)) {
                        Log.i("asd", "asd");
                    } else {
                        String nombre=data.getStringExtra("nombre");
                        Toast.makeText(getApplicationContext(),nombre+" "+"añadido al carrito "+"" + " ", Toast.LENGTH_SHORT ).show();

                    }
                }*/


                    break;
                case COMP:





                    break;

                case refreshCar:


                    break;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
         MenuItem item=menu.findItem(R.id.menuSearch);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                String campo=newText;

//adapter.getFilter().filter(newText.toString());
             //   adapter.getFilter().convertResultToString(newText);



              //  String textto=lista.getTextFilter().toString();
             //z   Log.i("text", textto);
            //  adaptadorProducto.getView().getFilter().filter(newText);



                return false;
            }
        });

      /*  //extraemos el drawable en un bitmap

*/
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection


        switch (item.getItemId()) {


            case R.id.Carrito:
                //Bundle parametros = this.getIntent().getExtras();
                //final String usuario = parametros.getString("usuario");
                if (cambioIcon==2){

                    item.setIcon(R.mipmap.ic_launcher_car_mas);
                }
                if (cambioIcon==1){

                    item.setIcon(R.mipmap.ic_launcher_car);
                }

                Intent intent = new Intent(MainActivity.this, listaComprasActivity.class);
                intent.putExtra("listaCarrito", gson.toJson(listaCarrito));
                intent.putExtra("usuario", usuario);
                //startActivity(intent);
                startActivityForResult(intent, PROD);
                return true;
            case R.id.Ajustes:

                Intent intent1= new Intent(MainActivity.this, ajustesActivity.class);
                intent1.putExtra("usuario", usuario);
                startActivity(intent1);

                return true;

            case R.id.Compras:


                Intent intent2 = new Intent(MainActivity.this, ComprasRealizadas.class);
                intent2.putExtra("usuario", usuario);
                startActivity(intent2);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás

           // moveTaskToBack(true);

            gson = new Gson();

            final String productos = getIntent().getStringExtra("productos");
            final List<Producto> listaProductos = gson.fromJson(productos, new TypeToken<List<Producto>>(){}.getType());
            lista = (GridView) findViewById(R.id.am_gv_gridview);
            adaptadorProducto = new AdaptadorProducto(this, listaProductos);
            lista.setAdapter(adaptadorProducto);


            return true;
        }

        return super.onKeyDown(keyCode, event);
    }







}
