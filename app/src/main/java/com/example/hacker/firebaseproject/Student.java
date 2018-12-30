package com.example.hacker.firebaseproject;

import java.util.List;

public class Student {
    private int sId;
    private List<Obj> student;


    public int getsId() {
        return sId;
    }

    public List<Obj> getStudent() {
        return student;
    }


    public Student(int sId, List<Obj> student) {
        this.sId = sId;
        this.student = student;
    }

    public Student() {

    }
}
