package com.mking1102.sample;

import android.os.Bundle;
import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mking1102.sample.chronos.data.repositories.ChronosRepository;
import com.mking1102.sample.chronos.domain.models.ChronosPref;
import com.mking1102.sample.chronos.domain.models.TimeStates;
import com.mking1102.sample.chronos.domain.utils.ChronosStates;
import com.mking1102.sample.chronos.domain.models.ChronosUiState;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainActivityViewModel extends ViewModel {
    private final MutableLiveData<ChronosUiState> chronosUiState = new MutableLiveData<>();
    private final MutableLiveData<ChronosStates> chronosStates = new MutableLiveData<>(ChronosStates.Initial);


    private Boolean hasReset = false;

    private int currentLap = 0;
    private long startTime = 0L;
    private long elapsedTime = 0L;
    private long lapTime = 0L;
    private boolean running = false;
    private final Handler handler = new Handler();

    private final ChronosRepository chronosRepository;
    private final FirebaseAnalytics analytics;

    @Inject
    public MainActivityViewModel(ChronosRepository chronosRepository, FirebaseAnalytics analytics) {
        this.chronosRepository = chronosRepository;
        this.analytics = analytics;
    }

    private final Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            if (running) {
                long currentTime = System.currentTimeMillis() - startTime;
                updateChronosUiState(currentTime);
                handler.postDelayed(this, 100);
            }
        }
    };


    public LiveData<ChronosUiState> getChronosUiState() {
        return chronosUiState;
    }

    public LiveData<ChronosStates> getChronosStates() {
        return chronosStates;
    }


    public LiveData<List<TimeStates>> getTimeStates() {
        return chronosRepository.getAllTimeStates();
    }

    public void toggleChronometer() {
        if (running) {
            pauseChronometer();
        } else {
            startChronometer();
        }
    }

    private void startChronometer() {
        if (!running) {
            startTime = System.currentTimeMillis() - elapsedTime;
            lapTime = startTime;
            running = true;
            handler.post(updateTimer);
            updateChronosUiState(elapsedTime);

            if (chronosStates.getValue() == ChronosStates.Paused){
                handleTapWithLabel("Resume");
            }else{
                handleTapWithLabel("Start");
            }
            chronosStates.postValue(ChronosStates.Running);


            if (!hasReset) {
                Integer usageCount = chronosRepository.getUsage();
                chronosRepository.updateUsage(usageCount + 1);
                analytics.setUserProperty("Usage", String.valueOf(usageCount));
            }
        }
    }

    private void pauseChronometer() {
        if (running) {
            running = false;
            elapsedTime = System.currentTimeMillis() - startTime;
            handler.removeCallbacks(updateTimer);
            updateChronosUiState(elapsedTime);
            chronosStates.postValue(ChronosStates.Paused);
            hasReset = true;
            handleTapWithLabel("Stop");
        }
    }

    public void resetChronometer() {
        running = false;
        elapsedTime = 0L;
        startTime = 0L;
        currentLap = 0;
        handler.removeCallbacks(updateTimer);
        chronosRepository.deleteAll();
        chronosUiState.setValue(new ChronosUiState());
        chronosStates.postValue(ChronosStates.Initial);
        chronosRepository.clearPrefs();
        hasReset = true;
    }

    public void handleTapWithLabel(String label) {
        Bundle bundle = new Bundle();
        bundle.putString("label", label);
        analytics.logEvent("tab_with_label", bundle);

    }


    public void addLap() {
        ChronosUiState currentState = chronosUiState.getValue();
        elapsedTime = System.currentTimeMillis() - startTime;
        long currentLapTime = System.currentTimeMillis() - lapTime;
        lapTime = System.currentTimeMillis();
        if (currentState != null) {

            String totalTime = formatTime(elapsedTime);
            currentLap = currentLap + 1;
            chronosRepository.insertTimeStates(new TimeStates("Lap " + (currentLap), formatTime(currentLapTime), totalTime, currentLap));
        }
        handleTapWithLabel("Lap");
    }

    public void setLocalPrefs() {
        ChronosPref pref = new ChronosPref(startTime, lapTime, elapsedTime, currentLap);

        chronosRepository.setPrefs(pref);
    }


    public void getPrefs() {
        ChronosPref pref = chronosRepository.getCurrentPrefs();

        lapTime = pref.lapTime();
        startTime = pref.startTime();
        elapsedTime = pref.elapsedTime();
        currentLap = pref.currentLap();


        updateChronosUiState(elapsedTime);
    }


    private void updateChronosUiState(long millis) {
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60;
        long milliseconds = (millis % 1000) / 100;


        ChronosUiState newState = new ChronosUiState(
                milliseconds,
                seconds,
                minutes,
                hours
        );

        chronosUiState.postValue(newState);
    }

    private String formatTime(long millis) {
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60;
        long milliseconds = (millis % 1000) / 100;
        return String.format(Locale.getDefault(), "%01d:%02d:%02d.%d", hours, minutes, seconds, milliseconds);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
