package com.example.interntestapp.viewModel;

import android.os.AsyncTask;

import com.example.interntestapp.Data.UserDetailModel;
import com.example.interntestapp.Data.UserDetailRepository;

import androidx.lifecycle.ViewModel;

public class NewUserDetailViewModel extends ViewModel {

    private UserDetailRepository userDetailRepository;

    public NewUserDetailViewModel(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    public void addNewUserDetailToDatabase(UserDetailModel userDetailModel){
        AddUserTask task = new AddUserTask();
        task.execute(userDetailModel);
    }


    public class AddUserTask extends AsyncTask<UserDetailModel,Void,Void>{

        @Override
        protected Void doInBackground(UserDetailModel... userDetailModels) {
            userDetailRepository.insertUserDetail(userDetailModels[0]);
            return null;
        }
    }
}
