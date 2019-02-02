package com.example.interntestapp.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserDetailModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String phoneNumber;
    String address;
    String emailAddress;
    String password;


    public UserDetailModel(String name, String phoneNumber, String address, String emailAddress, String password) {

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected UserDetailModel(Parcel in) {
        name = in.readString();
        phoneNumber = in.readString();
        address = in.readString();
        emailAddress = in.readString();
        password = in.readString();
        id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phoneNumber);
        dest.writeString(address);
        dest.writeString(emailAddress);
        dest.writeString(password);
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserDetailModel> CREATOR = new Creator<UserDetailModel>() {
        @Override
        public UserDetailModel createFromParcel(Parcel in) {
            return new UserDetailModel(in);
        }

        @Override
        public UserDetailModel[] newArray(int size) {
            return new UserDetailModel[size];
        }
    };
}
