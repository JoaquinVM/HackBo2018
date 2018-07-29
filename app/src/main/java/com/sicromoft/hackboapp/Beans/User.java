package com.sicromoft.hackboapp.Beans;

import java.util.List;
import java.util.ListIterator;

public class User {

    private String name;
    private String email;
    private List<String> skills;

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

}
