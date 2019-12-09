package com.example.aprendiz.proyectoclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Andrea y Oscar EDAM.
 */


public class AdaptadorMuestraFactura  extends BaseAdapter {


    private LayoutInflater layoutInflater1;
   // public static final String URL_FOTOS = "http://192.168.43.127/MiTiendaOnline/appCliente/assets/productos/";
    public static final String URL_FOTOS = "http://10.75.198.156/MiTiendaOnline/appCliente/assets/productos/";

    private TextView tvNombre, tvCantidad, tvAcumulado;
    private ImageView photoImage;
    private TextView tvId;
    private List<DescripcionFacturaDetails> listaDesc;
    private Context context;


    public AdaptadorMuestraFactura(Context context, List<DescripcionFacturaDetails> listaDesc){
        layoutInflater1=(LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.listaDesc = listaDesc;
        this.context = context;
    }

    @Override
    public int getCount() {

        return listaDesc.size();
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
        DescripcionFacturaDetails des= listaDesc.get(i);
        //se asigna a la vista el layout que vamos a reutilizar
        if(view==null){
            view=layoutInflater1.inflate(R.layout.activity_adaptador_muestra_factura,null);
        }



        tvNombre=(TextView)view.findViewById(R.id.tv_nombre12);
        tvCantidad=(TextView)view.findViewById(R.id.tv_cantidad12);
tvAcumulado=(TextView)view.findViewById(R.id.tv_acumulado12) ;
        photoImage=(ImageView) view.findViewById(R.id.tv_foto1);

        //Se asignan valores del libro a los objetos de la vista

        tvNombre.setText(des.getNombreProducto());
        Picasso.with(context).load(URL_FOTOS + des.getFotoProducto()).into(photoImage);
        tvCantidad.setText(String.valueOf(des.getCantidad()));
        Integer acumulado= des.getCantidad()*des.getPrecioProducto();

        tvAcumulado.setText(String.valueOf(acumulado));






        return view;
    }
}

