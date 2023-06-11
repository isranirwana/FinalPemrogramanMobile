package com.example.cinecore.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.cinecore.Adapter.MovieAdapter;
import com.example.cinecore.Model.MovieModel;
import com.example.cinecore.Model.MovieResponse;
import com.example.cinecore.Networking.ApiConfig;
import com.example.cinecore.databinding.FragmentMovieBinding;

public class MovieFragment extends Fragment {

    private FragmentMovieBinding binding;
    private MovieAdapter movieAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMovieBinding.inflate(inflater, container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvMovie.setHasFixedSize(true);
        binding.rvMovie.setLayoutManager(new GridLayoutManager(getContext(),2));

        ApiConfig.getApiService().getListMovie().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        List<MovieModel> movieModel = response.body().getMovieModels();
                        for (int i = 0; i < movieModel.size(); i++) {
                            Log.d("apakah", movieModel.get(i).getOriginal_title());
                        }
                        movieAdapter = new MovieAdapter(movieModel);
                        binding.rvMovie.setAdapter(movieAdapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("aduh","ude'eh");
            }
        });
    }
}