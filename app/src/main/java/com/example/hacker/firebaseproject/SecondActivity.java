package com.example.hacker.firebaseproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SecondActivity extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private TextView tv;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        tv = findViewById(R.id.text);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("NewMessage");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.setValue("Button1 Clicked");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.setValue("Button2 Clicked");

            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                tv.setText(databaseReference.toString());
//                Toast.makeText(MainActivity.this, value, Toast.LENGTH_LONG).show();
                String value = dataSnapshot.getValue(String.class);
                tv.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Obj o = new Obj("Huakun Shen", "shenhuakun@gmail.com", 19);
//        database.getReference().child("double").child("user3").setValue(o);
        database.getReference().child("User").child(o.getName()).setValue(o);

    }
}
