package com.example.interntestapp.viewModel;

import android.os.AsyncTask;

import com.example.interntestapp.Data.UserDetailModel;
import com.example.interntestapp.Data.UserDetailRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class UserDetailCollectionsViewModel extends ViewModel {

    private UserDetailRepository userDetailRepository;

    public UserDetailCollectionsViewModel(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    public LiveData<List<UserDetailModel>> getUserDetail(){
        return userDetailRepository.getUserDetails();
    }

    public void deleteListItem(UserDetailModel userDetailModel){
        DeleteUserDetailTask userDetailTask = new DeleteUserDetailTask();
        userDetailTask.execute(userDetailModel);
    }

    private class DeleteUserDetailTask extends AsyncTask<UserDetailModel,Void,Void>{
        @Override
        protected Void doInBackground(UserDetailModel... userDetailModels) {
            userDetailRepository.deleteUserDetail(userDetailModels[0]);
            return null;
        }
    }
}
