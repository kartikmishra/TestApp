package com.example.interntestapp.dependencyInjection;

import android.app.Application;

import com.example.interntestapp.Data.UserDetailDatabase;
import com.example.interntestapp.Data.UserDetailModelDAO;
import com.example.interntestapp.Data.UserDetailRepository;
import com.example.interntestapp.viewModel.CustomViewModelFactory;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private final UserDetailDatabase userDetailDatabase;

    public RoomModule(Application application){
        this.userDetailDatabase = Room.databaseBuilder(application
                ,UserDetailDatabase.class,"UserDetail.db").build();
    }


    @Provides
    @Singleton
    UserDetailRepository provideUserDetailRepository(UserDetailModelDAO userDetailModelDAO){
        return new UserDetailRepository(userDetailModelDAO);
    }

    @Provides
    @Singleton
     UserDetailModelDAO provideUserDetailModelDao(UserDetailDatabase detailDatabase){
        return detailDatabase.userDetailModelDAO();
    }

    @Provides
    @Singleton
    UserDetailDatabase provideUserDetailDatabase(Application application){
        return userDetailDatabase;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(UserDetailRepository repository){
        return new CustomViewModelFactory(repository);
    }
}
