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

    public Game() {}

    public Game(String name, String developer, String platform, String releaseDate){
        this.name = name;
        this.developer = developer;
        this.platform = platform;
        this.releaseDate = releaseDate;
        lowerCaseName = name.toLowerCase();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setResults(View view) {
        ((TextView) view.findViewById(R.id.nameText)).setText(name);
        ((TextView) view.findViewById(R.id.mediaText)).setText("Game");
    }

    @Override
    public void setInfo(View view) {
        ((TextView) view.findViewById(R.id.info_name)).setText(name);
        ((TextView) view.findViewById(R.id.info_type)).setText("Game");
        ((TextView) view.findViewById(R.id.info_topLine)).setText("Release Date: " + releaseDate);
        ((TextView) view.findViewById(R.id.info_middleLine)).setText("Developer: " + developer);
        ((TextView) view.findViewById(R.id.info_bottomLine)).setText("Platform(s): " + platform);
    }

    public String getDeveloper() {return developer;}
    public String getPlatform() {return platform;}
    public String getReleaseDate() {return releaseDate;}
}
