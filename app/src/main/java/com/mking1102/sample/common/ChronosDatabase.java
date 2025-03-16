package com.mking1102.sample.common;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mking1102.sample.chronos.domain.models.TimeStates;
import com.mking1102.sample.chronos.data.data_source.TimeStatesDao;

@Database(entities = {TimeStates.class}, version = 1, exportSchema = false)
public abstract class ChronosDatabase extends RoomDatabase {
    public abstract TimeStatesDao timeStatesDao();

}