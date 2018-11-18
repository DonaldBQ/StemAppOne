package com.stemlearningworld.stem_app_one;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public static String videoDir;
    public static String BookRef;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Retrieve an instance of the database and reference the location you want to read
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReferenceFromUrl("https://stemappone.firebaseio.com/");

        //********Begin: Getting Linear Layouts and implementing them on click listener********
        //Button Student Book
        LinearLayout StudentBookLayout = (LinearLayout)findViewById(R.id.bookButton);
        StudentBookLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mChild = databaseReference.child("Book1");
                mChild.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        BookRef=dataSnapshot.getValue(String.class);
                        startActivity(new Intent(MainActivity.this, pdfreaderActivity.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
        //Button Work Book
        LinearLayout WorkBookLayout = (LinearLayout)findViewById(R.id.workbookButton);
        WorkBookLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mChild = databaseReference.child("Book2");
                mChild.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        BookRef=dataSnapshot.getValue(String.class);
                        startActivity(new Intent(MainActivity.this, pdfreaderActivity.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
        //Button Youtube
       LinearLayout YoutubeLayout = (LinearLayout)findViewById(R.id.videoButton);
       YoutubeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mChild = databaseReference.child("video");
                mChild.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        videoDir = dataSnapshot.getValue(String.class);

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                startActivity(new Intent(MainActivity.this, youtubeActivity.class));
            }
        });
       //Button Archivos
        LinearLayout FilesLayout = (LinearLayout)findViewById(R.id.filesbutton);
        FilesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FilesActivity.class));
            }
        });
        //Button Word Reference
        LinearLayout DictionaryLayout = (LinearLayout)findViewById(R.id.wordref);
        DictionaryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse("http://www.wordreference.com/"));
               startActivity(intent);
            }
        });
    //********End: Getting Linear Layouts and implementing them on click listener*******
    }

}
