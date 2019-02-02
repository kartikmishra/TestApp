package com.example.interntestapp.viewModel;

import com.example.interntestapp.Data.UserDetailRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CustomViewModelFactory implements ViewModelProvider.Factory {

    private final UserDetailRepository repository;

    public CustomViewModelFactory(UserDetailRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(UserDetailCollectionsViewModel.class)){
            return (T) new UserDetailCollectionsViewModel(repository);
        }
        else if(modelClass.isAssignableFrom(UserDetailViewModel.class)){
            return (T) new UserDetailViewModel(repository);
        }
        else if(modelClass.isAssignableFrom(NewUserDetailViewModel.class)){
            return (T) new NewUserDetailViewModel(repository);
        }

        else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }

    }
}
