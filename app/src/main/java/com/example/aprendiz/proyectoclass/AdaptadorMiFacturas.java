package com.example.aprendiz.proyectoclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Andrea y Oscar EDAM.
 */


    public class AdaptadorMiFacturas  extends BaseAdapter {


        private LayoutInflater layoutInflater1;

        private TextView tvDescripcion, tvfecha;
        private TextView tvId;
        private List<MisFacturas> listaMisFacturas;
        private Context context;


        public AdaptadorMiFacturas(Context context, List<MisFacturas> listaMisFacturas){
            layoutInflater1=(LayoutInflater)context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            this.listaMisFacturas = listaMisFacturas;
            this.context = context;
        }

        @Override
        public int getCount() {

            return listaMisFacturas.size();
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
                MisFacturas c= listaMisFacturas.get(i);
            //se asigna a la vista el layout que vamos a reutilizar
            if(view==null){
                view=layoutInflater1.inflate(R.layout.elemento_mis_facturas,null);
            }



tvfecha=(TextView)view.findViewById(R.id.tv_nombre1);

            tvDescripcion=(TextView)view.findViewById(R.id.tv_idFactura);

            //Se asignan valores del libro a los objetos de la vista

            tvfecha.setText(c.getFechaFactura());
    Integer acumulado = c.getTotalFactura();

    tvDescripcion.setText(String.valueOf(acumulado));







            return view;
        }
    }

