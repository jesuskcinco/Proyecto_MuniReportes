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
import com.example.proyectoandroid.R;

import java.util.ArrayList;

public class AdaptadorReportes extends RecyclerView.Adapter<AdaptadorReportes.ViewHolderReportInc> implements View.OnClickListener {
    ArrayList<ReporteIncidente> listarreportesinc;
    public View.OnClickListener listener;
    //creamos un array y su constructor para recibir el parametro del recycler view
    public AdaptadorReportes(ArrayList<ReporteIncidente> listarreportesinc) {
        this.listarreportesinc = listarreportesinc;
    }

    @NonNull
    @Override
    public ViewHolderReportInc onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //asignamos la actividad que se repetira en el panel de incidentes con el recycler view
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listar_recycler,null,false);
        view.setOnClickListener(this);
        return new ViewHolderReportInc(view);
    }
    //comunicacion del apaptador al recyclerview
    @Override
    public void onBindViewHolder(@NonNull ViewHolderReportInc holder, int position) {
        if(listarreportesinc.get(position).getImage1()!=null){
            holder.img1.setImageBitmap(BitmapFactory.decodeByteArray(listarreportesinc.get(position).getImage1(),
                    0,listarreportesinc.get(position).getImage1().length));
        }else {
            holder.img1.setImageResource(R.drawable.sinimagen3);
        }


        holder.tv_asunto.setText("Asunto: "+listarreportesinc.get(position).getAsunto());
        holder.tv_estado.setText("Estado: "+listarreportesinc.get(position).getEstado());
        holder.tv_descrip.setText("Descripción: "+listarreportesinc.get(position).getDescripcion());
        holder.codrep.setText((""+listarreportesinc.get(position).getCodreporte()));
    }
    //recibimos los objetos del cardview
    @Override
    public int getItemCount() {
        return listarreportesinc.size();
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

    public class ViewHolderReportInc extends RecyclerView.ViewHolder {
        TextView tv_asunto,tv_descrip,tv_estado,codrep;
        ImageView img1;
        public ViewHolderReportInc(@NonNull View itemView) {

            super(itemView);
            img1= (ImageView) itemView.findViewById(R.id.imageView4);
            tv_asunto= (TextView) itemView.findViewById(R.id.edtasurep);
            tv_descrip= (TextView) itemView.findViewById(R.id.edtdescrep);
            tv_estado= (TextView) itemView.findViewById(R.id.edtestrep);
            codrep= (TextView) itemView.findViewById(R.id.codrep);
        }
    }
}
