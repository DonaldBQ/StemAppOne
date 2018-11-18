package com.stemlearningworld.stem_app_one;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String videoDir;
    public static String BookRef;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    private final static int Interval = 1000*30*1; // 2 minutos
    Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Retrieve an instance of the database and reference the location you want to read
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReferenceFromUrl("https://stemappone.firebaseio.com/");

        //Plotting information



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
        //Button Smart
        LinearLayout SmartLayout = (LinearLayout)findViewById(R.id.smart);
        SmartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setContentView(R.layout.activity_smart);
               // mHandlerTask.run();

               startActivity(new Intent(MainActivity.this, LightTester.class));


            }
        });
    //********End: Getting Linear Layouts and implementing them on click listener*******
    }
    //***********Begin: Graficacion**********************

/*        Runnable mHandlerTask = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, LightTester.class));
                mHandler.postDelayed(mHandlerTask, Interval);
            }
        };
        void startRepetingTask(){
            mHandlerTask.run();
        }
        void stopRepeatingTask(){
            mHandler.removeCallbacks(mHandlerTask);
        }*/






    //*****************End: Graficacion******************

}
