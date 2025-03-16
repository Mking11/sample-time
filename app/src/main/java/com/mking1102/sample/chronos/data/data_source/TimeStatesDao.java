package com.mking1102.sample.chronos.data.data_source;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mking1102.sample.chronos.domain.models.TimeStates;

import java.util.List;

@Dao
public interface TimeStatesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTimeStates(TimeStates timeStates);

    @Query("Delete from time_states")
    void deleteAll();

    @Query("Select * From time_states order by lapIndex Desc")
    LiveData<List<TimeStates>> getAllTimeStates();


}
