package com.TestingCenter.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.TestingCenter.Models.Patient;
import com.TestingCenter.Models.PatientRepository;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {
    private PatientRepository patientRepository;

    //private List<Patient> allPatients;


    public PatientViewModel(@NonNull Application application) {
        super(application);
        this.patientRepository = new PatientRepository(application);
   //     this.allPatients = patientRepository.AllPatients();
    }

    public void insert(Patient patient){
        patientRepository.insert(patient);
    }
    public void update(Patient patient){
        patientRepository.update(patient);
    }

    public void delete(Patient patient){
        patientRepository.delete(patient);
    }

    public List<Patient> allPatients(){
        return patientRepository.AllPatients();
    }

    public Patient getPatientById(int patientId)
    {
        return patientRepository.getPatientById(patientId);
    }
}
