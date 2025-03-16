package com.mking1102.sample.chronos.data.repositories;

import androidx.lifecycle.LiveData;

import com.mking1102.sample.chronos.data.data_source.PreferenceManager;
import com.mking1102.sample.chronos.domain.models.ChronosPref;
import com.mking1102.sample.chronos.domain.models.TimeStates;
import com.mking1102.sample.chronos.data.data_source.TimeStatesDao;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChronosRepositoryImpl implements ChronosRepository {


    private final TimeStatesDao dao;
    private final PreferenceManager preferenceManager;

    public ChronosRepositoryImpl(TimeStatesDao dao, PreferenceManager preferenceManager) {
        this.dao = dao;
        this.preferenceManager = preferenceManager;
    }

    @Override
    public void insertTimeStates(TimeStates timeStates) {
        io.reactivex.rxjava3.core.Completable.fromRunnable(() -> dao.insertTimeStates(timeStates)).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public void deleteAll() {
        Completable.fromRunnable(dao::deleteAll).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public LiveData<List<TimeStates>> getAllTimeStates() {
        return dao.getAllTimeStates();
    }

    @Override
    public void setPrefs(ChronosPref pref) {
        preferenceManager.setPefs(pref);
    }

    @Override
    public ChronosPref getCurrentPrefs() {
        return preferenceManager.getPrefs();
    }

    @Override
    public void clearPrefs() {
        preferenceManager.clearPreferences();
    }

    @Override
    public void setIsFirstRun(Boolean isFirstRun) {
        preferenceManager.setFirstRun(isFirstRun);
    }

    @Override
    public Boolean getIsFirstRun() {
        return preferenceManager.getIsFirstRun();
    }

    @Override
    public void updateUsage(Integer usage) {
        preferenceManager.setUsageCount(usage);
    }

    @Override
    public Integer getUsage() {
        return preferenceManager.getUsageCount();
    }
}
