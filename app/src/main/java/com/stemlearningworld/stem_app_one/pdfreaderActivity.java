package com.stemlearningworld.stem_app_one;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Stream;

public class pdfreaderActivity extends AppCompatActivity{

    String dir1 = "https://firebasestorage.googleapis.com/v0/b/stemappone.appspot.com/o/Group1%2FGass%20Heat%20transfer.pdf?alt=media&token=cdcdeb69-0568-4b89-8b43-4f49fab1accf";
    String dir2 = "https://www.periodni.com/download/tabla_periodica-color.pdf";
    PDFView pdfView;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfreader);
        pdfView = (PDFView) findViewById(R.id.pdfView);
        new RetrievePDFStream().execute(dir1);

        }
    class RetrievePDFStream extends AsyncTask<String, Void, InputStream>{

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if(urlConnection.getResponseCode()==200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch (IOException e){
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
        }
    }

}
