package com.mking1102.sample.chronos.data.repositories;

import androidx.lifecycle.LiveData;

import com.mking1102.sample.chronos.domain.models.ChronosPref;
import com.mking1102.sample.chronos.domain.models.TimeStates;

import java.util.List;

public interface ChronosRepository {

    void insertTimeStates(TimeStates timeStates);

    void deleteAll();

    LiveData<List<TimeStates>> getAllTimeStates();

    void setPrefs(ChronosPref pref);

    ChronosPref getCurrentPrefs();

    void clearPrefs();

    void setIsFirstRun(Boolean isFirstRun);

    Boolean getIsFirstRun();

    void updateUsage(Integer usage);

    Integer getUsage();

}
