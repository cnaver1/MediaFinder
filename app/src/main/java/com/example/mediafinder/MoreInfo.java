package com.example.mediafinder;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by thevs on 4/10/2018.
 */

public class MoreInfo extends Activity {

    public static Media media;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_info);
        media.setInfo(findViewById(android.R.id.content));
    }
}
