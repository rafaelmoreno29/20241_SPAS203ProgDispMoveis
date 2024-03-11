package com.example.calculomedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText editN1,editN2,editResultado;
    RadioGroup groupTipoMedia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editN1 = (EditText)findViewById(R.id.editNota1);
        editN2 = (EditText)findViewById(R.id.editNota2);
        editResultado = (EditText)findViewById(R.id.editResultado);
        groupTipoMedia = (RadioGroup)findViewById(R.id.radioGroupTipoMedia);

        editN2.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_SEND){
                double nota1 = Double.parseDouble(editN1.getText().toString());
                double nota2 = Double.parseDouble(editN2.getText().toString());
                double resultado = 0;
                if(groupTipoMedia.getCheckedRadioButtonId() == R.id.radioSimples) {
                    resultado = (nota1 + nota2)/2;
                }else{
                    resultado = (nota1 * 0.4 )+ (nota2 * 0.6);
                }
                editResultado.setText(String.valueOf(resultado));
            }
            return true;
        });
    }
}