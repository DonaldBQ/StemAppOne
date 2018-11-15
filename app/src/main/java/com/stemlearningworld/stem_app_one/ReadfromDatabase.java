package com.stemlearningworld.stem_app_one;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReadfromDatabase {

    //***************Get database data *******************
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseRootRef = firebaseDatabase.getReference();
    DatabaseReference pdfRef = databaseRootRef.child("video");
    String DatoDB;
    //****************************************************

    public void onDataChange(DataSnapshot dataSnapshot){
        if(dataSnapshot.getValue(String.class)!=null){
            String key=dataSnapshot.getKey();

            if(key.equals("video")){
                String DatoDB = dataSnapshot.getValue(String.class);

            }


        }

    }
}
