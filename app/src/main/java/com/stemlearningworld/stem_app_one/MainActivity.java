package com.stemlearningworld.stem_app_one;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public static String dir1;

    //Read Database
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    //End Read Databased


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReferenceFromUrl("https://stemappone.firebaseio.com/");


        // First get the LinearLayout object.
        LinearLayout StudentBookLayout = (LinearLayout)findViewById(R.id.bookButton);
        // Implement it's on click listener.
        StudentBookLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dir1 = "https://firebasestorage.googleapis.com/v0/b/stemappone.appspot.com/o/Books%2FSide_by_Side_1_Students_Book.pdf?alt=media&token=65265be3-b1fc-4641-9941-8a42c2142044";
                startActivity(new Intent(MainActivity.this, pdfreaderActivity.class));

            }
        });
        LinearLayout WorkBookLayout = (LinearLayout)findViewById(R.id.workbookButton);
        // Implement it's on click listener.
        WorkBookLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dir1 = "https://firebasestorage.googleapis.com/v0/b/stemappone.appspot.com/o/Books%2FSide_by_Side_1_Activity_Workbook.pdf?alt=media&token=07702c48-6d4f-420a-9bdb-c68a6048630b";
                startActivity(new Intent(MainActivity.this, pdfreaderActivity.class));

            }
        });


        LinearLayout YoutubeLayout = (LinearLayout)findViewById(R.id.videoButton);
        // Implement it's on click listener.
        YoutubeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mChild = databaseReference.child("video");
                mChild.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String videoDir = dataSnapshot.getValue(String.class);
                        dir1 = videoDir;
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                startActivity(new Intent(MainActivity.this, youtubeActivity.class));

            }
        });
        // First get the LinearLayout object.
        LinearLayout FilesLayout = (LinearLayout)findViewById(R.id.filesbutton);
        // Implement it's on click listener.
        FilesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, ""+DatoDB, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, FilesActivity.class));

            }
        });

        LinearLayout DictionaryLayout = (LinearLayout)findViewById(R.id.wordref);
        // Implement it's on click listener.
        DictionaryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse("http://www.wordreference.com/"));
               startActivity(intent);

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                String facebookUrl = "https://www.facebook.com/Artificial-Intelligence-CR-1139374079528947";


                try {
                    int versionCode = getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
                    if (versionCode >= 3002850) {
                        Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));;
                    } else {
                        // open the Facebook app using the old method (fb://profile/id or fb://page/id)
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1139374079528947")));
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    // Facebook is not installed. Open the browser
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl)));
                }
            case R.id.configuration:
                String facebookUr2 = "https://www.facebook.com/profile.php?id=100013334237011";
                try {
                    int versionCode = getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
                    if (versionCode >= 3002850) {
                        Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUr2);
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));;
                    } else {
                        // open the Facebook app using the old method (fb://profile/id or fb://page/id)
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/100013334237011")));
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    // Facebook is not installed. Open the browser
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUr2)));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
