package com.example.mediafinder;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.*;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashScreen extends AwesomeSplash{


    @Override
    public void initSplash(ConfigSplash configSplash) {

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


            configSplash.setBackgroundColor(R.color.colorPrimaryDark);
            configSplash.setAnimCircularRevealDuration(3000);
            configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
            configSplash.setRevealFlagX(Flags.REVEAL_BOTTOM);
            configSplash.setLogoSplash(R.drawable.mediafinder_logo);

            configSplash.setAnimLogoSplashDuration(5000);
            configSplash.setAnimLogoSplashTechnique(Techniques.FadeInUp);

            configSplash.setTitleSplash("CSC 3380 Group Project");
            configSplash.setTitleTextColor(R.color.colorPrimary);
            configSplash.setTitleTextSize(30f);
            configSplash.setAnimTitleDuration(3000);
            configSplash.setAnimTitleTechnique(Techniques.Landing);
        }





    @Override
    public void animationsFinished() {
      startActivity(new Intent(SplashScreen.this, MainActivity.class
      ));
    }
}