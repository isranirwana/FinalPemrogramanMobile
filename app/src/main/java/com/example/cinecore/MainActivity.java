package com.example.cinecore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import com.example.cinecore.Fragment.MovieFragment;
import com.example.cinecore.Fragment.FavoritesFragment;
import com.example.cinecore.Fragment.TvFragment;
import com.example.cinecore.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FragmentManager fragmentManager;
    MovieFragment moviesFragment = new MovieFragment();
    TvFragment tvShowsFragment = new TvFragment();
    FavoritesFragment favoritesFragment = new FavoritesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(MovieFragment.class.getSimpleName());

        if (!(fragment instanceof MovieFragment)) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, moviesFragment, MovieFragment.class.getSimpleName())
                    .commit();
            binding.toolbar.setText("Movies");

        }

        binding.btnMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.frame_container, moviesFragment).commit();
                binding.toolbar.setText("Movies");
            }
        });


        binding.btnTvShows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.frame_container, tvShowsFragment).commit();
                binding.toolbar.setText("TV Shows");
            }
        });

        binding.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().replace(R.id.frame_container, favoritesFragment).commit();
                binding.toolbar.setText("Favorites");
            }
        });
    }
}
