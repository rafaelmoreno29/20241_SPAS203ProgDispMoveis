package com.example.exemplofirebase2.recyclers;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exemplofirebase2.MainActivity;
import com.example.exemplofirebase2.R;
import com.example.exemplofirebase2.models.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioHolder>
{
    private final ArrayList<Usuario> usuarios;
    FirebaseFirestore db;
    public UsuarioAdapter(ArrayList<Usuario> usuarios,
                          FirebaseFirestore db){
        this.usuarios = usuarios;
        this.db = db;
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
        holder.textNome.setText(usuario.getNome() + " "+usuario.getSobrenome());

        new Thread(() -> {
            URL url = null;
            try {
                url = new URL(usuario.getFoto());
                final Bitmap bmp;
                bmp = BitmapFactory
                 .decodeStream(url.openConnection().getInputStream());
                new Handler(Looper.getMainLooper()).post(() ->
                {
                    holder.imageViewFoto.setImageBitmap(bmp);
                });
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        holder.imageButtonDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Atenção")
                    .setMessage("Deseja excluir esse item?")
                    .setIcon(R.mipmap.ic_launcher)
                    .setNegativeButton("Não",null)
                    .setPositiveButton("Sim",(dialog, which) -> {
                        db.collection("usuarios").document(usuario.getId())
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        usuarios.remove(position);
                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(position,usuarios.size());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("TAG", "Erro ao excluir", e);
                                    }
                                });




                    })
                    .show();
        });
        holder.imageButtonEdit.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            intent.putExtra("nome",usuario.getNome());
            intent.putExtra("sobrenome",usuario.getSobrenome());
            intent.putExtra("foto",usuario.getFoto());
            intent.putExtra("id",usuario.getId());
            intent.putExtra("anoNascimento",usuario.getAnoNascimento());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return usuarios != null ? usuarios.size() : 0;
    }
}
