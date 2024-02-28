package com.example.aula2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerSexo;
    EditText textIdade, textValorAutomovel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("onCreate","Entrou no método OnCreate");

        spinnerSexo = (Spinner) findViewById(R.id.spinnerSexo);
        textIdade = (EditText) findViewById(R.id.textIdade);
        textValorAutomovel = (EditText) findViewById(R.id.textValorAutomovel);

        List<String> opcoesSexo = Arrays.asList("Masculino","Feminino");
        ArrayAdapter<String> adapterSexo =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        opcoesSexo);
        spinnerSexo.setAdapter(adapterSexo);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart","Entrou no método OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onResume","Entrou no método OnResume");

        Toast.makeText(this,"Aqui vai o texto",Toast.LENGTH_SHORT).show();
    }
    public void buttonCalcularClick(View v){
        String sexo = spinnerSexo.getSelectedItem().toString();
        int idade = Integer.parseInt(textIdade.getText().toString());
        double valorAutomovel = Double.parseDouble(textValorAutomovel.getText().toString());
        double valorApolice = 0;

        if(sexo.equals("Masculino") && idade >= 35)
            valorApolice = valorAutomovel * 10/100;
        if(sexo.equals("Masculino") && idade < 35)
            valorApolice = valorAutomovel * 5/100;
        if(sexo.equals("Feminino"))
            valorApolice = valorAutomovel * 2/100;

        Toast.makeText(this,
         "O valor é: " + valorApolice,Toast.LENGTH_SHORT).show();
    }
}