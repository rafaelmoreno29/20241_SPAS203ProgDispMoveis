package com.example.exemplofirebase2.recyclers;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exemplofirebase2.R;
import com.example.exemplofirebase2.models.Usuario;

import java.util.ArrayList;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioHolder>
{
    private final ArrayList<Usuario> usuarios;

    public UsuarioAdapter(ArrayList<Usuario> usuarios){
        this.usuarios = usuarios;
    }
    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UsuarioHolder(
                LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.item_lista_usuario,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder holder, int position) {
        Usuario usuario = usuarios.get(position);
        holder.textNome.setText(usuario.getNome());
        holder.imageButtonDelete.setOnClickListener(v -> {
            usuarios.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,usuarios.size());
        });
        holder.imageButtonEdit.setOnClickListener(v -> {
            Toast.makeText(v.getContext(),"Clicou Editar",
                                            Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return usuarios != null ? usuarios.size() : 0;
    }
}
