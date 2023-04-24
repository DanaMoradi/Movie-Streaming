package com.example.bbc.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SliderModel implements Parcelable {

    private Long id;
    private String name;
    private String img;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.img);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        this.name = source.readString();
        this.img = source.readString();
    }

    public SliderModel() {
    }

    protected SliderModel(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.img = in.readString();
    }

    public static final Parcelable.Creator<SliderModel> CREATOR = new Parcelable.Creator<SliderModel>() {
        @Override
        public SliderModel createFromParcel(Parcel source) {
            return new SliderModel(source);
        }

        @Override
        public SliderModel[] newArray(int size) {
            return new SliderModel[size];
        }
    };
}
