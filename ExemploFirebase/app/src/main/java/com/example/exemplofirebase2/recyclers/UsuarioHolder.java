package com.example.exemplofirebase2.recyclers;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.exemplofirebase2.R;

public class UsuarioHolder extends RecyclerView.ViewHolder {
    protected TextView textNome;
    protected ImageButton imageButtonEdit, imageButtonDelete;
    ImageView imageViewFoto;

    public UsuarioHolder(View itemView){
        super(itemView);
        textNome = (TextView) itemView.findViewById(R.id.textNome);
        imageButtonDelete =
                (ImageButton) itemView.findViewById(R.id.imageButtonDelete);
        imageButtonEdit =
                (ImageButton) itemView.findViewById(R.id.imageButtonEdit);
        imageViewFoto = (ImageView) itemView.findViewById(R.id.imageView);
    }

}
