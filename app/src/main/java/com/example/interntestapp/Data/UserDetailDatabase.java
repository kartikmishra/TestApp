package com.example.interntestapp.Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserDetailModel.class}, version = 2,exportSchema = false)
public abstract class UserDetailDatabase extends RoomDatabase {

    public abstract UserDetailModelDAO userDetailModelDAO();
}
