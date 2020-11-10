package com.dinara.dinarasharipova_vladislavostrikov_comp304lab4_ex1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Room database class
@Database(entities = {Nurse.class}, version = 1)
public abstract class NurseRoomDatabase extends RoomDatabase {
    //
    private static volatile NurseRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    // use this to run database operations
    // asynchronously on a background thread.
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static final String DATABASE_NAME = "NurseDB";
    public abstract NurseDAO nurseDAO();

    //create and initialize the database instance
    static NurseRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NurseRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NurseRoomDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
