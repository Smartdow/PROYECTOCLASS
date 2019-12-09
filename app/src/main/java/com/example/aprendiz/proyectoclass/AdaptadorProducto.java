package com.example.aprendiz.proyectoclass;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
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

public class AdaptadorProducto extends BaseAdapter {

    //public static final String URL_FOTOS = "http://192.168.43.123:8080/MiTiendaOnline/appCliente/assets/productos/";
   // public static final String URL_FOTOS = "http://192.168.43.127/MiTiendaOnline/appCliente/assets/productos/";
    public static final String URL_FOTOS = "http://10.75.198.156/MiTiendaOnline/appCliente/assets/productos/";
    Button comprar1,btnMas,btnMenos;
    TextView count;
    int contador = 0;
    private LayoutInflater layoutInflater;
    private ImageView ivFoto;
    private TextView tvNombre;
    private TextView tvPrecio;
    private List<Producto> listaProductos;
    private Context context;


public AdaptadorProducto(Context context, List<Producto> listaProductos){
    layoutInflater=(LayoutInflater)context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE);
    this.listaProductos = listaProductos;
    this.context = context;
}

    @Override
    public int getCount() {

        return listaProductos.size();
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
        Producto p= listaProductos.get(i);
        //se asigna a la vista el layout que vamos a reutilizar
        if(view==null){
            view=layoutInflater.inflate(R.layout.elemento_lista,null);
        }


      //  btnMas=(Button)view.findViewById(R.id.btnMas);
        count = (TextView) view.findViewById(R.id.count);
      //  btnMenos=(Button)view.findViewById(R.id.btnMenos);



        //inicializar los objetos de la vista que vamos a usar
        ivFoto=(ImageView)view.findViewById(R.id.foto);


        tvNombre=(TextView)view.findViewById(R.id.tv_nombre);
        tvPrecio=(TextView)view.findViewById(R.id.tv_precio);
        //Se asignan valores del libro a los objetos de la vista
        tvNombre.setText(p.getNombreProducto());
        tvPrecio.setText(p.getPrecioProducto());

        Picasso.with(context).load(URL_FOTOS + p.getFotoProducto()).into(ivFoto);
        // ivFoto.setImageResource(p.getFotoProducto());


        return view;
    }




}
