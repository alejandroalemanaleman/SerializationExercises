package org.example;
import java.io.Serializable;

public class Theater implements Serializable {
    private String name;
    public Theater(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String newname){
        this.name = newname;
    }

}

