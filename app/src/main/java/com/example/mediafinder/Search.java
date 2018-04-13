package com.example.mediafinder;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by thevs on 2/28/2018.
 */

public class Search extends Fragment {

    final FirebaseDatabase db = FirebaseDatabase.getInstance();
    ArrayList list = new ArrayList();
    ListView lv;
    AutoCompleteTextView criteria;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = this.getContext();
        lv = view.findViewById(R.id.searchList);
        view.findViewById(R.id.background).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        db.getReference("media").child("1");
        final DatabaseReference ref = db.getReference("media");
        final SearchAdapter searchAdapter = new SearchAdapter(this.getContext(), R.id.searchList, list);
        lv.setAdapter(searchAdapter);
        criteria = view.findViewById(R.id.criteria);

        //This is the code that searches for the media based off of the entered text
        view.findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchAdapter.clear();
                final String search = criteria.getText().toString().trim().toLowerCase();
                ref.child("movies").orderByChild("lowerCaseName").startAt(search).endAt(search + "\uf8ff").addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        searchAdapter.add(dataSnapshot.getValue(Movie.class));
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                ref.child("books").orderByChild("lowerCaseName").startAt(search).endAt(search + "\uf8ff").addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        searchAdapter.add(dataSnapshot.getValue(Book.class));
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                ref.child("games").orderByChild("lowerCaseName").startAt(search).endAt(search + "\uf8ff").addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        searchAdapter.add(dataSnapshot.getValue(Game.class));
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        //This is the code to correctly upload the media to the firebase database
        view.findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.removeValue();
                AssetManager assetManager = context.getAssets();
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(getContext().getAssets().open("IMDB's Top 250.txt")));
                    String line;
                    String[] lines;
                    String line2;
                    String string;
                    String[] arr;
                    String string2;
                    while ((line = bufferedReader2.readLine()) != null) {
                        string = "";
                        lines = line.split("\\.");
                        for(int i = 1; i < lines.length; i++)
                            string += lines[i] + ".";
                        line2 = bufferedReader2.readLine();
                        arr = string.split("\\(");
                        ref.child("movies").push().setValue(new Movie(arr[0].trim(), arr[1].split("\\)")[0], line2, lines[0]));
                        bufferedReader2.readLine();
                    }
                    bufferedReader2 = new BufferedReader(new InputStreamReader(getContext().getAssets().open("Goodreads' Best Books Ever.txt")));
                    while ((line = bufferedReader2.readLine()) != null) {
                        lines = line.split("\\(");
                        if(lines.length == 1)
                            string2 = "Not part of a series";
                        else
                            string2 = lines[1];
                        line2 = bufferedReader2.readLine();
                        string = bufferedReader2.readLine();
                        arr = string.split("—");
                        ref.child("books").push().setValue(new Book(lines[0], string2.split("\\)")[0], line2, arr[0].split(" ")[0], arr[1].trim().split(" ")[0]));
                        bufferedReader2.readLine();
                    }
                    bufferedReader2 = new BufferedReader(new InputStreamReader(getContext().getAssets().open("Wikipedia's list of First Person Shooters.txt")));
                    while ((line = bufferedReader2.readLine()) != null) {
                        ref.child("games").push().setValue(new Game(line, bufferedReader2.readLine(), bufferedReader2.readLine(),bufferedReader2.readLine()));
                        bufferedReader2.readLine();
                    }
                    bufferedReader2.close();
                } catch (FileNotFoundException ex) {
                    System.out.println(
                            "Unable to open file");
                } catch (IOException ex) {
                    System.out.println(
                            "Error reading file");
                }
            }
        });
    }
}
