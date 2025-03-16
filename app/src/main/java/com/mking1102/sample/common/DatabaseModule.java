package com.mking1102.sample.common;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Singleton
    @Provides
    ChronosDatabase provideDb(@ApplicationContext Context context) {
        RoomDatabase.Builder<ChronosDatabase> builder = Room.databaseBuilder(context, ChronosDatabase.class, "ChronosDatabase.db");
        return builder.build();
    }

}
