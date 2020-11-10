package com.dinara.dinarasharipova_vladislavostrikov_comp304lab4_ex1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Room database class
@Database(entities = {Patient.class}, version = 1)
public abstract class PatientRoomDatabase extends RoomDatabase {
    //
    private static volatile PatientRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    // use this to run database operations
    // asynchronously on a background thread.
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static final String DATABASE_NAME = "PatientDB";
    public abstract PatientDAO patientDAO();

    //create and initialize the database instance
    static PatientRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PatientRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PatientRoomDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
