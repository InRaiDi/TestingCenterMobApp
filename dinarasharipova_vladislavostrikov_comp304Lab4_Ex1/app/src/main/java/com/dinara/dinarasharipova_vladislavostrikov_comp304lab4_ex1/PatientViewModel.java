package com.dinara.dinarasharipova_vladislavostrikov_comp304lab4_ex1;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

//provides data to the UI and acts as a communication center
// between the Repository and the UI.
public class PatientViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private PatientRepository patientRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Patient>> allPatients;
    //
    public PatientViewModel( Application application) {
        super(application);
        patientRepository = new PatientRepository(application);
        insertResult = patientRepository.getInsertResult();
        allPatients = patientRepository.getAllPatients();
    }
    //calls repository to insert a person
    public void insert(Patient patient) {
        patientRepository.insert(patient);
    }
    //gets insert results as LiveData object
    public LiveData<Integer> getInsertResult() {
        return insertResult;
    }
    //returns query results as live data object
    LiveData<List<Patient>> getAllPatients() { return allPatients; }

}