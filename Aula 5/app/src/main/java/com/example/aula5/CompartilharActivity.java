package com.example.aula5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CompartilharActivity extends AppCompatActivity {
    Button buttonVoltar, buttonEnviar;
    EditText textMensagem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartilhar);
        buttonEnviar = (Button) findViewById(R.id.buttonEnviar);
        buttonVoltar = (Button) findViewById(R.id.buttonVoltar);
        textMensagem = (EditText) findViewById(R.id.textMensagem);

        buttonVoltar.setOnClickListener(v -> {
            finish();
        });
        buttonEnviar.setOnClickListener(v -> {
            String msg = textMensagem.getText().toString();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        });
    }
}