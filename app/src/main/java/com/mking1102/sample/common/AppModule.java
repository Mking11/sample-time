package com.mking1102.sample.common;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class) // Makes dependencies available app-wide
public class AppModule {

    private static final String PREF_NAME = "StopwatchPreferences";

    // Provide SharedPreferences as a Singleton
    @Provides
    @Singleton
    public static SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


}
