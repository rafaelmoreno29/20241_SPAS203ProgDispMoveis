package com.example.atividaderecycler.recycler;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividaderecycler.R;

public class AlbumHolder extends RecyclerView.ViewHolder {
    TextView txtBanda, txtFotografo, txtAno;
    ImageView imagem;
    Button btnSaibamais;

    public AlbumHolder(@NonNull View itemView) {
        super(itemView);
        txtAno = (TextView) itemView.findViewById(R.id.textViewAno);
        txtFotografo = (TextView) itemView.findViewById(R.id.textViewFotografo);
        txtBanda = (TextView) itemView.findViewById(R.id.textViewBanda);
        imagem = (ImageView) itemView.findViewById(R.id.imageView);
        btnSaibamais = (Button) itemView.findViewById(R.id.buttonSinopse);
    }
}
