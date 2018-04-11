package com.example.mediafinder;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by thevs on 4/10/2018.
 */

public class Movie implements Media{
    String name;
    String year;
    String rating;
    String ranking;
    String lowerCaseName;

    public Movie(){}

    public Movie(String name, String year, String rating, String ranking){
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.ranking = ranking;
        lowerCaseName = name.toLowerCase();
    }

    @Override
    public String getName() {return name;}

    @Override
    public void setResults(View view) {
        ((TextView) view.findViewById(R.id.resultsText)).setText(name);
    }

    public String getYear(){return year;}
    public String getRating() {return rating;}
    public String getRanking() {return ranking;}
}
