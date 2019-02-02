package com.example.interntestapp.Data;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class UserDetailRepository {

    private final UserDetailModelDAO userDetailModelDAO;

    @Inject
    public UserDetailRepository(UserDetailModelDAO userDetailModelDAO){
        this.userDetailModelDAO = userDetailModelDAO;
    }

    public LiveData<List<UserDetailModel>> getUserDetails(){
        return userDetailModelDAO.getUserDetails();
    }

    public LiveData<UserDetailModel> getUserDetailById(int id){
        return userDetailModelDAO.getUserDetailById(id);
    }

    public void deleteUserDetail(UserDetailModel userDetailModel){
         userDetailModelDAO.deleteUserDetail(userDetailModel);
    }

    public void insertUserDetail(UserDetailModel userDetailModel){
        userDetailModelDAO.insertUserDetail(userDetailModel);
    }
}
