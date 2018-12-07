package com.liuhe.demoipc;

import android.os.Parcel;
import android.os.Parcelable;

public class Music implements Parcelable{
    private String title;

    protected Music(Parcel in) {
        title = in.readString();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }
}
