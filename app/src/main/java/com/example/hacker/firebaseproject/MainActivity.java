package com.example.hacker.firebaseproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userRef = db.collection("Users");
    private DocumentReference user1Ref = db.document("Users/User1");
//    private ListenerRegistration userListener;
    private static final String TAG = "MainActivity";
    private TextView text;
    private EditText nameET, ageET, userET;
    private Button saveBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupLayout();


//        saveData();
//        loadData();
    }

    private void setupLayout() {
        text = findViewById(R.id.textView);
        nameET = findViewById(R.id.nameET);
        ageET = findViewById(R.id.ageET);
        userET = findViewById(R.id.userET);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(userET.getText().toString(), nameET.getText().toString(), Integer.valueOf(ageET.getText().toString()));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        user1Ref.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "onEvent: Error while loading, in onStart");
                    return;
                }
                if (documentSnapshot.exists()) {
                    User user = documentSnapshot.toObject(User.class);
                    Log.d(TAG, "onSuccess: " + user.toString());
                    text.setText(user.toString());
                }
            }
        });
    }

    private void loadDatas() {
        db.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    User user = documentSnapshot.toObject(User.class);
                    // do something with this user object
                }
            }
        });
    }

    private void loadData() {
        user1Ref.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Map<String, Object> user = documentSnapshot.getData();
                            Log.d(TAG, "onSuccess: " + user.toString());
                            text.setText(user.toString());
                        } else {
                            Log.d(TAG, "onSuccess: Failed to read");
                        }
                    }
                });
    }

    private void saveData(String user, String name, int age) {
//        Map<String, Object> user1 = new HashMap();
//        user1.put("name", name);
//        user1.put("age", age);
        User user1 = new User(name, age);

        db.collection("Users").add(user1).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(MainActivity.this, "Success set user", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Faled set user", Toast.LENGTH_SHORT).show();
            }
        });
    }

}