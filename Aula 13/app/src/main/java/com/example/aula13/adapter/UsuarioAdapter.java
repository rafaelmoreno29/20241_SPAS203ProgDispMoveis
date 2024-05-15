package com.example.aula13.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aula13.R;
import com.example.aula13.activity.UsuarioActivity;
import com.example.aula13.api.ApiClient;
import com.example.aula13.api.UsuarioService;
import com.example.aula13.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioHolder> {
    private final List<Usuario> usuarios;
    Context context;

    public UsuarioAdapter(List<Usuario> usuarios, Context context) {
        this.usuarios = usuarios;
        this.context = context;
    }

    @Override
    public UsuarioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UsuarioHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_usuario, parent, false));
    }

    @Override
    public void onBindViewHolder(UsuarioHolder holder, int position) {
        holder.nome.setText(usuarios.get(position).getId() + " - " + usuarios.get(position).getNome());
        holder.email.setText(usuarios.get(position).getEmail());
        holder.btnexcluir.setOnClickListener(view -> removerItem(position));
        holder.btnEditar.setOnClickListener(view -> editarItem(position));
    }
    @Override
    public int getItemCount() {
        return usuarios != null ? usuarios.size() : 0;
    }
    private void removerItem(int position) {
        int id = usuarios.get(position).getId();
        UsuarioService apiService = ApiClient.getUsuarioService();
        Call<Void> call = apiService.deleteUsuario(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // A solicitação foi bem-sucedida
                    usuarios.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, usuarios.size());
                    Toast.makeText(context, "Excluído com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    // A solicitação falhou
                    Log.e("Exclusao","Erro ao excluir");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Ocorreu um erro ao fazer a solicitação
                Log.e("Exclusao","Erro ao excluir");
            }
        });
    }

    private void editarItem(int position) {
        int id = usuarios.get(position).getId();
        Intent i = new Intent(context, UsuarioActivity.class);
        i.putExtra("id",id);
        context.startActivity(i);
    }


    public class UsuarioHolder extends RecyclerView.ViewHolder {

        public TextView nome;
        public TextView email;
        public ImageView btnexcluir;
        public ImageView btnEditar;


        public UsuarioHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.txtNome);
            email = (TextView) itemView.findViewById(R.id.txtEmail);
            btnexcluir = (ImageView) itemView.findViewById(R.id.btnExcluir);
            btnEditar = (ImageView) itemView.findViewById(R.id.btnEditar);
        }
    }
}

