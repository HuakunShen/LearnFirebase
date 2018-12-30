package com.example.hacker.firebaseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Obj o = new Obj("Jason Wang", "jason@gmail.com", 19);
        Obj o2 = new Obj("Huakun Shen", "shenhuakun@gmail.com", 19);
        List<Obj> lst = new ArrayList<>();
        lst.add(o);
        lst.add(o2);
        Student student = new Student(001, lst);
        dbRef = FirebaseDatabase.getInstance().getReference().child("StudentList");
        dbRef.child(o.getName()).setValue(student);

    }
}
