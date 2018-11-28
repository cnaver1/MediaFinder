package com.example.mediafinder;

import android.app.Activity;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by thevs on 4/10/2018.
 * This is the page that displays more info about the media type
 */

public class MoreInfo extends Activity {

    public static Media media;

//Called to do initial creation of a fragment
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_info);
        media.setInfo(findViewById(android.R.id.content));
        ImageView img = (ImageView)findViewById(R.id.imageView4);

        Glide.with(getApplicationContext()).load(media.getPoster()).into(img);

        final Button likeButton = (Button) findViewById(R.id.likeButton);
        if (media.isLiked())
            likeButton.setText("Unlike");
        else
            likeButton.setText("Like");
        likeButton.setOnClickListener(new View.OnClickListener() {
		//Called when a view has been clicked
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                DatabaseReference mediaRef = FirebaseDatabase
                        .getInstance()
                        .getReference(Constants.FIREBASE_CHILD_MEDIA).child(uid);
                if (media.isLiked()) {
                    System.out.println(media.getTitle() + ": " + uid + ", " + media.getID());
                    mediaRef.child(media.getID()).removeValue();
                } else {
                    DatabaseReference pushRef = mediaRef.push();
                    String pushId = pushRef.getKey();
                    media.setPushId(pushId);
                    media.toggleLiked();
                    pushRef.setValue(media);
                }


                Toast.makeText(MoreInfo.this, "Saved!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}

