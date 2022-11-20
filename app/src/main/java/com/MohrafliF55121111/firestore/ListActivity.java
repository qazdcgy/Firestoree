package com.MohrafliF55121111.firestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Objects;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ArrayList<String> dataName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        dataName = new ArrayList<>();


        ListView listView = findViewById(R.id.list_View);
        mFirestore.collection("pengguna")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull
                                (task.getResult())) {dataName.add(document.getString
                                ("nama") + " - " + document.getString
                                ("stambuk"));
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>
                                (this,androidx.appcompat.R.layout.
                                        support_simple_spinner_dropdown_item, dataName);
                        listView.setAdapter( (adapter));
                    }
                    else {
                        Log.w("Lihat", "Error getting documents.", task.getException());
                    }
                });
    }
}