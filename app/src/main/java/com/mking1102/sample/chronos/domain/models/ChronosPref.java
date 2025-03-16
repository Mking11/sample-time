package com.mking1102.sample.chronos.domain.models;

public record ChronosPref(
        Long startTime,
        Long lapTime,
        Long elapsedTime,
        Integer currentLap
){}