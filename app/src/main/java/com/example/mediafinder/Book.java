package com.example.mediafinder;

import android.view.View;
import android.widget.TextView;


/**
 * Book creates the fields in the book class. Allowing them to be set in the SearchList and MoreInfo.
 */
public class Book implements Media {
    String author;
    String genre;
    String poster;
    String title;
    String type;
    String id;
    public String lowerCaseName;
    boolean liked;

    public Book(){}

    public Book(String author,String genre,String poster,String title,String type, String id){
        this.author = author;
        this.genre = genre;
        this.poster = poster;
        this.title = title;
        this.id = id;
        this.type = type;
        lowerCaseName = title.toLowerCase();
        liked = false;
    }

    @Override
    public String getTitle(){return title;}
    public String getType(){return type;}




//Sets the Title and Type to the searchList
    @Override
    public void setResults(View view) {
        ((TextView) view.findViewById(R.id.nameText)).setText(title);
        ((TextView) view.findViewById(R.id.mediaText)).setText(type);
    }


//Sets the title, type, author, genre ,and poster to the moreInfo page.

    @Override
    public void setInfo(View view) {
        ((TextView) view.findViewById(R.id.info_name)).setText(title);
        ((TextView) view.findViewById(R.id.info_type)).setText(type);
        ((TextView) view.findViewById(R.id.info_topLine)).setText("Author: " + author);
        ((TextView) view.findViewById(R.id.info_middleLine)).setText("Genre: " + genre);
        //((TextView) view.findViewById(R.id.info_bottomLine)).setText("Poster"  + Poster);
    }

    public void setPushId(String pushId){

        this.id = pushId;
    }

    public String getAuthor(){return author;}
    public String getGenre(){return genre;}
    public String getPoster(){return poster;}
    public boolean isLiked(){return liked;}
    public void toggleLiked(){liked = !liked;}
    public String getID(){return id;}
}