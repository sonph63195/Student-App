package com.duatson.studentapp.network;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDb {

    public FirebaseDb() {
        // empty
    }

    public static DatabaseReference makeDbRef(String path) {
        return FirebaseDatabase.getInstance().getReference(path);
    }

    public static DatabaseReference makeDbRef() {
        return FirebaseDatabase.getInstance().getReference();
    }

}
