package com.example.mediafinder;

import android.view.View;
import android.widget.TextView;

/**
 * Created by thevs on 4/10/2018.
 */

public class Movie implements Media{
    String name;
    String year;
    String rating;
    String ranking;
    public String lowerCaseName;
    String type;
    boolean liked;
    String id;

    public Movie(){}

    public Movie(String name, String year, String rating, String ranking, String id){
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.ranking = ranking;
        type = "Movie";
        lowerCaseName = name.toLowerCase();
        liked = false;
        this.id = id;
    }

    @Override
    public String getName() {return name;}

    @Override
    public void setResults(View view) {
        ((TextView) view.findViewById(R.id.nameText)).setText(name);
        ((TextView) view.findViewById(R.id.mediaText)).setText("Movie");
    }

    public void setInfo(View view){
        ((TextView) view.findViewById(R.id.info_name)).setText(name);
        ((TextView) view.findViewById(R.id.info_type)).setText(type);
        ((TextView) view.findViewById(R.id.info_topLine)).setText("Year Released: " + year);
        ((TextView) view.findViewById(R.id.info_middleLine)).setText("IMDB Movie Ranking: " + ranking);
        ((TextView) view.findViewById(R.id.info_bottomLine)).setText("Rating: " + rating + "/10.0");
    }
    public void setPushId(String pushId){
        this.id = pushId;
    }

    public String getYear(){return year;}
    public String getRating() {return rating;}
    public String getRanking() {return ranking;}
    public String getType(){return type;}
    public boolean isLiked(){return liked;}
    public void toggleLiked(){liked = !liked;}
    public String getID(){return id;}
}
