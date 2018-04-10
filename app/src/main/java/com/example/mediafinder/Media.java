package com.example.mediafinder;

/**
 * Created by thevs on 4/10/2018.
 */

public class Media {
    String name;
    String def;

    public Media(){}

    public Media(String name, String def){
        this.name = name;
        this.def = def;
    }

    public String getName(){
        return name;
    }

    public String getDef(){
        return def;
    }
}
