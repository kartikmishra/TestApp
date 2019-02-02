package com.example.interntestapp.Data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import dagger.Provides;

@Dao
public interface UserDetailModelDAO {


    @Query("SELECT  * FROM UserDetailModel")
    LiveData<List<UserDetailModel>> getUserDetails();

    @Query("SELECT * FROM UserDetailModel WHERE id= :id")
    LiveData<UserDetailModel> getUserDetailById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertUserDetail(UserDetailModel userDetailModel);

    @Delete
    void deleteUserDetail(UserDetailModel userDetailModel);
}
