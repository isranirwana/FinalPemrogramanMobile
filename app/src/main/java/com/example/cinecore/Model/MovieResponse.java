package com.example.cinecore.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    public List<MovieModel> movieModels;

    public List<MovieModel> getMovieModels() {return movieModels;}

    public void setMovieModels(List<MovieModel> movieModels) {this.movieModels = movieModels;}
}