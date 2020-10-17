package com.proyecto.asn.proyectoasn.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.proyecto.asn.proyectoasn.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapterCursos extends RecyclerView.Adapter<RecyclerAdapterCursos.Holder> {

    private List<Curso> listaClases;
    private RecyclerAdapterAlumnos.OnCustomItemClickListener mlistener;
    public  interface OnCustomItemClickListener{
        void itemClick(int position);
    }

    public RecyclerAdapterCursos(List<Curso> listaClases) {
        this.listaClases = listaClases;
    }

    public void setMlistener(RecyclerAdapterAlumnos.OnCustomItemClickListener mlistener) {
        this.mlistener = mlistener;
    }

    @NonNull
    @Override
    public RecyclerAdapterCursos.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curso,parent,false);
        return new Holder(view, mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterCursos.Holder holder, int position) {
        holder.connectData(listaClases.get(position));
    }

    @Override
    public int getItemCount() {
        return listaClases.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView, final RecyclerAdapterAlumnos.OnCustomItemClickListener mlistener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener !=null) {
                        int position = getLayoutPosition();
                        if (position!=RecyclerView.NO_POSITION){
                           mlistener.itemClick(position);
                        }
                    }
                }
            });
        }

        public void connectData(Curso clase){
            TextView txtNombreClase = itemView.findViewById(R.id.txtNombreCurso);
            TextView txtNumeroAlumnos = itemView.findViewById(R.id.txtNumeroAlumnos);
            txtNombreClase.setText(clase.getNombre());
            txtNumeroAlumnos.setText(String.valueOf(clase.getAlumnos().size()));
        }
    }
}
