package com.sicromoft.hackboapp.Activities;

import java.util.List;

public class Project{
    String name;
    String description;
    List<String> tags;

    public Project(String name, String description, List<String> tags){
        this.name = name;
        this.description = description;
        this.tags = tags;
    }

    public Project(){

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }
}
