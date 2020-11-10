package com.dinara.dinarasharipova_vladislavostrikov_comp304lab4_ex1;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

//a class for managing multiple data sources
//
public class NurseRepository {
    private final NurseDAO nurseDAO;

    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Nurse>> nursesList;
    //
    public NurseRepository(Context context) {
        //create a database object
        NurseRoomDatabase db = NurseRoomDatabase.getDatabase(context);
        //create an interface object
        nurseDAO = db.nurseDAO();
        //call interface method
        nursesList = nurseDAO.getAllNurses();
    }
    // returns query results as LiveData object
    LiveData<List<Nurse>> getAllNurses() {
        return nursesList;
    }
    //
    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Nurse nurse) {
        NurseRoomDatabase.databaseWriteExecutor.execute(() -> {
            try {
                nurseDAO.insert(nurse);
                insertResult.postValue(1); //correct
            } catch (Exception e) {
                insertResult.postValue(0); //error
            }
        });
    }

}
