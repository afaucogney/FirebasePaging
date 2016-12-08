package com.example.afaucogney.firebasepaging.common;

import com.example.afaucogney.firebasepaging.model.CardModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by afaucogney on 07/12/2016.
 */

public class FirebaseFakeDataFactory {
    public static void generateFakeData() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.removeValue();

        for (int i = 0; i < 100; i++) {
            ref.child("test").push().setValue(CardModel.newRandomInstance(i));
        }
    }
}
