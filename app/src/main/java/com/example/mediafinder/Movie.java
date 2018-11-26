package com.example.mediafinder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by thevs on 4/10/2018.
 */

public class Movie implements Media{
    String genre;
    String plot;
    String poster;
    String title;
    String year;
    public String lowerCaseName;
    String type;
    boolean liked;
    String id;

    public Movie(){}

    public Movie(String genre, String plot, String poster, String title, String year,String type, String id){
        this.genre = genre;
        this.plot = plot;
        this.poster = poster;
        this.title = title;
        this.year = year;
        this.type = type;
        lowerCaseName = title.toLowerCase();
        liked = false;
        this.id = id;
    }

    @Override
    public String getTitle() {return title;}
    public String getType(){return type;}

    @Override
    public void setResults(View view) {
        ((TextView) view.findViewById(R.id.nameText)).setText(title);
        ((TextView) view.findViewById(R.id.mediaText)).setText(type);
    }

    public void setInfo(View view){
        ((TextView) view.findViewById(R.id.info_name)).setText(title);
        ((TextView) view.findViewById(R.id.info_type)).setText(type);
        ((TextView) view.findViewById(R.id.info_topLine)).setText("Year Released: " + year);
        ((TextView) view.findViewById(R.id.info_middleLine)).setText("Plot " + plot);
        ((TextView) view.findViewById(R.id.info_bottomLine)).setText("Genre: " +genre);

    }
    public void setPushId(String pushId){
        this.id = pushId;
    }

    public String getGenre(){return genre;}
    public String getPlot() {return plot;}
    public String getPoster(){return poster;}
    public String getYear(){return year;}
    public boolean isLiked(){return liked;}
    public void toggleLiked(){liked = !liked;}
    public String getID(){return id;}
}
