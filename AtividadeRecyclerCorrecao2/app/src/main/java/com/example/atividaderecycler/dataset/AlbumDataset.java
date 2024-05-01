package com.example.atividaderecycler.dataset;

import com.example.atividaderecycler.R;
import com.example.atividaderecycler.models.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumDataset {
    public static List<Album> getAlbuns(){
        ArrayList<Album> albums =  new ArrayList<>();
        albums.add(new Album("Pink Floyd","1973","Storm Thorgerson","Saiba Mais Pink Floyd", R.mipmap.pink));
        albums.add(new Album("Ramones","1976","Mick Rock","Saiba mais Ramones", R.mipmap.ramones));
        albums.add(new Album("Queen II","1974","Roberta Bayley","Saiba mais Queen", R.mipmap.queen));

        return  albums;
    }
}
