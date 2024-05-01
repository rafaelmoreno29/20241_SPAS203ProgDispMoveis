package com.example.exemplofirebase2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.exemplofirebase2.models.Usuario;
import com.example.exemplofirebase2.recyclers.UsuarioAdapter;
import com.example.exemplofirebase2.recyclers.UsuarioHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListaUsuarioActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Usuario> usuarios;
    UsuarioAdapter usuarioAdapter;
    FirebaseFirestore db;
    FloatingActionButton buttonadd;

    TextView txtUsuario;
    Button btnLogout;
    FirebaseUser user;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuario);
        db = FirebaseFirestore.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewUsuario);
        usuarios = new ArrayList<>();
        buttonadd = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        buttonadd.setOnClickListener(v -> {
            Intent i = new Intent(ListaUsuarioActivity.this, MainActivity.class);
            startActivity(i);
        });

        auth = FirebaseAuth.getInstance();
        btnLogout = (Button) findViewById(R.id.buttonLogout);
        txtUsuario = (TextView) findViewById(R.id.textViewUsuario);
        user = auth.getCurrentUser();
        if(user == null){
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        }else{
            txtUsuario.setText(user.getEmail());
        }

        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        buscarUsuarios();
    }

    public void buscarUsuarios(){
        //usuarios = Usuario.getUsuarios();
        //iniciarRecycler();
        usuarios = new ArrayList<>();
        db.collection("usuarios").get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                  for (QueryDocumentSnapshot document : task.getResult()) {
                        Usuario usu = document.toObject(Usuario.class);
                        usu.setId(document.getId());
                        usuarios.add(usu);
                    }
                    iniciarRecycler();
                }
            }
        });

    }
    public void iniciarRecycler(){
        LinearLayoutManager layout =
      new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

       // GridLayoutManager layout =new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(layout);
        usuarioAdapter = new UsuarioAdapter(usuarios,db);
        recyclerView.setAdapter(usuarioAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(
                                this,DividerItemDecoration.VERTICAL));
    }
}