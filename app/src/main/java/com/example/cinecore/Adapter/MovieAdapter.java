package com.example.cinecore.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.cinecore.Model.MovieModel;
import com.example.cinecore.DetailsActivity;
import com.example.cinecore.databinding.ItemMovieBinding;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final List<MovieModel> movieModelList;

    public MovieAdapter(List<MovieModel> movieModel) {this.movieModelList = movieModel;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieModel movieModel =movieModelList.get(position);
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + movieModel.getPoster_path()).into(holder.binding.imageView);
        holder.binding.tvMovieTitle.setText(movieModel.getOriginal_title());
        holder.binding.tvReleaseDate.setText(movieModel.getRelease_date());
        holder.itemView.setOnClickListener(v ->{
            Intent intent = new Intent(v.getContext(), DetailsActivity.class);
            intent.putExtra(DetailsActivity.EXTRA_MOVIE, movieModel);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {return movieModelList.size();}

    class ViewHolder extends RecyclerView.ViewHolder{
        private ItemMovieBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.binding = ItemMovieBinding.bind(itemView);
        }
    }
}
