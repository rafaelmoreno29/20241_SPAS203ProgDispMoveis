package com.example.aula5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonCompartilhar, buttonLigacao, buttonMapa,
    buttonNavegacao, buttonSite, buttonTirarFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonNavegacao = (Button) findViewById(R.id.buttonNavegar);
        buttonMapa = (Button) findViewById(R.id.buttonMapa);
        buttonSite = (Button) findViewById(R.id.buttonSite);
        buttonCompartilhar = (Button) findViewById(R.id.buttonCompartilhar);
        buttonLigacao = (Button) findViewById(R.id.buttonLigacao);

        buttonTirarFoto = (Button)findViewById(R.id.buttonTirarFoto);
        buttonTirarFoto.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FotoActivity.class);
            startActivity(intent);
        });


        buttonCompartilhar.setOnClickListener(v -> {
            Intent intent = new
                    Intent(MainActivity.this,CompartilharActivity.class);
            startActivity(intent);
        });
        buttonLigacao.setOnClickListener(v -> {
            Uri uri = Uri.parse("tel:15999999999");
            Intent intent = new Intent(Intent.ACTION_CALL,uri);
            int permissionCheck = ContextCompat.checkSelfPermission
                    (this, android.Manifest.permission.CALL_PHONE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                           android.Manifest.permission.CALL_PHONE},1);
            } else {
                startActivity(intent);
            }
        });

        buttonSite.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.facens.br/");
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
        });
        buttonMapa.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=Avenida+general+carneiro+1427");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            //   mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
        buttonNavegacao.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("google.navigation:q=Avenida+general+carneiro+1427");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            //   mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
    }
}