package com.example.mediafinder;

import android.view.View;

/**
 * Created by thevs on 4/10/2018.
 */

public interface Media {
    String getName();
    String getType();
    boolean isLiked();
    void toggleLiked();
    void setResults(View view);
    void setInfo(View view);
    void setPushId(String pushId);
    String getID();

}
