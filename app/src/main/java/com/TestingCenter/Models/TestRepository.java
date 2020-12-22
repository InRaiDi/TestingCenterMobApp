package com.TestingCenter.Models;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

//import androidx.lifecycle.LiveData;

public class TestRepository {
    private TestDao testDao;
    private List<Test> allTests;

    public TestRepository(Context context) {
        AppDB db = AppDB.getInstance(context);
        testDao = db.testDao();
        //allPatients = patientDao.getAllPatients();
    }

    public void insert(final Test test) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                testDao.insert(test);
            }
        }).start();
    }

    public void update(final Test test) {
        new Thread(new Runnable() {
            @Override
            public void run() { testDao.update(test);
            }
        }).start();
    }

    public void delete(final Test test) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                testDao.delete(test);
            }
        }).start();
    }

    public List<Test> AllTests() {
        try {
            return new GetAllTestsAsync().execute().get();
        } catch (Exception ex) {
            Log.e("Error: ", ex.getMessage());
        }
        return null;
    }

    private class GetAllTestsAsync extends AsyncTask<Void, Void, List<Test>> {
        @Override
        protected List<Test> doInBackground(Void... voids) {
            try {
                return testDao.getAllTests();
            } catch (Exception ex) {
                Log.e("Error: ", ex.getMessage());
            }
            return null;
        }
    }

    public Test getTestById(int testId) {

        try {
            return new getTestByIdAsync().execute(testId).get();
        } catch (Exception ex) {
            Log.e("Error: ", ex.getMessage());
        }
        return null;
    }

    private class getTestByIdAsync extends AsyncTask<Integer, Void, Test> {
        @Override
        protected Test doInBackground(Integer... params) {
            try {
                return testDao.getTestById(params[0]);
            } catch (Exception ex) {
                Log.e("Error: ", ex.getMessage());
            }
            return null;
        }


    }

    public List<Test> GetTestsByPatientId(int patientId) {
        try {
            return new GetTestsByPatientIdAsync().execute(patientId).get();
        } catch (Exception ex) {
            Log.e("Error: ", ex.getMessage());
        }
        return null;
    }

    private class GetTestsByPatientIdAsync extends AsyncTask<Integer, Void, List<Test>> {
        @Override
        protected List<Test> doInBackground(Integer... params) {
            try {
                return testDao.getTestsByPatientId(params[0]);
            } catch (Exception ex) {
                Log.e("Error: ", ex.getMessage());
            }
            return null;
        }
    }
}