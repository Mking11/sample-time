package com.mking1102.sample.chronos.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mking1102.sample.MainActivityViewModel;
import com.mking1102.sample.chronos.domain.utils.ChronosStates;
import com.mking1102.sample.databinding.FragmentMyBinding;

import java.util.Locale;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChronometerFragment extends Fragment {

    private FragmentMyBinding binding;
    private MainActivityViewModel viewModel;
    private Button toggleButton;
    private Button actionButton;
    private LapAdapter lapAdapter;

    private RecyclerView lapView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        toggleButton = binding.btnToggle;
        actionButton = binding.btnLap;


        lapView = binding.lapTimesList;


        lapView.setLayoutManager(new LinearLayoutManager(requireContext()));
        lapAdapter = new LapAdapter();
        lapView.setAdapter(lapAdapter);


        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

        toggleButton.setOnClickListener(c -> viewModel.toggleChronometer());
        viewModel.getChronosUiState().observe(getViewLifecycleOwner(), state -> {


            String formattedTime = String.format(Locale.getDefault(), "%01d:%02d:%02d.%d",
                    state.getHours(),
                    state.getMinutes(),
                    state.getSeconds(),
                    state.getMiiSeconds());

            binding.timerDisplay.setText(formattedTime);


        });


        viewModel.getChronosStates().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case Running:
                    toggleButton.setText("Stop");
                    actionButton.setText("LAP");
                    break;
                case Initial:
                case Paused:
                    toggleButton.setText("Start");
                    actionButton.setText("Reset");
                    break;
                default:
                    break;
            }

            actionButton.setOnClickListener(c -> {
                if (Objects.requireNonNull(state) == ChronosStates.Running) {
                    viewModel.addLap();
                } else {
                    viewModel.resetChronometer();
                }
            });
        });

        viewModel.getTimeStates().observe(getViewLifecycleOwner(), list -> {
            lapAdapter.updateList(list); // Ensure new list reference is passed
            lapView.post(() -> lapView.scrollToPosition(0));
        });
    }


    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
        binding = null;
    }
}
