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
    public String lowerCaseName;
    String type;
    boolean liked;
    String pushId;
    public Book(){}


    public Book(String name, String series, String author, String rating, String numRatings){
        this.name = name;
        this.series = series;
        this.author = author;
        this.rating = rating;
        type = "Book";
        this.numRatings = numRatings;
        lowerCaseName = name.toLowerCase();
        liked = false;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setResults(View view) {
        ((TextView) view.findViewById(R.id.nameText)).setText(name);
        ((TextView) view.findViewById(R.id.mediaText)).setText(type);
    }

    @Override
    public void setInfo(View view) {
        ((TextView) view.findViewById(R.id.info_name)).setText(name);
        ((TextView) view.findViewById(R.id.info_type)).setText("Book");
        ((TextView) view.findViewById(R.id.info_topLine)).setText("Author: " + author);
        ((TextView) view.findViewById(R.id.info_middleLine)).setText("Series: " + series);
        ((TextView) view.findViewById(R.id.info_bottomLine)).setText("Rating: " + rating + "/5.00 (" + numRatings + " ratings)");
    }
    public void setPushId(String pushId){
        this.pushId = pushId;
    }

    public String getSeries() {return series;}
    public String getAuthor() {return author;}
    public String getRating() {return rating;}
    public String getNumRatings() {return numRatings;}
    public String getType(){return type;}
    public boolean isLiked(){return liked;}
    public void toggleLiked(){liked = !liked;}
    public String getID(){return pushId;}
}
