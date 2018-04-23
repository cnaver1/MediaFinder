package com.example.mediafinder;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button button;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    GoogleApiClient mGoogleApiClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else {
            Toast.makeText(MainActivity.this, "Welcome to MediaFinder "+mAuth.getCurrentUser().getDisplayName()+"!", Toast.LENGTH_SHORT).show();

        }



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new Search()).commit();
        navigationView.getMenu().getItem(0).setChecked(true);


        if(mAuth.getCurrentUser() != null) {

            String userName = mAuth.getCurrentUser().getDisplayName();
            String userEmail = mAuth.getCurrentUser().getEmail();
            String userPhoto = mAuth.getCurrentUser().getPhotoUrl().toString();




            NavigationView nav = (NavigationView) findViewById(R.id.nav_view);
            View headerView = navigationView.getHeaderView(0);

            TextView navUsername = (TextView) headerView.findViewById(R.id.userName);
            navUsername.setText(userName);

            TextView navEmail = (TextView) headerView.findViewById(R.id.userEmail);
            navEmail.setText(userEmail);

            ImageView userPic = (ImageView) headerView.findViewById(R.id.userPhoto);

            Glide.with(getApplicationContext()).load(userPhoto)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(userPic);



        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        switch (id){
            default:
            case R.id.nav_search:
                fragment = new Search();
                break;
            case R.id.nav_movies:
                fragment = new MoviePage();
                break;
            case R.id.nav_books:
                fragment = new BookPage();
                break;
            case R.id.nav_games:
                fragment = new GamePage();
                break;
            case R.id.nav_nim:
                fragment = new NimPage();
                break;
            case R.id.nav_settings:
                fragment = new Settings();
                break;
            case R.id.nav_signout:
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));

                break;
        }

        if(fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_main, fragment);
            fragmentTransaction.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
