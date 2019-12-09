package com.example.aprendiz.proyectoclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Andrea y Oscar EDAM.
 */


public class AdaptadorCarrito extends BaseAdapter{
   // public static final String URL_FOTOS = "http://192.168.43.127/MiTiendaOnline/appCliente/assets/productos/";
    public static final String URL_FOTOS = "http://10.75.198.156/MiTiendaOnline/appCliente/assets/productos/";

    private LayoutInflater layoutInflater2;

    private ImageView Ivfoto;
    private TextView tvNombre,tvCantidad,tvPrecio;
    private TextView tvId;
    private List<Carrito> listaCarrito;
    private Context context;


    public AdaptadorCarrito(Context context, List<Carrito> listaCarrito){
        layoutInflater2=(LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.listaCarrito = listaCarrito;
        this.context = context;
    }

    @Override
    public int getCount() {

        return listaCarrito.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Se obtiene el libro por cada posicion de la lista
        Carrito ca= listaCarrito.get(i);
        //se asigna a la vista el layout que vamos a reutilizar
        if(view==null){
            view=layoutInflater2.inflate(R.layout.activity_elemento_carrito,null);
        }





        tvNombre=(TextView)view.findViewById(R.id.tv_nombre1);
        tvCantidad=(TextView)view.findViewById(R.id.tv_cantidad1);
        tvPrecio=(TextView)view.findViewById(R.id.tv_acumulado1);
        Ivfoto=(ImageView) view.findViewById(R.id.tv_foto);

        //Se asignan valores del libro a los objetos de la vista

        tvNombre.setText(ca.getNombre());
        Picasso.with(context).load(URL_FOTOS + ca.getFoto()).into(Ivfoto);
        tvCantidad.setText(String.valueOf(ca.getCantidad()));

        Integer Acumulado= ca.getCantidad()*ca.getPrecio();

        tvPrecio.setText(String.valueOf(Acumulado));

        return view;
    }
}
