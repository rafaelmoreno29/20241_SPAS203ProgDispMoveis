package com.example.aula14;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aula14.adapters.NoticiaAdapter;
import com.example.aula14.models.Noticia;
import com.example.aula14.sqlite.SqlLiteContract;
import com.example.aula14.sqlite.SqliteDbHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SqliteDbHelper dbHelper;
    EditText txtTitulo, txtSubtitulo;
    public boolean edicao;
    public int idEdicao;
    Button btnSalvar;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbHelper = new SqliteDbHelper(getApplicationContext());
        txtTitulo = (EditText)findViewById(R.id.editTextTitulo);
        txtSubtitulo = (EditText)findViewById(R.id.editTextSubtitulo);
        btnSalvar = (Button)findViewById(R.id.buttonSalvar);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        edicao = false;

        btnSalvar.setOnClickListener(v -> {
            if(edicao){
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(SqlLiteContract.Noticia.COLUMN_NAME_TITULO , txtTitulo.getText().toString());
                values.put(SqlLiteContract.Noticia.COLUMN_NAME_SUBTITULO , txtSubtitulo.getText().toString());
                String selection = SqlLiteContract.Noticia._ID + " = ?";
                String[] selectionArgs = { idEdicao + "" };
                int count = db.update(SqlLiteContract.Noticia.TABLE_NAME,values,selection,selectionArgs);
                Toast.makeText(getApplicationContext(), "Salvo com sucesso!", Toast.LENGTH_LONG).show();
                edicao = false;

            }
            else {
                //EXEMPLO INSERÇÃO
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(SqlLiteContract.Noticia.COLUMN_NAME_TITULO, txtTitulo.getText().toString());
                values.put(SqlLiteContract.Noticia.COLUMN_NAME_SUBTITULO, txtSubtitulo.getText().toString());
                long newRowId = db.insert(SqlLiteContract.Noticia.TABLE_NAME, null, values);
                Toast.makeText(getApplicationContext(), "Salvo com sucesso!", Toast.LENGTH_LONG).show();
            }
            txtTitulo.setText("");
            txtSubtitulo.setText("");
            montarRecycler(ObterTodasNoticias());

        });
        montarRecycler(ObterTodasNoticias());

       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }

    private void montarRecycler(List<Noticia> noticias) {
        NoticiaAdapter adapter = new NoticiaAdapter(noticias,dbHelper.getWritableDatabase());
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);
    }

    private List<Noticia> ObterTodasNoticias() {
        SQLiteDatabase dbReader = dbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                SqlLiteContract.Noticia.COLUMN_NAME_TITULO,
                SqlLiteContract.Noticia.COLUMN_NAME_SUBTITULO };

      //  String selection = SqlLiteContract.Noticia.COLUMN_NAME_TITULO + " = ?";
       // String[] selectionArgs = { "teste" };

        String sortOrder = SqlLiteContract.Noticia.COLUMN_NAME_SUBTITULO + " DESC";
        Cursor cursor = dbReader.query(SqlLiteContract.Noticia.TABLE_NAME,  projection, null,  null,  null, null,  sortOrder);

        List<Noticia> noticias = new ArrayList<>();
        while(cursor.moveToNext()) {
            Noticia n = new Noticia();
            n.setId(cursor.getInt(cursor.getColumnIndexOrThrow(SqlLiteContract.Noticia._ID)));
            n.setTitulo(cursor.getString(cursor.getColumnIndexOrThrow(SqlLiteContract.Noticia.COLUMN_NAME_TITULO)));
            n.setSubtitulo(cursor.getString(cursor.getColumnIndexOrThrow(SqlLiteContract.Noticia.COLUMN_NAME_SUBTITULO)));
            noticias.add(n);
        }
        cursor.close();
        return  noticias;
    }
}