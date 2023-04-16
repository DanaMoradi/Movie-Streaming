package com.example.bbc.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TopMovieModel implements Parcelable {
    private int id;
    private String name;
    private String img;
    private String director;
    private String rate_imdb;
    private String published;
    private String time;
    private String genre_name;
    private String description;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.img);
        dest.writeString(this.director);
        dest.writeString(this.rate_imdb);
        dest.writeString(this.published);
        dest.writeString(this.time);
        dest.writeString(this.genre_name);
        dest.writeString(this.description);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.name = source.readString();
        this.img = source.readString();
        this.director = source.readString();
        this.rate_imdb = source.readString();
        this.published = source.readString();
        this.time = source.readString();
        this.genre_name = source.readString();
        this.description = source.readString();
    }

    public TopMovieModel() {
    }

    protected TopMovieModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.img = in.readString();
        this.director = in.readString();
        this.rate_imdb = in.readString();
        this.published = in.readString();
        this.time = in.readString();
        this.genre_name = in.readString();
        this.description = in.readString();
    }

    public static final Creator<TopMovieModel> CREATOR = new Creator<TopMovieModel>() {
        @Override
        public TopMovieModel createFromParcel(Parcel source) {
            return new TopMovieModel(source);
        }

        @Override
        public TopMovieModel[] newArray(int size) {
            return new TopMovieModel[size];
        }
    };
}
