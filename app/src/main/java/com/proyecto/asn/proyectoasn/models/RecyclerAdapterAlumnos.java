package com.proyecto.asn.proyectoasn.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.proyecto.asn.proyectoasn.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

public class RecyclerAdapterAlumnos extends Adapter<RecyclerAdapterAlumnos.Holder> {
    //Declaración de variables
    private List<Alumno> listaAlumnos;
    private OnCustomItemClickListener mlistener;

    public  interface OnCustomItemClickListener{
        void itemClick(int position);
    }

    public RecyclerAdapterAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public void setMlistener(OnCustomItemClickListener mlistener) {
        this.mlistener = mlistener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno,parent,false);
        return new Holder(view, mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.connectData(listaAlumnos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView, final OnCustomItemClickListener mlistener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener !=null){
                        int position = getLayoutPosition();
                        if (position!=RecyclerView.NO_POSITION){
                           mlistener.itemClick(position);
                        }
                    }
                }
            });
        }

        public void connectData(Alumno alumno){
            TextView txtNombreClase = itemView.findViewById(R.id.txtNombreAlumno);
            CheckBox cbRealizado = itemView.findViewById(R.id.cbRealizado);
            txtNombreClase.setText(alumno.getNombreCompleto());
            cbRealizado.setChecked(alumno.getPuntuacion() > 0);
        }
    }
}
