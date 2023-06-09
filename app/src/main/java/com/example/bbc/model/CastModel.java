package com.example.bbc.model;


import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CastModel implements Parcelable {

    private int id;
    private String img;
    private String name;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.img);
        dest.writeString(this.name);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.img = source.readString();
        this.name = source.readString();
    }

    public CastModel() {
    }

    protected CastModel(Parcel in) {
        this.id = in.readInt();
        this.img = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<CastModel> CREATOR = new Parcelable.Creator<CastModel>() {
        @Override
        public CastModel createFromParcel(Parcel source) {
            return new CastModel(source);
        }

        @Override
        public CastModel[] newArray(int size) {
            return new CastModel[size];
        }
    };
}
