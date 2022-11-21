package com.robet.pahlawanku;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterCard extends RecyclerView.Adapter<AdapterCard.ClassViewHolder> {
    private ArrayList<ModelPahlawan> dataPahlawan;
    private Context ctx;

    public AdapterCard(ArrayList<ModelPahlawan> dataPahlawan, Context ctx) {
        this.dataPahlawan = dataPahlawan;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.item_card, parent, false);
        return new ClassViewHolder(varView);
    }

    @Override
    //menentukan posisi
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ModelPahlawan pahlawan = dataPahlawan.get(position);
        holder.tv_nama.setText(pahlawan.getNama());
        holder.tv_tentang.setText(pahlawan.getTentang());
        Glide
                .with(ctx)
                .load(pahlawan.getFoto())
                .centerCrop()
                .into(holder.iv_foto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String xnama,xtentang,xfoto;
                xnama = pahlawan.getNama();
                xtentang = pahlawan.getTentang();
                xfoto = pahlawan.getFoto();

                Intent kirim = new Intent(ctx, DetailActivity.class);
                kirim.putExtra("xNama",xnama);
                kirim.putExtra("xTentang", xtentang);
                kirim.putExtra("xFoto", xfoto);
                ctx.startActivity(kirim);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataPahlawan.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama,tv_tentang;
        ImageView iv_foto;
        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_tentang = itemView.findViewById(R.id.tv_tentang);
            iv_foto = itemView.findViewById(R.id.iv_foto);
        }
    }
}

