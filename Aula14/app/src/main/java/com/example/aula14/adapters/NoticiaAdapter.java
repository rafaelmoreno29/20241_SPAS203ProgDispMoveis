package com.example.aula14.adapters;

import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aula14.MainActivity;
import com.example.aula14.R;
import com.example.aula14.models.Noticia;
import com.example.aula14.sqlite.SqlLiteContract;

import java.util.List;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.NoticiaHolder> {
    private List<Noticia> noticias;
    SQLiteDatabase db;
    public NoticiaAdapter(List<Noticia> noticias, SQLiteDatabase db){
        this.noticias = noticias;
        this.db = db;
    }
    @NonNull
    @Override
    public NoticiaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoticiaHolder(LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false));    }

    @Override
    public void onBindViewHolder(@NonNull NoticiaHolder holder, int position) {
        holder.text1.setText(noticias.get(position).getTitulo());
        holder.text2.setText(noticias.get(position).getSubtitulo());

        holder.itemView.setOnLongClickListener(v -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
            alert.setMessage("Deseja Excluir esse item?");
            alert.setPositiveButton("Sim",(dialog, which) -> {
                String selection = SqlLiteContract.Noticia._ID + " = ?";
                String[] selectionArgs = { noticias.get(position).getId() + "" };
                int deletedRows = db.delete(SqlLiteContract.Noticia.TABLE_NAME, selection, selectionArgs);
                noticias.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, noticias.size());
                Toast.makeText(v.getContext(), "Excluído com sucesso!", Toast.LENGTH_SHORT).show();
            });
            alert.setNegativeButton("Não",null);
            alert.show();

            return true;
        });
        holder.itemView.setOnClickListener(v -> {
            EditText editText = ((MainActivity)v.getContext()).findViewById(R.id.editTextTitulo) ;
            editText.setText(noticias.get(position).getTitulo());
            editText = ((MainActivity)v.getContext()).findViewById(R.id.editTextSubtitulo) ;
            ((MainActivity)v.getContext()).edicao = true;
            ((MainActivity)v.getContext()).idEdicao = noticias.get(position).getId();
            editText.setText(noticias.get(position).getSubtitulo());
        });
        }

    @Override
    public int getItemCount() {
        return noticias != null ? noticias.size() : 0;
    }

    public class NoticiaHolder extends RecyclerView.ViewHolder{
        TextView text1,text2;
        public NoticiaHolder(View itemView) {
        super(itemView);
        text1 = (TextView) itemView.findViewById(android.R.id.text1);
        text2 = (TextView) itemView.findViewById(android.R.id.text2);

    }
}
}
