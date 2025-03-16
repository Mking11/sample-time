package com.mking1102.sample.chronos.domain.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity(tableName = "time_states")
public class TimeStates {
    @PrimaryKey
    @NotNull
    private final String lap;
    private final String lapTime;
    private final String totalTime;
    private final Integer lapIndex;


    // Parameterized Constructor
    public TimeStates(String lap, String lapTime, String totalTime, Integer lapIndex) {
        this.lap = lap;
        this.lapTime = lapTime;
        this.totalTime = totalTime;
        this.lapIndex = lapIndex;
    }


    // Getters
    public String getLap() {
        return lap;
    }

    public String getLapTime() {
        return lapTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public Integer getLapIndex() {
        return lapIndex;
    }

    // toString
    @Override
    public String toString() {
        return "TimeStates{" +
                "lap='" + lap + '\'' +
                ", lapTime='" + lapTime + '\'' +
                ", totalTime='" + totalTime + '\'' +
                ", lapIndex='" + lapIndex + '\'' +
                '}';
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeStates that = (TimeStates) o;
        return Objects.equals(lap, that.lap) &&
                Objects.equals(lapTime, that.lapTime) &&
                Objects.equals(totalTime, that.totalTime) &&
                Objects.equals(lapIndex, that.lapIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lap, lapTime, totalTime, lapIndex);
    }
}