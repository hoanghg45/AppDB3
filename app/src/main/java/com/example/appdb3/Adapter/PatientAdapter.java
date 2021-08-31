package com.example.appdb3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdb3.Models.Patient;
import com.example.appdb3.Others.ItemClickListener;
import com.example.appdb3.R;

import java.util.ArrayList;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {
    private ArrayList<Patient> alPatient;
    private Context context;
    private PatientAdapterListener patientAdapterListener;

    public PatientAdapter(ArrayList<Patient> alPatient, Context context, PatientAdapterListener patientAdapterListener) {
        this.alPatient = alPatient;
        this.context = context;
        this.patientAdapterListener = patientAdapterListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from((parent.getContext()))
                .inflate(R.layout.patient_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(String.valueOf(alPatient.get(position).getName()));
        holder.txtPla.setText(alPatient.get(position).getPlace()+"-"+alPatient.get(position).getCost()+"VNĐ");
        if (String.valueOf(alPatient.get(position).getForm()).equals("tt")){
            holder.imgIc.setImageResource(R.drawable.taptrung);
        }else holder.imgIc.setImageResource(R.drawable.tainha);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                patientAdapterListener.click(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alPatient.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtName, txtPla;
        public ImageView imgIc;
        public ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPla = itemView.findViewById(R.id.txtPla);
            imgIc = itemView.findViewById(R.id.imgIc);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view, getAdapterPosition());
        }
        public void setItemClickListener(ItemClickListener ic) {
            this.itemClickListener = ic;
        }
    }
    public interface PatientAdapterListener {
        void click(View v, int position);
    }
}
