package com.example.atividaderecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.atividaderecycler.dataset.AlbumDataset;
import com.example.atividaderecycler.recycler.AlbumAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AlbumAdapter albumAdapter;
    Button btnLayout;
    String tipoLayout = "grid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        albumAdapter = new AlbumAdapter(AlbumDataset.getAlbuns());
        recyclerView.setAdapter(albumAdapter);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        btnLayout = (Button) findViewById(R.id.buttonLayout);
        btnLayout.setOnClickListener(v -> {
            if(tipoLayout== "grid"){
                RecyclerView.LayoutManager layout = new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false);
                recyclerView.setLayoutManager(layout);
                tipoLayout = "horizontal";
            }
            else{
                RecyclerView.LayoutManager layout = new GridLayoutManager(this,2);
                recyclerView.setLayoutManager(layout);
                tipoLayout = "grid";
            }
        });

    }


}