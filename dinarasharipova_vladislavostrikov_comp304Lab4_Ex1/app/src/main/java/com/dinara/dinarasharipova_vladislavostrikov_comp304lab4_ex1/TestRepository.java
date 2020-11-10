package com.dinara.dinarasharipova_vladislavostrikov_comp304lab4_ex1;


import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

//a class for managing multiple data sources
//
public class TestRepository {
    private final TestDAO testDAO;

    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Test>> testsList;
    //
    public TestRepository(Context context) {
        //create a database object
        TestRoomDatabase db = TestRoomDatabase.getDatabase(context);
        //create an interface object
        testDAO = db.testDAO();
        //call interface method
        testsList = testDAO.getAllTests();
    }
    // returns query results as LiveData object
    LiveData<List<Test>> getAllTests() {
        return testsList;
    }
    //
    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Test test) {
        TestRoomDatabase.databaseWriteExecutor.execute(() -> {
            try {
                testDAO.insert(test);
                insertResult.postValue(1); //correct
            } catch (Exception e) {
                insertResult.postValue(0); //error
            }
        });
    }

}
