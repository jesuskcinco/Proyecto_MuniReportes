package com.example.proyectoandroid.Adaptadores;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.Entidades.Mensajes;

import com.example.proyectoandroid.R;

import java.util.ArrayList;

public class AdaptadorMensajes extends RecyclerView.Adapter <AdaptadorMensajes.ViewHolderMensajes> implements View.OnClickListener {
    ArrayList<Mensajes> listarmensajes;
    public View.OnClickListener listener;

    public AdaptadorMensajes(ArrayList<Mensajes> listarmensajes) {
        this.listarmensajes = listarmensajes;
    }

    @NonNull
    @Override
    public ViewHolderMensajes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lista_recycler_bandeja,null,false);
        view.setOnClickListener(this);
        return new ViewHolderMensajes(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMensajes holder, int position) {



        holder.tv_asunto.setText("Solicitud de la placa: "+listarmensajes.get(position).getPlaca());
        holder.tv_mensaje.setText("Mensaje: "+listarmensajes.get(position).getMensaje_not());
       // holder.tv_modelo.setText("Descripción: "+listarvehiculos.get(position).getModelo());
        //holder.tv_color.setText(("Color: "+listarvehiculos.get(position).getColor()));
        //holder.tv_cantidadrep.setText(("Cantidad de Reportes: "+listarvehiculos.get(position).getCantidadreportes()));
    }

    @Override
    public int getItemCount() {
        return listarmensajes.size();
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

    public class ViewHolderMensajes extends RecyclerView.ViewHolder {
        TextView tv_asunto,tv_mensaje;
        ImageView img1;
        public ViewHolderMensajes(@NonNull View itemView) {
            super(itemView);
            img1= (ImageView) itemView.findViewById(R.id.imageView444);
            tv_asunto= (TextView) itemView.findViewById(R.id.edtasunto2);
            tv_mensaje= (TextView) itemView.findViewById(R.id.edtmsj);
        }
    }
}
