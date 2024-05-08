package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtNumero;
    Button btnIniciar;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNumero = (TextView) findViewById(R.id.txtNumero);
        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(v -> {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Processando...");
            progressDialog.setCancelable(false);
            progressDialog.show();
           new Thread(() -> {
               for (int i = 0; i <= 10; i++){
                   final int x = i;
                   runOnUiThread(() -> {
                       txtNumero.setText(""+x);
                       if(x==10){
                           progressDialog.dismiss();
                       }
                   });
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
           }).start();
        });
    }
}