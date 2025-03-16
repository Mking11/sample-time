package com.mking1102.sample.chronos.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.mking1102.sample.databinding.LapLayoutBinding;
import com.mking1102.sample.chronos.domain.models.TimeStates;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LapAdapter extends ListAdapter<TimeStates, LapAdapter.LapViewHolder> {

    private static final DiffUtil.ItemCallback<TimeStates> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull TimeStates oldItem, @NonNull TimeStates newItem) {
            return Objects.equals(oldItem.getLap(), newItem.getLap()); // Compare by lap ID
        }

        @Override
        public boolean areContentsTheSame(@NonNull TimeStates oldItem, @NonNull TimeStates newItem) {
            return oldItem.equals(newItem);
        }
    };

    public LapAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public LapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LapViewHolder(LapLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LapViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public void updateList(List<TimeStates> timeStates) {
        submitList(new ArrayList<>(timeStates)); // Ensures a new list reference
    }

    static class LapViewHolder extends RecyclerView.ViewHolder {
        private final LapLayoutBinding binding;

        LapViewHolder(LapLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(TimeStates timeStates) {
            binding.lapText.setText(timeStates.getLap());
            binding.lapTime.setText(timeStates.getLapTime());
            binding.totalTime.setText(timeStates.getTotalTime());
        }
    }
}
