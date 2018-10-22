package com.stemlearningworld.stem_app_one;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class pdfreaderActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfreader);

        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        //pdfView.fromUri(Uri.parse("http://tutorialesenpdf.com/pdfs/preview/Curso%20Android%20Studio.pdf")).load();
        pdfView.fromAsset("Dragonflies.pdf").load();
    }
}
