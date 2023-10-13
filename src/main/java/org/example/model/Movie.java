package org.example.model;
import java.io.Serializable;


public class Movie implements Serializable {
    private String name;

    public Movie(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String newname){
        this.name = newname;
    }

}
