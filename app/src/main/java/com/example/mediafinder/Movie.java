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

//Default constructor for the movie object
    public Movie(){}

/**
 * Parameterized constructor for the Game object.
 * genre: Genre(s) of the game
 * plot: Synopsis of the movie
 * poster: Poster/cover of the game
 * title: Title of the game
 * year: Year the movie was released
 * type: 
 * id: 
 */
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

//Returns the title of the movie
    @Override
    public String getTitle() {return title;}
	
//Returns the title of the movie
    public String getType(){return type;}

//Sets the results
    @Override
    public void setResults(View view) {
        ((TextView) view.findViewById(R.id.nameText)).setText(title);
        ((TextView) view.findViewById(R.id.mediaText)).setText(type);
    }

//Sets the info
    public void setInfo(View view){
        ((TextView) view.findViewById(R.id.info_name)).setText(title);
        ((TextView) view.findViewById(R.id.info_type)).setText(type);
        ((TextView) view.findViewById(R.id.info_topLine)).setText("Year Released: " + year);
        ((TextView) view.findViewById(R.id.info_middleLine)).setText("Plot " + plot);
        ((TextView) view.findViewById(R.id.info_bottomLine)).setText("Genre: " +genre);

    }

//Sets the push ID
    public void setPushId(String pushId){
        this.id = pushId;
    }

//Returns the genre(s) of the movie
    public String getGenre(){return genre;}
	
//Returns the plot of the movie
    public String getPlot() {return plot;}

//Returns the poster of the movie
    public String getPoster(){return poster;}
	
//Returns the year the movie released
    public String getYear(){return year;}
	
//Returns if the movie has been liked or not
    public boolean isLiked(){return liked;}
	
//Toggles the liked boolean
    public void toggleLiked(){liked = !liked;}
	
//Returns the ID of the movie
    public String getID(){return id;}
}
