package com.example.aula3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText3;
    AutoCompleteTextView editPais;
    String[] listaPaises;
    ArrayAdapter adapterPaises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText3 = (EditText) findViewById(R.id.editTextText3);

        editPais = (AutoCompleteTextView)findViewById(R.id.editPais);
        listaPaises = getResources().getStringArray(R.array.paises_array);
        adapterPaises = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,listaPaises);
        editPais.setAdapter(adapterPaises);

        editText3.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                Toast.makeText(MainActivity.this,"Clicou na lupa!",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }
}