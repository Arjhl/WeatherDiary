package com.example.myapplication;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;



public class Diarieslist extends AppCompatActivity {

    ArrayList<Item> rvList;

    FirebaseFirestore db;

    MyAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarieslist);
        rvList = new ArrayList<Item>();
        getData();
//        rvList.add(new Item("hi","https://images.unsplash.com/photo-1682687220945-922198770e60?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
         adapter = new MyAdapter(rvList, getApplicationContext());
        recyclerView.setAdapter(adapter);


    }

    private void getData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<Item> result = new ArrayList<Item>();
        db.collection("WeatherDiary").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                Log.i("Diaries list",list.toString());
                for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                    // Assuming each document has a field named "yourField"
                    String fieldValue = document.getString("Des");
                    String ImageUri = document.getString("ImageUrl");
                    if(fieldValue != null && ImageUri != null){
                        Log.i("RV LIST",fieldValue + ImageUri);
                        Item idk = new Item(fieldValue,ImageUri);
                        rvList.add(idk);
                        adapter.notifyDataSetChanged();
                    }

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // if we do not get any data or any error we are displaying
                // a toast message that we do not get any data
                Toast.makeText(Diarieslist.this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


