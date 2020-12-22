package com.TestingCenter.Models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TestDao {

    @Insert
    void insert(Test test);

    @Update
    void update(Test test);

    @Delete
    void delete(Test test);


    @Query("select * from Test")
    List<Test> getAllTests();

    @Query("select * from Test where testId = :testId")
    Test getTestById(int testId);

    @Query("select * from Test where patientId= :patientId")
    List<Test> getTestsByPatientId(int patientId);
}
