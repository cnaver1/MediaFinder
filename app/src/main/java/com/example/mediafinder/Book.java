package com.example.mediafinder;

import android.view.View;
import android.widget.TextView;

/**
 * Created by thevs on 4/10/2018.
 */

public class Book implements Media {
    String name;
    String series;
    String author;
    String rating;
    String numRatings;
    String lowerCaseName;

    public Book(){}

    public Book(String name, String series, String author, String rating, String numRatings){
        this.name = name;
        this.series = series;
        this.author = author;
        this.rating = rating;
        this.numRatings = numRatings;
        lowerCaseName = name.toLowerCase();
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setResults(View view) {
        ((TextView) view.findViewById(R.id.resultsText)).setText(name);
    }

    public String getSeries() {return series;}
    public String getAuthor() {return author;}
    public String getRating() {return rating;}
    public String getNumRatings() {return numRatings;}
}
