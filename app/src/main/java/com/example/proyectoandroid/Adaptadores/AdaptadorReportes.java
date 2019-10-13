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

public class AdaptadorReportes extends RecyclerView.Adapter<AdaptadorReportes.ViewHolderReportInc> {
    ArrayList<ReporteIncidente> listarreportesinc;

    public AdaptadorReportes(ArrayList<ReporteIncidente> listarreportesinc) {
        this.listarreportesinc = listarreportesinc;
    }

    @NonNull
    @Override
    public ViewHolderReportInc onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listar_recycler,null,false);
        return new ViewHolderReportInc(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderReportInc holder, int position) {

        holder.img1.setImageBitmap(BitmapFactory.decodeByteArray(listarreportesinc.get(position).getImage1(),
                0,listarreportesinc.get(position).getImage1().length));
        holder.tv_asunto.setText("Asunto: "+listarreportesinc.get(position).getAsunto());
        holder.tv_estado.setText("Estado: "+listarreportesinc.get(position).getEstado());
        holder.tv_descrip.setText("Descripción: "+listarreportesinc.get(position).getDescripcion());
        holder.codrep.setText(("Código de reporte:"+listarreportesinc.get(position).getCodreporte()));
    }

    @Override
    public int getItemCount() {
        return listarreportesinc.size();
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
