package com.dinara.dinarasharipova_vladislavostrikov_comp304lab4_ex1;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

//a class for managing multiple data sources
//
public class PatientRepository {
    private final PatientDAO patientDAO;

    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Patient>> patientsList;
    //
    public PatientRepository(Context context) {
        //create a database object
        PatientRoomDatabase db = PatientRoomDatabase.getDatabase(context);
        //create an interface object
        patientDAO = db.patientDAO();
        //call interface method
        patientsList = patientDAO.getAllPatients();
    }
    // returns query results as LiveData object
    LiveData<List<Patient>> getAllPatients() {
        return patientsList;
    }
    //
    // returns insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Patient patient) {
        PatientRoomDatabase.databaseWriteExecutor.execute(() -> {
            try {
                patientDAO.insert(patient);
                insertResult.postValue(1); //correct
            } catch (Exception e) {
                insertResult.postValue(0); //error
            }
        });
    }

}
