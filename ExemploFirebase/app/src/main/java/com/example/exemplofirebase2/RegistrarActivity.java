package com.example.exemplofirebase2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrarActivity extends AppCompatActivity {
    EditText editEmail, editSenha;
    Button buttonSalvar;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        mAuth = FirebaseAuth.getInstance();
        buttonSalvar.setOnClickListener(v -> inserirUsuario());
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(),
                                        ListaUsuarioActivity.class);
            startActivity(i);
            finish();
        }
    }

    public void inserirUsuario(){
        if(TextUtils.isEmpty(editEmail.getText())){
            Toast.makeText(this, "E-mail obrigatório",
                                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(editSenha.getText())){
            Toast.makeText(this, "Senha obrigatória",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(
                editEmail.getText().toString(),
                editSenha.getText().toString()
        ).addOnCompleteListener(task -> {
            progressBar.setVisibility(View.VISIBLE);
           if(task.isSuccessful()){
               Toast.makeText(this, "Salvo com sucesso!",
                       Toast.LENGTH_SHORT).show();
               Intent i = new Intent(getApplicationContext(),
                                    LoginActivity.class);
               startActivity(i);
               finish();
           }else{
               Toast.makeText(this, "Falha ao salvar",
                                    Toast.LENGTH_SHORT).show();
           }
            progressBar.setVisibility(View.GONE);
        });
    }
}