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
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth mAuth;

//Called to do initial creation of a fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
	// Displays a certain nav menu depending if the users is logged.
        if(mAuth.getCurrentUser() != null) {
            setContentView(R.layout.activity_main);
        }
        else{
            setContentView(R.layout.activity_guest);
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

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new HomePage()).commit();
        navigationView.getMenu().getItem(0).setChecked(true);

        //The is user is logged in the pulls their: name, email, and photo from their google
        // account and displays is in the nav menu

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
                    .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop()
                    .into(userPic);

        }
    }

//Called when the activity has detected the user's press of the back key.
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//Called when an item in the navigation menu is selected.
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        switch (id) {
            default:
            case R.id.nav_home:
                fragment = new HomePage();
                break;
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
            case R.id.nav_profile:
                fragment = new ProfilePage();
                break;
            case R.id.nav_settings:
                fragment = new Settings();
                break;
            case R.id.nav_signout:
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                break;
            case R.id.nav_signin:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
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

