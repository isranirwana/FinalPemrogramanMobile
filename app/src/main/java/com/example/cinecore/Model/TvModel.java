package com.example.cinecore.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TvModel implements Parcelable {
    @SerializedName("id")
    private int id;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("original_name")
    private String original_name;

    @SerializedName("first_air_date")
    private String first_air_date;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("vote_average")
    private String vote_average;

    public TvModel(int id, String backdropPath, String originalName, String firstAirDate, String overview, String posterPath, String voteAverage) {

    }

    protected TvModel(Parcel in) {
        id = in.readInt();
        backdrop_path = in.readString();
        original_name = in.readString();
        first_air_date = in.readString();
        overview = in.readString();
        poster_path = in.readString();
        vote_average = in.readString();
    }

    public static final Creator<TvModel> CREATOR = new Creator<TvModel>() {
        @Override
        public TvModel createFromParcel(Parcel in) {
            return new TvModel(in);
        }

        @Override
        public TvModel[] newArray(int size) {
            return new TvModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(backdrop_path);
        parcel.writeString(original_name);
        parcel.writeString(first_air_date);
        parcel.writeString(overview);
        parcel.writeString(poster_path);
        parcel.writeString(vote_average);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public int getId() {
        return id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getVote_average() {
        return vote_average;
    }
}