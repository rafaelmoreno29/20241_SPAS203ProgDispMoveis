package com.example.aula14.sqlite;

import android.provider.BaseColumns;

public class SqlLiteContract {
    public static class Noticia implements BaseColumns {
        public static final String TABLE_NAME = "noticia";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_SUBTITULO = "subtitulo";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Noticia.TABLE_NAME +
                " (" + Noticia._ID + " INTEGER PRIMARY KEY,"
                + Noticia.COLUMN_NAME_TITULO + " TEXT,"
                + Noticia.COLUMN_NAME_SUBTITULO + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + Noticia.TABLE_NAME;

    }

}
