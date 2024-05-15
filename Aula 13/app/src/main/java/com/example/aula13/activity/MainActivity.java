package com.example.aula13.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aula13.R;
import com.example.aula13.adapter.UsuarioAdapter;
import com.example.aula13.api.ApiClient;
import com.example.aula13.api.UsuarioService;
import com.example.aula13.model.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerUsuario;
    UsuarioAdapter usuarioAdapter;
    UsuarioService apiService;
    List<Usuario> listaUsuarios;
    FloatingActionButton btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerUsuario = (RecyclerView) findViewById(R.id.recyclerUsuario);
        btnAdd = (FloatingActionButton)findViewById(R.id.btnAdd);
        apiService = ApiClient.getUsuarioService();
        btnAdd.setOnClickListener(v -> {
                Intent i = new Intent(MainActivity.this,UsuarioActivity.class);
                startActivity(i);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        obterUsuarios();
    }
    private void configurarRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerUsuario.setLayoutManager(layoutManager);
        usuarioAdapter = new UsuarioAdapter(listaUsuarios, this);
        recyclerUsuario.setAdapter(usuarioAdapter);
        recyclerUsuario.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
    private void obterUsuarios() {
        retrofit2.Call<List<Usuario>> call = apiService.getUsuarios();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                listaUsuarios = response.body();
                configurarRecycler();
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("TESTE", "Erro ao obter os contatos: " + t.getMessage());
            }
        });
    }

}