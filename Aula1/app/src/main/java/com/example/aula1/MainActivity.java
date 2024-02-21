package com.example.aula1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textHello;
    private int qtdeClicks = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textHello = findViewById(R.id.textHello);
        textHello.setText("Olá Rafael");
    }
    public void clickBotaoOK(View view){
        qtdeClicks++;
        textHello.setText("Clicou " + qtdeClicks + " vez(es)!");
    }

}