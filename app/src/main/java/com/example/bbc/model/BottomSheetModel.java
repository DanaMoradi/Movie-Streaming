package com.example.bbc.model;


import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BottomSheetModel implements Parcelable {

    private Long id;
    private String img;
    private String name;
    private String director;
    private String time;
    private String rate_imdb;
    private String description;
    private String category_name;
    private String genre_name;
    private String published;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.img);
        dest.writeString(this.name);
        dest.writeString(this.director);
        dest.writeString(this.time);
        dest.writeString(this.rate_imdb);
        dest.writeString(this.description);
        dest.writeString(this.category_name);
        dest.writeString(this.genre_name);
        dest.writeString(this.published);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        this.img = source.readString();
        this.name = source.readString();
        this.director = source.readString();
        this.time = source.readString();
        this.rate_imdb = source.readString();
        this.description = source.readString();
        this.category_name = source.readString();
        this.genre_name = source.readString();
        this.published = source.readString();
    }

    public BottomSheetModel() {
    }

    protected BottomSheetModel(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.img = in.readString();
        this.name = in.readString();
        this.director = in.readString();
        this.time = in.readString();
        this.rate_imdb = in.readString();
        this.description = in.readString();
        this.category_name = in.readString();
        this.genre_name = in.readString();
        this.published = in.readString();
    }

    public static final Creator<BottomSheetModel> CREATOR = new Creator<BottomSheetModel>() {
        @Override
        public BottomSheetModel createFromParcel(Parcel source) {
            return new BottomSheetModel(source);
        }

        @Override
        public BottomSheetModel[] newArray(int size) {
            return new BottomSheetModel[size];
        }
    };
}
