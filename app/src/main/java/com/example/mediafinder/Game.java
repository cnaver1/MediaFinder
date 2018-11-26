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

    public Game() {}

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

    @Override
    public String getTitle() {
        return title;
    }

    public String getType(){return type;}

    @Override
    public void setResults(View view) {
        ((TextView) view.findViewById(R.id.nameText)).setText(title);
        ((TextView) view.findViewById(R.id.mediaText)).setText(type);
    }

    @Override
    public void setInfo(View view) {
        ((TextView) view.findViewById(R.id.info_name)).setText(title);
        ((TextView) view.findViewById(R.id.info_type)).setText(type);
        ((TextView) view.findViewById(R.id.info_topLine)).setText("Genre: " + genre);
        ((TextView) view.findViewById(R.id.info_bottomLine)).setText("Price: " + price);

    }
    public void setPushId(String pushId){
        this.id = pushId;
    }

    public String getGenre() {return genre;}
    public String getPrice() {return price;}
    public String getPoster() {return poster;}
    public boolean isLiked(){return liked;}
    public void toggleLiked(){liked = !liked;}
    public String getID(){return id;}
}
