package com.example.mediafinder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by thevs on 4/11/2018.
 */

public class Game implements Media {

    String genre;
    String poster;
    String price;
    String title;
    public String lowerCaseName;
    String type;
    boolean liked;
    String id;

//Default constructor for the game object
    public Game() {}

/**
 * Parameterized constructor for the Game object.
 * genre: Genre(s) of the game
 * poster: Poster/cover of the game
 * price: Price of the game
 * title: Title of the game
 * type: Type of the media, i.e. game is "game"
 * id: Unique id that identifies the specific media title
 */
    public Game(String genre, String poster, String price, String title, String type, String id){
        this.genre = genre;
        this.type = type;
        this.poster = poster;
        this.price = price;
        this.title = title;
        lowerCaseName = title.toLowerCase();
        liked = false;
        this.id = id;
    }

//Returns the title of the game object
    @Override
    public String getTitle() {return title;}
	
//Returns the type of the game object
    public String getType(){return type;}

//Sets the results
    @Override
    public void setResults(View view) {
        ((TextView) view.findViewById(R.id.nameText)).setText(title);
        ((TextView) view.findViewById(R.id.mediaText)).setText(type);
    }

//Sets the info
    @Override
    public void setInfo(View view) {
        ((TextView) view.findViewById(R.id.info_name)).setText(title);
        ((TextView) view.findViewById(R.id.info_type)).setText(type);
        ((TextView) view.findViewById(R.id.info_topLine)).setText("Genre: " + genre);
        ((TextView) view.findViewById(R.id.info_bottomLine)).setText("Price: " + price);

    }
	
//Sets the pushID
    public void setPushId(String pushId){this.id = pushId;}

//Returns the genre(s) of the game object
    public String getGenre() {return genre;}
	
//Returns the price of the game object
    public String getPrice() {return price;}
	
//Returns the poster of the game object
    public String getPoster() {return poster;}
	
//Returns whether the game object is liked or not
    public boolean isLiked(){return liked;}
	
//Toggles the liked boolean
    public void toggleLiked(){liked = !liked;}
	
//Returns the ID of the game object
    public String getID(){return id;}
}
