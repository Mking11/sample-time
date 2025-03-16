package com.mking1102.sample.chronos;

import android.content.SharedPreferences;

import com.mking1102.sample.chronos.data.data_source.PreferenceManager;
import com.mking1102.sample.chronos.data.repositories.ChronosRepository;
import com.mking1102.sample.chronos.data.repositories.ChronosRepositoryImpl;
import com.mking1102.sample.common.ChronosDatabase;
import com.mking1102.sample.chronos.data.data_source.TimeStatesDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class TimeStateModule {


    @Provides
    @Singleton
    TimeStatesDao provideTimeStateDao(ChronosDatabase database) {
        return database.timeStatesDao();
    }

    @Singleton
    @Provides
    PreferenceManager providePrefsManager(
            SharedPreferences prefs
    ) {
        return new PreferenceManager(prefs);
    }

    @Singleton
    @Provides
    ChronosRepository provideChronosRepository(
            TimeStatesDao timeStatesDao,
            PreferenceManager prefs
    ) {
        return new ChronosRepositoryImpl(timeStatesDao, prefs);
    }
}
