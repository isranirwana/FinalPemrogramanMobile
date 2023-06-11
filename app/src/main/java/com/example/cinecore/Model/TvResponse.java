package com.example.cinecore.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvResponse {

    @SerializedName("results")
    public List<TvModel> tvModels;

    public List<TvModel> getTvModels() {return tvModels;}

    public void setTvModels(List<TvModel> tvModels) {this.tvModels = tvModels;}
}
