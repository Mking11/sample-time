package com.mking1102.sample;

import android.app.Application;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mking1102.sample.chronos.data.repositories.ChronosRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class ChronoApplication extends Application {

    @Inject
    FirebaseAnalytics analytics;

    @Inject
    ChronosRepository chronosRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        Boolean isFirstRun = chronosRepository.getIsFirstRun();
        if (isFirstRun) {
            setFirstRunData();
        }
    }

    private void setFirstRunData() {
        String cohortDate = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        analytics.setUserProperty("Cohort", cohortDate);
    }
}
