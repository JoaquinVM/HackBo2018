package com.sicromoft.hackboapp.Beans;

import java.util.List;

public class Project{
    String name;
    String overview;
    String description;
    List<String> tags;

    public Project(String name, String overview, String description, List<String> tags){
        this.name = name;
        this.overview = overview;
        this.description = description;
        this.tags = tags;
    }

    public Project(){

    }

    public String getName() {
        return name;
    }

    public String getOverview() { return overview; }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }
}
