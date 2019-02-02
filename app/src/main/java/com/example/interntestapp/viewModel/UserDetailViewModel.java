package com.example.interntestapp.viewModel;

import com.example.interntestapp.Data.UserDetailModel;
import com.example.interntestapp.Data.UserDetailRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class UserDetailViewModel extends ViewModel {

    private UserDetailRepository userDetailRepository;

    UserDetailViewModel(UserDetailRepository userDetailRepository){
        this.userDetailRepository = userDetailRepository;
    }

    public LiveData<UserDetailModel> getUserDetailById(int id){
        return userDetailRepository.getUserDetailById(id);
    }
}
