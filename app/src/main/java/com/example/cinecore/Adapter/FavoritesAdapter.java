package com.example.cinecore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinecore.Model.MovieModel;
import com.example.cinecore.Model.TvModel;
import com.example.cinecore.R;
import com.example.cinecore.databinding.ItemFavoritesBinding;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private List<MovieModel> favoriteMovies;
    private List<TvModel> favoriteTVs;
    private OnItemClickListener clickListener;

    public FavoritesAdapter(List<MovieModel> favoriteMovies, List<TvModel> favoriteTVs) {
        this.favoriteMovies = favoriteMovies;
        this.favoriteTVs = favoriteTVs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFavoritesBinding binding = ItemFavoritesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position < favoriteMovies.size()) {
            final MovieModel movie = favoriteMovies.get(position);
            holder.bind(movie);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onItemClick(movie);
                    }
                }
            });
        } else {
            int tvPosition = position - favoriteMovies.size();
            final TvModel tv = favoriteTVs.get(tvPosition);
            holder.bind(tv);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.onItemClick(tv);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return favoriteMovies.size() + favoriteTVs.size();
    }

    public void updateData(List<MovieModel> favoriteMovies, List<TvModel> favoriteTVs) {
        this.favoriteMovies = favoriteMovies;
        this.favoriteTVs = favoriteTVs;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemFavoritesBinding binding;

        public ViewHolder(@NonNull ItemFavoritesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MovieModel movie) {
            binding.tvMovieTitle.setText(movie.getOriginal_title());
            binding.tvReleaseDate.setText(movie.getRelease_date());
            binding.ivType.setImageResource(R.drawable.movies);

            Glide.with(binding.getRoot().getContext())
                    .load("https://image.tmdb.org/t/p/w500" + movie.getPoster_path())
                    .into(binding.ivPosterPath);
        }

        public void bind(TvModel tv) {
            binding.tvMovieTitle.setText(tv.getOriginal_name());
            binding.tvReleaseDate.setText(tv.getFirst_air_date());
            binding.ivType.setImageResource(R.drawable.tv_shows);

            Glide.with(binding.getRoot().getContext())
                    .load("https://image.tmdb.org/t/p/w500" + tv.getPoster_path())
                    .into(binding.ivPosterPath);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(MovieModel movie);
        void onItemClick(TvModel tv);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }
}

