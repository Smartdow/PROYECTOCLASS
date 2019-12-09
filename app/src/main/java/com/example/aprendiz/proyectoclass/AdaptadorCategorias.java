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

public class AdaptadorCategorias extends BaseAdapter{


    private LayoutInflater layoutInflater1;

    private TextView tvDescripcion;
    private TextView tvId;
    private List<Categoria> listaCategorias;
    private Context context;


    public AdaptadorCategorias(Context context, List<Categoria> listaCategorias){
        layoutInflater1=(LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.listaCategorias = listaCategorias;
        this.context = context;
    }

    @Override
    public int getCount() {

        return listaCategorias.size();
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
        Categoria c= listaCategorias.get(i);
        //se asigna a la vista el layout que vamos a reutilizar
        if(view==null){
            view=layoutInflater1.inflate(R.layout.elemento_category,null);
        }





        tvDescripcion=(TextView)view.findViewById(R.id.tv_descripcion);

        //Se asignan valores del libro a los objetos de la vista
        tvDescripcion.setText(c.getDescripcionProducto());




        return view;
    }
}
