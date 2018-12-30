package com.example.hacker.firebaseproject;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int age;

    private List<Year> year_list = new ArrayList<>();

    public List<Year> getYear_list() {
        return year_list;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    public User() {
    }

    public User(String name, int age) {

        this.name = name;
        this.age = age;
        year_list.add(new Year("2018"));
        year_list.add(new Year("2019"));
        year_list.add(new Year("2020"));

    }

    @Override
    public String toString() {
        return name + " is " + age + " years old.";
    }
}
