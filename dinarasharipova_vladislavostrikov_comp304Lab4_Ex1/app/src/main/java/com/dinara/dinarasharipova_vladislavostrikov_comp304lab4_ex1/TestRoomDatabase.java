package com.dinara.dinarasharipova_vladislavostrikov_comp304lab4_ex1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Room database class
@Database(entities = {Test.class}, version = 1)
public abstract class TestRoomDatabase extends RoomDatabase {
    //
    private static volatile TestRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    // use this to run database operations
    // asynchronously on a background thread.
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static final String DATABASE_NAME = "TestDB";
    public abstract TestDAO testDAO();

    //create and initialize the database instance
    static TestRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TestRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TestRoomDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
