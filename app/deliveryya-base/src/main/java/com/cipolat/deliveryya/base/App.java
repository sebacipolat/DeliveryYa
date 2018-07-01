package com.cipolat.deliveryya.base;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by sebastian on 22/03/18.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
