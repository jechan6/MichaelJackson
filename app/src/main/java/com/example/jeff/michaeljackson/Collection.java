package com.example.jeff.michaeljackson;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Jeff on 6/23/2017.
 */

public class Collection implements Parcelable {
    private String collection;
    private String collectionPrice;
    private String trackPrice;
    private String date;
    private String track;
    private String image;
    private String genre;
    private String id;

    public Collection(String id,String collection, String tPrice, String cPrice, String date, String track, String image, String genre) {
        this.collection = collection;
        this.collectionPrice = cPrice;
        this.trackPrice = tPrice;
        this.date = date;
        this.track = track;
        this.image = image;
        this.genre = genre;
        this.id = id;

    }

    protected Collection(Parcel in) {
        collection = in.readString();
        collectionPrice = in.readString();
        trackPrice = in.readString();
        date = in.readString();
        track = in.readString();
        image = in.readString();
        genre = in.readString();
        id = in.readString();
    }

    public static final Creator<Collection> CREATOR = new Creator<Collection>() {
        @Override
        public Collection createFromParcel(Parcel in) {
            return new Collection(in);
        }

        @Override
        public Collection[] newArray(int size) {
            return new Collection[size];
        }
    };

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(String collectionPrice) {
        this.collectionPrice = collectionPrice;
    }
    public String getId() {
        return id;
    }

    public String getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(String trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(collection);
        dest.writeString(collectionPrice);
        dest.writeString(trackPrice);
        dest.writeString(date);
        dest.writeString(track);
        dest.writeString(image);
        dest.writeString(genre);
        dest.writeString(id);
    }
}
