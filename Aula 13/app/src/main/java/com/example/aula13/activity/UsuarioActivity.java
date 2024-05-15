package com.example.aula13.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aula13.R;
import com.example.aula13.api.ApiClient;
import com.example.aula13.api.UsuarioService;
import com.example.aula13.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioActivity extends AppCompatActivity {
    Button btnSalvar;
    UsuarioService apiService;
    TextView txtemail, txtsenha,txtnome;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        apiService = ApiClient.getUsuarioService();
        txtemail = findViewById(R.id.txtEmailUsuario);
        txtsenha = findViewById(R.id.txtSenhaUsuario);
        txtnome = findViewById(R.id.txtNomeUsuario);
        id = getIntent().getIntExtra("id", 0);
        if (id > 0) {
            apiService.getUsuarioPorId(id).enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        txtemail.setText(response.body().getEmail());
                        txtnome.setText(response.body().getNome());
                        txtsenha.setText(response.body().getSenha());
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.e("Obter usuario", "Erro ao obter usuario");
                }
            });
        }
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario();
                usuario.setEmail(txtemail.getText().toString());
                usuario.setSenha(txtsenha.getText().toString());
                usuario.setNome(txtnome.getText().toString());

                if (id == 0)
                    inserirUsuario(usuario);
                else {
                    usuario.setId(id);
                    editarUsuario(usuario);
                }

            }
        });
    }
    private void inserirUsuario(Usuario usuario) {
        Call<Usuario> call = apiService.postUsuario(usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    // A solicitação foi bem-sucedida
                    Usuario createdPost = response.body();
                    Toast.makeText(UsuarioActivity.this, "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // A solicitação falhou
                    Log.e("Inserir", "Erro ao criar: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                // Ocorreu um erro ao fazer a solicitação
                Log.e("Inserir", "Erro ao criar: " + t.getMessage());
            }
        });
    }
    private void editarUsuario(Usuario usuario) {
        Call<Usuario> call = apiService.putUsuario(id,usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    // A solicitação foi bem-sucedida
                    Usuario createdPost = response.body();
                    Toast.makeText(UsuarioActivity.this, "Editado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // A solicitação falhou
                    Log.e("Editar", "Erro ao editar: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                // Ocorreu um erro ao fazer a solicitação
                Log.e("Editar", "Erro ao editar: " + t.getMessage());
            }
        });
    }
}