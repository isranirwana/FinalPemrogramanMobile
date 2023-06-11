package com.example.cinecore.Networking;

import com.example.cinecore.Model.MovieResponse;
import com.example.cinecore.Model.TvResponse;
import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiService {

    @GET("movie/popular?api_key=95b6039a10870ec1fc877c0eaf7e30dc")
    Call<MovieResponse> getListMovie();

    @GET("tv/popular?api_key=95b6039a10870ec1fc877c0eaf7e30dc")
    Call<TvResponse> getListTv();

}
