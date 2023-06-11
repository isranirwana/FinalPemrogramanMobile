package com.example.cinecore.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinecore.Model.TvModel;
import com.example.cinecore.DetailsActivity;
import com.example.cinecore.databinding.ItemTvBinding;
import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {

    private final List<TvModel> tvModelList;

    public TvAdapter(List<TvModel> tvModelList) {this.tvModelList = tvModelList;}

    @NonNull
    @Override
    public TvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTvBinding bind = ItemTvBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(bind.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.ViewHolder holder, int position) {
        TvModel tvModel = tvModelList.get(position);
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + tvModel.getPoster_path()).into(holder.bind.imageView);
        holder.bind.tvMovieTitle.setText(tvModel.getOriginal_name());
        holder.bind.tvReleaseDate.setText(tvModel.getFirst_air_date());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailsActivity.class);
            intent.putExtra(DetailsActivity.EXTRA_TV, tvModel);
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {return tvModelList.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemTvBinding bind;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.bind = ItemTvBinding.bind(itemView);
        }
    }
}
