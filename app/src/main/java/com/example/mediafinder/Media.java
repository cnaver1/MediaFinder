package com.example.mediafinder;

import android.view.View;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by thevs on 4/10/2018.
 */

//Interface for the the book, movie, and game objects.
	public interface Media {
		String getTitle();
		String getType();
		String getPoster();
		boolean isLiked();
		void toggleLiked();
		void setResults(View view);
		void setInfo(View view);
		void setPushId(String pushId);
		String getID();
	}
