package com.example.interntestapp;

import android.app.Application;

import com.example.interntestapp.dependencyInjection.ApplicationComponent;
import com.example.interntestapp.dependencyInjection.ApplicationModule;
//import com.example.interntestapp.dependencyInjection.DaggerApplicationComponent;
import com.example.interntestapp.dependencyInjection.DaggerApplicationComponent;
//import com.example.interntestapp.dependencyInjection.DaggerApplicationComponent;
import com.example.interntestapp.dependencyInjection.RoomModule;


public class RoomDemoApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
