package com.example.bbc.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModelResponse implements Parcelable {

    private String status;
    private long id;
    private String full_name;
    private String email;
    private String phone;
    private String password;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeLong(this.id);
        dest.writeString(this.full_name);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.password);
    }

    public void readFromParcel(Parcel source) {
        this.status = source.readString();
        this.id = source.readLong();
        this.full_name = source.readString();
        this.email = source.readString();
        this.phone = source.readString();
        this.password = source.readString();
    }

    public UserModelResponse() {
    }

    protected UserModelResponse(Parcel in) {
        this.status = in.readString();
        this.id = in.readLong();
        this.full_name = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.password = in.readString();
    }

    public static final Parcelable.Creator<UserModelResponse> CREATOR = new Parcelable.Creator<UserModelResponse>() {
        @Override
        public UserModelResponse createFromParcel(Parcel source) {
            return new UserModelResponse(source);
        }

        @Override
        public UserModelResponse[] newArray(int size) {
            return new UserModelResponse[size];
        }
    };
}
