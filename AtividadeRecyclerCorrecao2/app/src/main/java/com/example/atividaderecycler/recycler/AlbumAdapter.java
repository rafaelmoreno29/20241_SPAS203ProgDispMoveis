package com.example.atividaderecycler.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividaderecycler.R;
import com.example.atividaderecycler.models.Album;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumHolder> {
    private List<Album> albums;

    public AlbumAdapter(List<Album> filmes) {
        this.albums = filmes;
    }

    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new AlbumHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder holder, int position) {
        Album f = albums.get(position);
        holder.txtBanda.setText(f.getBanda());
        holder.txtFotografo.setText(f.getFotografo());
        holder.txtAno.setText(f.getAno());
        holder.imagem.setImageResource(f.getImagem());
        holder.btnSaibamais.setOnClickListener(v -> {
            Toast.makeText(v.getContext(),f.getSaibamais(),Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return albums != null ? albums.size() : 0;
    }
}
