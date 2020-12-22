package com.TestingCenter.Models;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

//we create this class to access the database using a separate thread
public class NurseRepository {

    private NurseDao nurseDao;
    //private LiveData<List<Nurse>> allNurses;
    private LiveData<List<Nurse>> allNurses;

    //constructor
    public NurseRepository(Context context) {
        AppDB db = AppDB.getInstance(context);
        nurseDao = db.nurseDao();
        allNurses = nurseDao.getAllNurses();
    }

    public void insert(final Nurse nurse){
        new Thread(new Runnable() {
            @Override
            public void run() {
                nurseDao.insert(nurse);
            }
        }).start();
    }




    public void update(final Nurse nurse){
        new Thread(new Runnable() {
            @Override
            public void run() {
                nurseDao.update(nurse);
            }
        }).start();
    }

    public void delete(final Nurse nurse){
        new Thread(new Runnable() {
            @Override
            public void run() {
                nurseDao.delete(nurse);
            }
        }).start();
    }


    public LiveData<List<Nurse>> allNurses(){
        return allNurses;
    }


}
