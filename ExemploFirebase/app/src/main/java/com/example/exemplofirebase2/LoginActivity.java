package com.example.exemplofirebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail, txtSenha;
    TextView txtRegistar;
    Button btnEntrar;
    FirebaseAuth auth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmail = (EditText) findViewById(R.id.editTextEmail);
        txtSenha = (EditText) findViewById(R.id.editTextSenha);
        btnEntrar = (Button) findViewById(R.id.buttonEntrar);
        txtRegistar = (TextView) findViewById(R.id.TextViewRegistrar);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        btnEntrar.setOnClickListener(v -> {
            entrar();
        });
        txtRegistar.setOnClickListener(v -> {
            registrar();
        });

        auth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(),ListaUsuarioActivity.class);
            startActivity(i);
            finish();
        }
    }
    public void entrar(){
        progressBar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(txtEmail.getText().toString(),txtSenha.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(getApplicationContext(), ListaUsuarioActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Falha na autenticação.",
                                                                Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
    public void registrar(){
        Intent i = new Intent(getApplicationContext(),RegistrarActivity.class);
        startActivity(i);
        finish();
    }



}