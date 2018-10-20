package com.stemlearningworld.stem_app_one;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button Reading= (Button) findViewById(R.id.Reading_Button);
        final Button Recording= (Button) findViewById(R.id.Recording_Button);
        final Button Audio= (Button) findViewById(R.id.Audio_Button);
        final Button Test= (Button) findViewById(R.id.Test_Button);
        final Button Video= (Button) findViewById(R.id.Files_Button);
    }
}
