package com.example.mediafinder;

import android.view.View;
import android.widget.TextView;

/**
 * Created by thevs on 4/11/2018.
 */

public class Game implements Media {

    String name;
    String developer;
    String platform;
    String releaseDate;
    public String lowerCaseName;
    String type;
    boolean liked;
    String id;

    public Game() {}

    public Game(String name, String developer, String platform, String releaseDate, String id){
        this.name = name;
        type = "Game";
        this.developer = developer;
        this.platform = platform;
        this.releaseDate = releaseDate;
        lowerCaseName = name.toLowerCase();
        liked = false;
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getType(){return type;}

    @Override
    public void setResults(View view) {
        ((TextView) view.findViewById(R.id.nameText)).setText(name);
        ((TextView) view.findViewById(R.id.mediaText)).setText(type);
    }

    @Override
    public void setInfo(View view) {
        ((TextView) view.findViewById(R.id.info_name)).setText(name);
        ((TextView) view.findViewById(R.id.info_type)).setText("Game");
        ((TextView) view.findViewById(R.id.info_topLine)).setText("Release Date: " + releaseDate);
        ((TextView) view.findViewById(R.id.info_middleLine)).setText("Developer: " + developer);
        ((TextView) view.findViewById(R.id.info_bottomLine)).setText("Platform(s): " + platform);
    }
    public void setPushId(String pushId){
        this.id = pushId;
    }

    public String getDeveloper() {return developer;}
    public String getPlatform() {return platform;}
    public String getReleaseDate() {return releaseDate;}
    public boolean isLiked(){return liked;}
    public void toggleLiked(){liked = !liked;}
    public String getID(){return id;}
}
