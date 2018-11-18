package com.stemlearningworld.stem_app_one;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FilesActivity extends AppCompatActivity {
    //Declarar un elemento tipo FirebaseStorage
    //Obtener referencia del elemento que se quiere descargar.
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://stemappone.appspot.com").child("Group1/ic_action_android.png");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);


        RelativeLayout FileGroup1Layout;
        FileGroup1Layout = findViewById(R.id.file_group1);

        //Prueba para mostrar elemento descargado en un ImageView
        final ImageView imageView = findViewById(R.id.image);


        FileGroup1Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Descarga temporal del elemento y muestra en ImageView imageView
                final long ONE_MEGABYTE = 1024 * 1024;

                //download file as a byte array
                storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        imageView.setImageBitmap(bitmap);
                        //Toast("Download successful!");
                    }
                });


            }
        });
    }
}
