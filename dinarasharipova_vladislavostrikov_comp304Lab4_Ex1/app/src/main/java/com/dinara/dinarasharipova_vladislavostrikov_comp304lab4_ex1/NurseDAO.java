package com.dinara.dinarasharipova_vladislavostrikov_comp304lab4_ex1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NurseDAO {
    @Insert
    void insert(Nurse nurse);
    //Monitoring Query Result Changes with Live Data
    @Query("select * from Nurse order by nurseId")
    LiveData<List<Nurse>> getAllNurses();
}