package com.example.hacker.firebaseproject;

import java.util.ArrayList;
import java.util.List;

public class Obj {
    private String name;
    private String Email;
    private int age;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return Email;
    }

    public int getAge() {
        return age;
    }



    public Obj(String name, String email, int age) {
        this.name = name;
        Email = email;
        this.age = age;
    }

    public Obj() {
    }


}

