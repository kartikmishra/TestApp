package com.example.interntestapp.dependencyInjection;

import android.app.Application;

import com.example.interntestapp.MainActivityFragment;
import com.example.interntestapp.SignUpFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {

      void inject(SignUpFragment signUpFragment);
      void inject(MainActivityFragment mainActivityFragment);

    Application application();
}
