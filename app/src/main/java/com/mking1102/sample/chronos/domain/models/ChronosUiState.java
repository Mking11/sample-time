package com.mking1102.sample.chronos.domain.models;

import java.util.Objects;

public class ChronosUiState {


    private final long miiSeconds;
    private final long seconds;
    private final long minutes;
    private final long hours;


    // Default Constructor
    public ChronosUiState() {


        this.miiSeconds = 0L;
        this.seconds = 0L;
        this.minutes = 0L;
        this.hours = 0L;
    }

    // Parameterized Constructor
    public ChronosUiState(
                          long miiSeconds, long seconds, long minutes, long hours) {

        this.miiSeconds = miiSeconds;
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
    }

    // Getters


    public long getMiiSeconds() {
        return miiSeconds;
    }

    public long getSeconds() {
        return seconds;
    }

    public long getMinutes() {
        return minutes;
    }

    public long getHours() {
        return hours;
    }

    // toString
    @Override
    public String toString() {
        return "ChronosState{" +
                ", miiSeconds=" + miiSeconds +
                ", seconds=" + seconds +
                ", minutes=" + minutes +
                ", hours=" + hours +
                '}';
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChronosUiState that = (ChronosUiState) o;
        return
                miiSeconds == that.miiSeconds &&
                        seconds == that.seconds &&
                        minutes == that.minutes &&
                        hours == that.hours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(miiSeconds, seconds, minutes, hours);
    }

}
