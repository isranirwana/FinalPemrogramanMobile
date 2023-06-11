package com.example.cinecore.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.cinecore.Adapter.TvAdapter;
import com.example.cinecore.Model.TvModel;
import com.example.cinecore.Model.TvResponse;
import com.example.cinecore.Networking.ApiConfig;
import com.example.cinecore.databinding.FragmentTvBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvFragment extends Fragment {

    private FragmentTvBinding binding;

    private TvAdapter tvAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTvBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvTv.setHasFixedSize(true);
        binding.rvTv.setLayoutManager(new GridLayoutManager(getContext(), 2));

        ApiConfig.getApiService().getListTv().enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<TvModel> tvModels = response.body().getTvModels();
                        if (tvModels != null && !tvModels.isEmpty()) {
                            tvAdapter = new TvAdapter(tvModels);
                            binding.rvTv.setAdapter(tvAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                // Menampilkan pesan kesalahan atau melakukan tindakan yang diperlukan
                Toast.makeText(getContext(), "Failed to retrieve TV data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}