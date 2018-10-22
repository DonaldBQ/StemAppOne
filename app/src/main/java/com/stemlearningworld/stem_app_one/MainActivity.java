package com.stemlearningworld.stem_app_one;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// First get the LinearLayout object.
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.bookButton);
        // Implement it's on click listener.
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, pdfreaderActivity.class));

            }
        });


    }
}
