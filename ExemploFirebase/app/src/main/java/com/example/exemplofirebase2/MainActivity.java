package com.example.exemplofirebase2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton buttonAdd;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        buscarUsuarios();
        buttonAdd = (FloatingActionButton) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(v -> {
            adicionarUsuario();
        });


        final DocumentReference docRef = db.collection("usuarios").document("123");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("xxx", "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d("xxx", "Current data: " + snapshot.getData());
                } else {
                    Log.d("xxx", "Current data: null");
                }
            }
        });

    }

    public void buscarUsuarios(){
        db.collection("usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Usuario", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("Usuario", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void adicionarUsuario(){
        Map<String, Object> user = new HashMap<>();
        user.put("nome", "Rafael");
        user.put("sobrenome", "Moreno");
        user.put("anoNascimento", 1985);

        //exemplo setando o ID do documento
        //db.collection("usuarios").document("123")
                //.set(user);

        db.collection("usuarios")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Usuario", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Usuario", "Error adding document", e);
                    }
                });
    }
}