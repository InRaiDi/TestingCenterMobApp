package com.TestingCenter.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.TestingCenter.Models.Nurse;
import com.TestingCenter.Models.NurseRepository;

import java.util.List;

public class NurseViewModel extends AndroidViewModel {

    private NurseRepository nurseRepository;

    private LiveData<List<Nurse>> allNurses;


    public NurseViewModel(@NonNull Application application) {
        super(application);
        this.nurseRepository = new NurseRepository(application);
        this.allNurses = nurseRepository.allNurses();
    }

    public void insert(Nurse nurse){
        nurseRepository.insert(nurse);
    }
    public void update(Nurse nurse){
        nurseRepository.update(nurse);
    }

    public void delete(Nurse nurse){
        nurseRepository.delete(nurse);
    }
    public LiveData<List<Nurse>> allNurses(){
        return allNurses;
    }





    public boolean checkLogin(int nurseId, String password){


        for(int i=0;i<allNurses.getValue().size();i++){
            if(allNurses.getValue().get(i).getNurseId()==nurseId &&
                    allNurses.getValue().get(i).getPassword().equals(password)){
                return true;

            }
        }
        return false;

    }
}
