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
	
//Default constructor for the Book object
    public Book(){}
	
/**
 * Parameterized constructor for the Book object.
 * author: Author of the book
 * genre: Genre(s) of the book
 * poster: Poster/cover of the book
 * title: Title of the book
 * type: Type of the media, i.e. book has "book"
 * id: Unique id that identifies the specific media title
 */
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

//Returns the title of the book object
    @Override
    public String getTitle(){return title;}
	
//Returns the type of the book object
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

//Sets the pushId of the book object	
    public void setPushId(String pushId){this.id = pushId;}

//Returns the Author of the book object
    public String getAuthor(){return author;}
	
//Returns the Genre(s) of the book object
    public String getGenre(){return genre;}
	
//Returns the Poster of the book object
    public String getPoster(){return poster;}
	
//Returns a boolean if the book is liked or not
    public boolean isLiked(){return liked;}
	
//Toggles the liked boolean of the book object
    public void toggleLiked(){liked = !liked;}
	
//Returns the ID of the book object
    public String getID(){return id;}
}