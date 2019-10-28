package com.example.proyectoandroid.Adaptadores;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.Entidades.ReporteIncidente;
import com.example.proyectoandroid.Entidades.Vehiculo;
import com.example.proyectoandroid.R;

import java.util.ArrayList;

public class AdaptadorMotos extends RecyclerView.Adapter<AdaptadorMotos.ViewHolderMotos> implements View.OnClickListener{
    ArrayList<Vehiculo> listarvehiculos;
    public View.OnClickListener listener;
    public AdaptadorMotos(ArrayList<Vehiculo> listarvehiculos) {
        this.listarvehiculos = listarvehiculos;
    }
    @NonNull
    @Override
    public ViewHolderMotos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //asignamos la actividad que se repetira en el panel de incidentes con el recycler view
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lista_recycler_motos,null,false);
        view.setOnClickListener(this);
        return new ViewHolderMotos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMotos holder, int position) {
        if(listarvehiculos.get(position).getImage1()!=null){
            holder.img1.setImageBitmap(BitmapFactory.decodeByteArray(listarvehiculos.get(position).getImage1(),
                    0,listarvehiculos.get(position).getImage1().length));
        }else {
            holder.img1.setImageResource(R.drawable.sinimagen3);
        }


        holder.tv_placa.setText("Asunto: "+listarvehiculos.get(position).getPlaca());
        holder.tv_tipo.setText("Estado: "+listarvehiculos.get(position).getVehiculo());
        holder.tv_modelo.setText("Descripción: "+listarvehiculos.get(position).getModelo());
        holder.tv_color.setText(("Color: "+listarvehiculos.get(position).getColor()));
        holder.tv_cantidadrep.setText(("Cantidad de Reportes: "+listarvehiculos.get(position).getCantidadreportes()));
    }

    @Override
    public int getItemCount() {
        return listarvehiculos.size();
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }



    public class ViewHolderMotos extends RecyclerView.ViewHolder {
        TextView tv_placa,tv_tipo,tv_modelo,tv_color,tv_cantidadrep;
        ImageView img1;
        public ViewHolderMotos(@NonNull View itemView) {
            super(itemView);
            img1= (ImageView) itemView.findViewById(R.id.imageView44);
            tv_placa= (TextView) itemView.findViewById(R.id.edtplacas);
            tv_tipo= (TextView) itemView.findViewById(R.id.edttipos);
            tv_modelo= (TextView) itemView.findViewById(R.id.edtmodelos);
            tv_color= (TextView) itemView.findViewById(R.id.edtcolores);
            tv_cantidadrep= (TextView) itemView.findViewById(R.id.edtcantreportes);
        }
    }
}
