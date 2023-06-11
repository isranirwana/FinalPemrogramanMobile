package com.example.cinecore;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.cinecore.Model.MovieModel;
import com.example.cinecore.Model.TvModel;
import com.example.cinecore.Database.DatabaseHelper;
import com.example.cinecore.Database.ModelHelper;
import com.example.cinecore.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra-movie";
    public static final String EXTRA_TV = "extra-tv";

    private ActivityDetailsBinding binding;
    private DatabaseHelper databaseHelper;
    private boolean isFavorite;
    private TvModel tvModel;
    private MovieModel movieModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = DatabaseHelper.getInstance(this);

        TvModel tvModel = getIntent().getParcelableExtra(EXTRA_TV);
        MovieModel movieModel = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if (tvModel != null) {
            showTvDetails(tvModel);
        } else if (movieModel != null) {
            showMovieDetails(movieModel);
        }

        binding.ivBack.setOnClickListener(view -> onBackPressed());
    }

    private void showTvDetails(TvModel tvModel) {
        binding.tvMovieTitle.setText(tvModel.getOriginal_name());
        binding.tvReleaseDate.setText(tvModel.getFirst_air_date());
        binding.tvOverview.setText(tvModel.getOverview());
        binding.tvRate.setText(tvModel.getVote_average());
        binding.ivType.setImageResource(R.drawable.tv_shows);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + tvModel.getPoster_path()).into(binding.ivPosterPath);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + tvModel.getBackdrop_path()).into(binding.ivBackdropPath);

        isFavorite = ModelHelper.isFavoriteTv(this, tvModel.getId());
        updateFavoriteButton();

        binding.ivFavorite.setOnClickListener(v -> {
            if (isFavorite) {
                removeFromFavorites(tvModel);
            } else {
                addToFavorites(tvModel);
            }
        });
    }

    private void showMovieDetails(MovieModel movieModel) {
        binding.tvMovieTitle.setText(movieModel.getOriginal_title());
        binding.tvReleaseDate.setText(movieModel.getRelease_date());
        binding.tvOverview.setText(movieModel.getOverview());
        binding.tvRate.setText(movieModel.getVote_average());
        binding.ivType.setImageResource(R.drawable.movies);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movieModel.getPoster_path()).into(binding.ivPosterPath);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movieModel.getBackdrop_path()).into(binding.ivBackdropPath);

        isFavorite = ModelHelper.isFavoriteMovie(this, movieModel.getId());
        updateFavoriteButton();

        binding.ivFavorite.setOnClickListener(v -> {
            if (isFavorite) {
                removeFromFavorites(movieModel);
            } else {
                addToFavorites(movieModel);
            }
        });
    }

    private void addToFavorites(TvModel tvModel) {
        if (databaseHelper != null) {
            ModelHelper.insertTv(this, tvModel);
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
            isFavorite = true;
            updateFavoriteButton();
        }
    }

    private void addToFavorites(MovieModel movieModel) {
        if (databaseHelper != null) {
            ModelHelper.insertMovie(this, movieModel);
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
            isFavorite = true;
            updateFavoriteButton();
        }
    }

    private void removeFromFavorites(TvModel tvModel) {
        if (databaseHelper != null) {
            ModelHelper.deleteTv(this, tvModel.getId());
            Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
            isFavorite = false;
            updateFavoriteButton();
        }
    }

    private void removeFromFavorites(MovieModel movieModel) {
        if (databaseHelper != null) {
            ModelHelper.deleteMovie(this, movieModel.getId());
            Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
            isFavorite = false;
            updateFavoriteButton();
        }
    }

    private void updateFavoriteButton() {
        // Change the favorite button's image based on the favorite status
        int favoriteIcon = isFavorite ? R.drawable.liked : R.drawable.faves;
        binding.ivFavorite.setImageResource(favoriteIcon);
    }
}
