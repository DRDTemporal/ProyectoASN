package com.proyecto.asn.proyectoasn.models;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapterClases extends RecyclerView.Adapter<RecyclerAdapterClases.Holder> {

    private List<Clase> listaClases = new ArrayList<>();
    private RecyclerAdapterAlumnos.OnCustomItemClickListener mlistener;
    public  interface OnCustomItemClickListener{
        void itemClick(int position);
    }

    public RecyclerAdapterClases(List<Clase> listaClases) {
        this.listaClases = listaClases;
    }

    public void setMlistener(RecyclerAdapterAlumnos.OnCustomItemClickListener mlistener) {
        this.mlistener = mlistener;
    }

    @NonNull
    @Override
    public RecyclerAdapterClases.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterClases.Holder holder, int position) {
        holder.connectData(listaClases.get(position));
    }

    @Override
    public int getItemCount() {
        return listaClases.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener!=null) {
                        int position = getLayoutPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mlistener.itemClick(position);
                        }
                    }
                }
            });
        }

        public void connectData(Clase clase){

        }
    }
}
