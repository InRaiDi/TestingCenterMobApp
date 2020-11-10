package com.dinara.dinarasharipova_vladislavostrikov_comp304lab4_ex1;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

//provides data to the UI and acts as a communication center
// between the Repository and the UI.
public class TestViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private TestRepository testRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Test>> allTests;
    //
    public TestViewModel( Application application) {
        super(application);
        testRepository = new TestRepository(application);
        insertResult = testRepository.getInsertResult();
        allTests = testRepository.getAllTests();
    }
    //calls repository to insert a person
    public void insert(Test test) {
        testRepository.insert(test);
    }
    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    //returns query results as live data object
    LiveData<List<Test>> getAllTests() { return allTests; }

}