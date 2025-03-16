package com.mking1102.sample.chronos.data.data_source;

import android.content.SharedPreferences;

import com.mking1102.sample.chronos.domain.models.ChronosPref;

public class PreferenceManager {
    private static final String KEY_TOTAL_TIME = "total_time";
    private static final String KEY_START_TIME = "start_time";
    private static final String KEY_LAP_TIME = "lap_time";
    private static final String KEY_LAP = "lap";

    private static final String USAGE_COUNT = "usage_count";
    private static final String IS_FIRST_RUN = "first_run";

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public PreferenceManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    private void setTotalTime(long totalTime) {
        editor.putLong(KEY_TOTAL_TIME, totalTime).apply();
    }

    private long getTotalTime() {
        return sharedPreferences.getLong(KEY_TOTAL_TIME, 0);
    }

    public void setUsageCount(Integer usageCount) {
        editor.putInt(USAGE_COUNT, usageCount).apply();
    }

    public Integer getUsageCount() {
        return sharedPreferences.getInt(USAGE_COUNT, 0);
    }

    public void setFirstRun(Boolean firstRun) {
        editor.putBoolean(IS_FIRST_RUN, firstRun).apply();
    }

    public Boolean getIsFirstRun() {
        return sharedPreferences.getBoolean(IS_FIRST_RUN, true);
    }

    private void setStartTime(long startTime) {
        editor.putLong(KEY_START_TIME, startTime).apply();
    }

    private long getStartTime() {
        return sharedPreferences.getLong(KEY_START_TIME, 0L);
    }

    private void setLapTime(long lapTime) {
        editor.putLong(KEY_LAP_TIME, lapTime).apply();
    }

    private long getLapTime() {
        return sharedPreferences.getLong(KEY_LAP_TIME, 0L);
    }

    private void setLap(int lap) {
        editor.putInt(KEY_LAP, lap).apply();
    }

    private int getLap() {
        return sharedPreferences.getInt(KEY_LAP, 0);
    }

    public void setPefs(ChronosPref pref) {
        setLap(pref.currentLap());
        setLapTime(pref.lapTime());
        setStartTime(pref.startTime());
        setTotalTime(pref.elapsedTime());
    }

    public ChronosPref getPrefs() {
        return new ChronosPref(getStartTime(), getLapTime(), getTotalTime(), getLap());
    }

    // Clear all stored values
    public void clearPreferences() {
        setLap(0);
        setLapTime(0L);
        setStartTime(0L);
        setTotalTime(0L);
    }
}
