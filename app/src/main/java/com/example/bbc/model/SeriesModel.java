package com.example.bbc.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SeriesModel implements Parcelable {

    private Long id;
    private String name;
    private String img;
    private String director;
    private String rate_imdb;
    private String seasonsAndEpisodes;
    private String published;
    private String category_name;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.img);
        dest.writeString(this.director);
        dest.writeString(this.rate_imdb);
        dest.writeString(this.seasonsAndEpisodes);
        dest.writeString(this.published);
        dest.writeString(this.category_name);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        this.name = source.readString();
        this.img = source.readString();
        this.director = source.readString();
        this.rate_imdb = source.readString();
        this.seasonsAndEpisodes = source.readString();
        this.published = source.readString();
        this.category_name = source.readString();
    }

    public SeriesModel() {
    }

    protected SeriesModel(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.img = in.readString();
        this.director = in.readString();
        this.rate_imdb = in.readString();
        this.seasonsAndEpisodes = in.readString();
        this.published = in.readString();
        this.category_name = in.readString();
    }

    public static final Parcelable.Creator<SeriesModel> CREATOR = new Parcelable.Creator<SeriesModel>() {
        @Override
        public SeriesModel createFromParcel(Parcel source) {
            return new SeriesModel(source);
        }

        @Override
        public SeriesModel[] newArray(int size) {
            return new SeriesModel[size];
        }
    };
}
